/**
 *
 * 
 * 修订历史记录：
 * 
 * Revision	1.0	 2010-12-2  guolei_mao  创建。
 * 
 */

package com.mongo.morphia.complex.core_dal;

import com.mongo.morphia.complex.core_dal.impl.morphia.MongoFileMetadata;




/**
 * 类说明:
 * 
 * @author <a href="mailto:guolei_mao@kingdee.com">guolei_mao</a>
 * @version 1.0, 2010-12-2
 */
public class FileMetadataFactory {
    public static FileMetadata getInstance() {
        return new MongoFileMetadata();
    }
}
