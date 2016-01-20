/**
 *
 * 
 * 修订历史记录：
 * 
 * Revision	1.0	 2010-12-10  guolei_mao  创建。
 * 
 */

package com.mongo.morphia.complex.core_dal.util;

import java.util.Date;

import com.mongo.morphia.complex.core_dal.datasource.DataSource;
import com.mongo.morphia.complex.core_dal.datasource.DataSourceConfigManager;
import com.mongo.morphia.complex.core_dal.datasource.PersistenceInfo;
import com.mongo.morphia.complex.core_dal.datasource.PersistenceUnitInfo;


//import com.mongodb.MapReduceCommand;
//import com.mongodb.MapReduceOutput;

/**
 * 类说明:
 * 
 * @author <a href="mailto:guolei_mao@kingdee.com">guolei_mao</a>
 * @version 1.0, 2010-12-10
 */
public class DBTimeUtil {
    
    private static DataSource ds;
    
    // By Jianbo Liu
    public static Date getCurrentTime() {
        init();
        
        return new java.util.Date();
//        DB db = ds.getDB();
//        CommandResult res = db.command(BasicDBObjectBuilder.start()
//                .add( "$eval" , "function() {  return new Date();}")
//                .add("nolock", true)
//                .get()
//                ); 
//        
//        return (java.util.Date)res.get("retval");
    }
    
    private static void init(){
        if(ds == null ){
            PersistenceInfo pi = DataSourceConfigManager.getByContext(null);
            PersistenceUnitInfo pui = pi.getByName("oss");
            ds = pui.getDataSource();               
        }
    }
    
//    private static Logger logger = LoggerFactory.getLogger(DBTimeUtil.class);
//    private static DBCollection dbc;
//    
//    static {
//        try {
//            init();
//        } catch (Throwable t) {
//            logger.error(t.getMessage(), t);
//        }
//    }
//
//    /*
//    public static Date getCurrentTime() {
//        init();
//
//        String map = "function() {emit(this.author, {now: new Date()});}";
//        String reduce = "function(k,vals){return vals[0].now;}";
//
//        MapReduceOutput cr = null;
//        try {
//            cr = dbc.mapReduce(map, reduce, null, MapReduceCommand.OutputType.INLINE, null);
//            Iterable<DBObject> ite = cr.results();
//            for (DBObject dbObject : ite) {
//                DBObject dbo = (com.mongodb.DBObject) dbObject.get("value");
//                return (Date) dbo.get("now");
//            }
//        } catch (Throwable t) {
//            logger.error(t.getMessage(), t);
//        } finally {
//            if (cr != null) {
//                try {
//                    cr.drop();
//                } catch (Throwable e) {
//                    logger.debug(e.getMessage(), e);
//                }
//            }
//        }
//        logger.warn("get DBTime failed, use local time");
//        return new Date();
//    }
//
//    private static void init() {
//        if (dbc == null) {
//            PersistenceInfo pi = DataSourceConfigManager.getByContext(null);
//            PersistenceUnitInfo pui = pi.getByName("oss");
//            DataSource ds = pui.getDataSource();
//            DB db = ds.getDB();
//            dbc = db.getCollection("DBTime");
//            if (dbc.count() <= 0) {
//                dbc.save(new BasicDBObject("author", "maoguolei"));
//            }
//        }
//    }
//    */
//    
//    public static Date getCurrentTime() {
//        init();
//
//        DBObject dbo = new BasicDBObject();
//        dbo.put("localId", ObjectId.get());
//        try {
//            dbc.insert(false, dbo);
//
//            DBCursor cursor = dbc.find(dbo);
//            if (cursor.hasNext()) {
//                DBObject dbobject = cursor.next();
//                ObjectId id = (ObjectId) dbobject.get("_id");
//                return new Date(id.getTime());
//            }
//        } catch (Throwable t) {
//            logger.error(t.getMessage(), t);
//        } finally {
//            dbc.remove(dbo);
//        }
//        logger.warn("get DBTime failed, use local time");
//        return new Date();
//    }
//
//    private static void init() {
//        if (dbc == null) {
//            PersistenceInfo pi = DataSourceConfigManager.getByContext(null);
//            PersistenceUnitInfo pui = pi.getByName("oss");
//            DataSource ds = pui.getDataSource();
//            DB db = ds.getDB();
//            dbc = db.getCollection("DBTime");
//        }
//    }
}
