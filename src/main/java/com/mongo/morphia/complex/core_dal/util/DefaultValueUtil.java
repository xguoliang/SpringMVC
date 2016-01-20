/**
 *
 * 
 * 修订历史记录：
 * 
 * Revision	1.0	 2012-2-29  guolei_mao  创建。
 * 
 */

package com.mongo.morphia.complex.core_dal.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;






import com.mongo.morphia.complex.core_dal.datasource.DataSource;
import com.mongo.morphia.complex.core_dal.datasource.DataSourceConfigManager;
import com.mongo.morphia.complex.core_dal.datasource.PersistenceInfo;
import com.mongo.morphia.complex.core_dal.datasource.PersistenceUnitInfo;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

/**
 * 类说明:
 * 
 * @author <a href="mailto:guolei_mao@kingdee.com">guolei_mao</a>
 * @version 1.0, 2012-2-29
 */
public class DefaultValueUtil {
    private static Logger logger = LoggerFactory.getLogger(DefaultValueUtil.class);

    private static DBCollection getCollection() {
        PersistenceInfo pi = DataSourceConfigManager.getByContext(null);
        PersistenceUnitInfo pui = pi.getByName("oss");
        DataSource ds = pui.getDataSource();
        DB db = ds.getDB();
        return db.getCollection("DefaultValue");
    }

    public static Object getDefaultValue(String key) {
        DBObject query = BasicDBObjectBuilder.start("_id", key).get();
        DBObject rst = getCollection().findOne(query);
        if (rst != null)
            return rst.get("value");

        return null;
    }

    public static void setDefaultValue(String key, Object value) {
        if (value != null) {
            DBObject dbo = BasicDBObjectBuilder.start("_id", key).add("value", value).get();
            getCollection().save(dbo);
        }
    }

    public static Object getDefaultValue(Class<?> clazz) {
        return getDefaultValue(clazz.getName());
    }

    public static void setDefaultValue(Class<?> clazz, Object value) {
        setDefaultValue(clazz.getName(), value);
    }

    public static Object getDefaultValue(Class<?> clazz, String field) {
        return getDefaultValue(clazz.getName() + "_" + field);
    }

    public static void setDefaultValue(Class<?> clazz, String field, Object value) {
        setDefaultValue(clazz.getName() + "_" + field, value);
    }
}
