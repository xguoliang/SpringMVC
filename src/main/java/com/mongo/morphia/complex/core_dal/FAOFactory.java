/**
 *
 * 
 * 修订历史记录：
 * 
 * Revision	1.0	 2010-10-21  guolei_mao  创建。
 * 
 */

package com.mongo.morphia.complex.core_dal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;






import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongo.morphia.complex.common.StringUtils;
import com.mongo.morphia.complex.core.ContextToken;
import com.mongo.morphia.complex.core_dal.datasource.DataSourceException;
import com.mongo.morphia.complex.core_dal.impl.morphia.MongoFAO;



/**
 * 类说明:
 * 
 * @author <a href="mailto:guolei_mao@kingdee.com">guolei_mao</a>
 * @version 1.0, 2010-10-21
 */
public class FAOFactory {
    private static Logger logger = LoggerFactory.getLogger(FAOFactory.class);
    private static Map<String,FAO> instances = Collections.synchronizedMap(new HashMap<String, FAO>());
    
    public static FAO getInstance(ContextToken ct,String bucketName) {
        String tenantId = null;
        if (ct != null && ct.getContext() != null) {
            tenantId = ct.getContext().getTenantId();
        }
        return getInstance(tenantId,bucketName);
    }
    
    public static FAO getInstance(ContextToken ct) {
        return getInstance(ct,null);
    }
    
    public static FAO getInstance() {
        return getInstance((ContextToken)null,null);
    }
    
    public static FAO getInstance(String tenantId, String bucketName) {
        String key = tenantId;
        if (!StringUtils.isEmpty(bucketName)) {
            key = tenantId + "." + bucketName;
        }
        FAO fao = null;
        if (instances.containsKey(key)) {
            fao = instances.get(key);
        } else {
            fao = newInstance(tenantId, bucketName);
            instances.put(key, fao);
        }
        return fao;
    }
    
   private static FAO newInstance(String tenantId,String bucketName) {
        try {
            return new MongoFAO(tenantId,bucketName);
        } catch (DataSourceException e) {
            logger.error("", e);
            throw new RuntimeException(e);
        }
    }
}
