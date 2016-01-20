package com.mongo.morphia.complex.kingdee.morphia;

/**
 *
 * 
 * 修订历史记录：
 * 
 * Revision	1.0	 2011-7-15  guolei_mao  创建。
 * 
 */

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongo.morphia.complex.core_dal.datasource.DataSourceException;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ReplicaSetStatus;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

/**
 * 类说明:
 * 
 * @author <a href="mailto:guolei_mao@kingdee.com">guolei_mao</a>
 * @version 1.0, 2011-7-15
 */
public class MongoFactory {
    private static Logger logger = LoggerFactory.getLogger(MongoFactory.class);

    private static ConcurrentMap<String, Mongo> mongoMap = new ConcurrentHashMap<String, Mongo>(8);
    private static ConcurrentMap<String, DB> dbMap = new ConcurrentHashMap<String, DB>(8);

    private static ConcurrentMap<String, MongoOptions> optionsMap = new ConcurrentHashMap<String, MongoOptions>(8);
    private static ConcurrentMap<String, WriteConcern> writeConcernMap = new ConcurrentHashMap<String, WriteConcern>(8);
    private static ConcurrentMap<String, ReadPreference> readPreferenceMap = new ConcurrentHashMap<String, ReadPreference>(8);

    public synchronized static Mongo getMongo(String url) {
        Mongo mongo = mongoMap.get(url);
        if (mongo == null) {
            try {
                url = url.trim();
                if (url.contains(",")) {
                    String[] urls = url.split(",");
                    List<ServerAddress> replicaSetSeeds = new ArrayList<ServerAddress>();
                    for (String string : urls) {
                        ServerAddress addr = null;
                        int i = string.indexOf(':');
                        if (i > 0) {
                            addr = new ServerAddress(string.substring(0, i), Integer.parseInt(string.substring(i + 1)));
                        } else {
                            addr = new ServerAddress(string);
                        }
                        replicaSetSeeds.add(addr);
                    }

                    MongoOptions options = optionsMap.get(url);
                    if (options == null) {
                        options = initOptions(url);
                    }
                    mongo = new Mongo(replicaSetSeeds, options);
                } else {
                    ServerAddress addr = null;
                    int i = url.indexOf(':');
                    if (i > 0) {
                        addr = new ServerAddress(url.substring(0, i), Integer.parseInt(url.substring(i + 1)));
                    } else {
                        addr = new ServerAddress(url);
                    }
                    MongoOptions options = optionsMap.get(url);
                    if (options == null) {
                        options = initOptions(url);
                    }
                    mongo = new Mongo(addr, options);
                }
                mongo.setWriteConcern(WriteConcern.SAFE);
                mongoMap.put(url, mongo);
            } catch (Exception e) {
                throw new DataSourceException(e);
            }
        }
        return mongo;
    }

    public static ConcurrentMap<String, Mongo> getMongos() {
        return mongoMap;
    }

    public static MongoOptions getMongoOptions(String url) {
        MongoOptions options = optionsMap.get(url);
        if (options == null) {
            options = initOptions(url);
        }
        return options;
    }

    private static MongoOptions initOptions(String url) {
        MongoOptions options = new MongoOptions();
        options.autoConnectRetry = true;
        options.connectionsPerHost = 50;
        options.connectTimeout = 10000;
        options.socketTimeout = 10000;
        options.maxWaitTime = 120000;
        options.socketKeepAlive = true;
        options.threadsAllowedToBlockForConnectionMultiplier = 5;
        
        optionsMap.put(url, options);
        return options;
    }

    public static WriteConcern getWriteConcern(String url, String db) {
        WriteConcern writeConcern = writeConcernMap.get(getKey(url, db));
        return writeConcern;
    }

    private static String getKey(String url, String dbName) {
        return url + "_" + dbName;
    }

    public static void setWriteConcern(String url, String dbName, WriteConcern writeConcern) {
        if (writeConcern != null)
            writeConcernMap.put(getKey(url, dbName), writeConcern);
    }

    public static DB getDB(String url, String dbName) {
        String key = getKey(url, dbName);
        DB db = dbMap.get(key);

        if (db == null) {
            Mongo mongo = getMongo(url);
            if (mongo == null)
                return null;

            db = mongo.getDB(dbName);
            
            boolean isRepSet = false;
            ReplicaSetStatus rss = mongo.getReplicaSetStatus();
            if (rss != null) {
            	isRepSet = rss.getMaster()!=null;
//                try {
//                    Method m = ReplicaSetStatus.class.getDeclaredMethod("getASecondary", (Class[]) null);
//                    m.setAccessible(true);
//                    Object o = m.invoke(rss, (Object[]) null);
//                    if (o != null) {
//                        isRepSet = true;
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }

            db.setReadPreference(ReadPreference.secondaryPreferred());
//            ReadPreference readPreference = MongoFactory.getReadPreference(url, dbName);
//            if (isRepSet) {
//                if (readPreference != null) {
//                    db.setReadPreference(readPreference);
//                }
//            }

            WriteConcern writeConcern = MongoFactory.getWriteConcern(url, dbName);
            if (writeConcern != null) {
                if (WriteConcern.REPLICAS_SAFE.equals(writeConcern)) {
                    if (isRepSet) {
                        db.setWriteConcern(writeConcern);
                    }
                } else {
                    db.setWriteConcern(writeConcern);
                }
            }

            // mongod以不验权方式启动，暂时屏蔽验权
            // if (pu.getMongoUser() != null && pu.getMongoPw() != null) {
            // boolean auth = db.authenticate(pu.getMongoUser(), pu.getMongoPw().toCharArray());
            // if (!auth)
            // throw new DataSourceException("Username or password is not correct to access url: "
            // + pu.getMongoUrl() + "   dbname: " + pu.getMongoDbName() + "    user: " +
            // pu.getMongoUser()
            // + "    password: " + pu.getMongoPw());
            // }

            if (!dbMap.containsKey(key))
                dbMap.put(key, db);

            logger.info("getDB url: " + url + ", dbname: " + dbName + ", writeConcern: " + db.getWriteConcern()
                    + ", readPreference: " + db.getReadPreference());
        }

        return db;
    }
    
    public static synchronized void close() {
        ConcurrentMap<String, Mongo> tempMongoMap = new ConcurrentHashMap<String, Mongo>(mongoMap);
        mongoMap.clear();
        dbMap.clear();
        for (Map.Entry<String, Mongo> entry : tempMongoMap.entrySet()) {
            entry.getValue().close();
        }
    }
    
    public static ReadPreference getReadPreference(String url, String dbName) {
        ReadPreference readPreference = readPreferenceMap.get(getKey(url, dbName));
        return readPreference;
    }

    public static void setReadPreference(String url, String dbName, ReadPreference readPreference) {
        if (readPreference != null)
            readPreferenceMap.put(getKey(url, dbName), readPreference);
    }
}
