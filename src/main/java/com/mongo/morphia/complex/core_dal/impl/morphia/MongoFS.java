/**
 *
 * 
 * 修订历史记录：
 * 
 * Revision	1.0	 2010-11-24  guolei_mao  创建。
 * 
 */

package com.mongo.morphia.complex.core_dal.impl.morphia;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;


/**
 * 类说明:
 * 
 * @author <a href="mailto:guolei_mao@kingdee.com">guolei_mao</a>
 * @version 1.0, 2010-11-24
 */
public class MongoFS extends GridFS {
    public MongoFS(DB db) {
        super(db);
    }

    public MongoFS(DB db, String bucket) {
        super(db, bucket);
    }

    public DBCollection getFilesCollection() {
        return super._filesCollection;
    }

    public List<GridFSDBFile> find(DBObject query, DBObject orderBy, int start, int limit) {
        List<GridFSDBFile> files = new ArrayList<GridFSDBFile>();

        DBCursor c = _filesCollection.find(query);
        if (orderBy != null) {
            c.sort(orderBy);
        }
        c.skip(start);
        c.limit(limit);
        while (c.hasNext()) {
            files.add(_fix(c.next()));
        }
        return files;
    }
    
    public int count(DBObject query) {
        DBCursor c = _filesCollection.find(query);
        return c.count();
    }
}
