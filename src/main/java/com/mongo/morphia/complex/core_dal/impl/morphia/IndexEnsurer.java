/**
 *
 * 
 * 修订历史记录：
 * 
 * Revision	1.0	 2010-11-17  guolei_mao  创建。
 * 
 */

package com.mongo.morphia.complex.core_dal.impl.morphia;

import java.lang.reflect.Field;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.annotations.Index;
import com.google.code.morphia.annotations.Indexed;
import com.google.code.morphia.annotations.Indexes;
import com.google.code.morphia.query.QueryImpl;
import com.google.code.morphia.utils.IndexDirection;
import com.mongo.morphia.complex.core.BObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

/**
 * 类说明:
 * 
 * @author <a href="mailto:guolei_mao@kingdee.com">guolei_mao</a>
 * @version 1.0, 2010-11-17
 */
public class IndexEnsurer {
    private static Logger logger = LoggerFactory.getLogger(IndexEnsurer.class);
    private static Set<String> classes = new ConcurrentSkipListSet<String>();

    public static void ensureIndexes(MongoHelperImpl mongoHelper, Class<? extends BObject> clazz) {
//        if (!classes.contains(clazz.getName())) {
//            try {
//                Morphia morphia = mongoHelper.getMorphia();
//                Datastore ds = morphia.createDatastore(mongoHelper.getMongo(), mongoHelper.getDb().getName());
//                ds.ensureIndexes(clazz);
//                // ensureIndexes0(mongoHelper, clazz);
//                classes.add(clazz.getName());
//            } catch (Exception ex) {
//                logger.warn(ex.getMessage(), ex);
//            }
//        }
    }

    // morphia之前的版本ds.ensureIndexes有bug，新版本已经修正
    @SuppressWarnings("unused")
    private static void ensureIndexes0(MongoHelperImpl mongoHelper, Class<? extends BObject> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotation(Indexed.class) != null) {
                Indexed index = field.getAnnotation(Indexed.class);

                int dir = 1;
                if (IndexDirection.DESC.equals(index.value()))
                    dir = -1;

                DBObject keys = BasicDBObjectBuilder.start().add(field.getName(), dir).get();

                BasicDBObjectBuilder keyOpts = new BasicDBObjectBuilder();
                if (!StringUtils.isEmpty(index.name())) {
                    keyOpts.add("name", index.name());
                }
                if (index.unique()) {
                    keyOpts.add("unique", true);
                    if (index.dropDups())
                        keyOpts.add("dropDups", true);
                }

                if (index.background()) {
                    keyOpts.add("background", true);
                }

                DBCollection dbc = mongoHelper.getCollection(clazz);
                dbc.ensureIndex(keys, keyOpts.get());
            }
        }

        Indexes idxs = clazz.getAnnotation(Indexes.class);
        if (idxs != null && idxs.value() != null && idxs.value().length > 0) {
            for (Index index : idxs.value()) {
                DBObject keys = QueryImpl.parseSortString(index.value());
                BasicDBObjectBuilder keyOpts = new BasicDBObjectBuilder();
                if (!StringUtils.isEmpty(index.name())) {
                    keyOpts.add("name", index.name());
                }
                if (index.unique()) {
                    keyOpts.add("unique", true);
                    if (index.dropDups())
                        keyOpts.add("dropDups", true);
                }

                if (index.background()) {
                    keyOpts.add("background", true);
                }

                DBCollection dbc = mongoHelper.getCollection(clazz);
                dbc.ensureIndex(keys, keyOpts.get());
            }
        }
    }
}
