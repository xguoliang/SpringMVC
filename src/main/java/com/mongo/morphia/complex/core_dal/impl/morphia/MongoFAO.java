/**
 *
 * 
 * 修订历史记录：
 * 
 * Revision	1.0	 2010-10-21  guolei_mao  创建。
 * 
 */

package com.mongo.morphia.complex.core_dal.impl.morphia;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;




import org.apache.commons.lang.StringUtils;
import org.bson.types.ObjectId;

import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.QueryImpl;
import com.mongo.morphia.complex.core.ContextToken;
import com.mongo.morphia.complex.core_dal.FAO;
import com.mongo.morphia.complex.core_dal.FileMetadata;
import com.mongo.morphia.complex.core_dal.Updates;
import com.mongo.morphia.complex.core_dal.datasource.DataSource;
import com.mongo.morphia.complex.core_dal.datasource.DataSourceConfigManager;
import com.mongo.morphia.complex.core_dal.datasource.DataSourceException;
import com.mongo.morphia.complex.core_dal.datasource.PersistenceInfo;
import com.mongo.morphia.complex.core_dal.datasource.PersistenceUnitInfo;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.QueryOperators;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

/**
 * 类说明:
 * 
 * @author <a href="mailto:guolei_mao@kingdee.com">guolei_mao</a>
 * @version 1.0, 2010-10-21
 */
public class MongoFAO implements FAO {
    private MongoFS gfs;

    public MongoFAO(ContextToken ct) throws DataSourceException {
        this(ct, null);
    }

    public MongoFAO(ContextToken ct, String bucketName) throws DataSourceException {
        this( (ct!=null&&ct.getContext()!=null)? ct.getContext().getTenantId(): null
                , bucketName);
    }
    
    public MongoFAO(String tenantId, String bucketName) throws DataSourceException {
        PersistenceInfo pi = DataSourceConfigManager.getByTenantID(tenantId);
        PersistenceUnitInfo pui = pi.getFileUnit();
        DataSource ds = pui.getDataSource();
        if (StringUtils.isEmpty(bucketName))
            gfs = new MongoFS(ds.getDB());
        else
            gfs = new MongoFS(ds.getDB(), bucketName);
    }

    @Override
    public String saveFile(InputStream in, FileMetadata metadata) {
        PushbackInputStream pis = ContentType.getPushbackInputStream(in);
        GridFSInputFile gf = gfs.createFile(pis);
        setContentType(pis, metadata);
        return save(gf, metadata);
    }

    private void setContentType(PushbackInputStream pis, FileMetadata metadata) {
        if (StringUtils.isEmpty(metadata.getContentType())) {
            String type = ContentType.getType(pis);
            if (type != null)
                metadata.setContentType(type);
        }
    }

    private void setContentType(File file, FileMetadata metadata) {
        if (StringUtils.isEmpty(metadata.getContentType())) {
            String type = ContentType.getType(file);
            if (type != null)
                metadata.setContentType(type);
        }
    }

    @Override
    public String saveFile(InputStream in, String filename) {
        FileMetadata metadata = new MongoFileMetadata();
        metadata.setFileName(filename);
        return saveFile(in, metadata);
    }

    private String save(GridFSInputFile gf, FileMetadata metadata) {
        if (StringUtils.isEmpty(metadata.getContentType())) {
            String filename = metadata.getFileName();
            String suffix = filename.substring(filename.lastIndexOf('.') + 1);
            String type = ContentType.getType(suffix);
            if (type != null) {
                metadata.setContentType(type);
            }
        }

        Map<String, Object> map = metadata.getMetadata();
        Set<Map.Entry<String, Object>> set = map.entrySet();
        for (Map.Entry<String, Object> entry : set) {
            gf.put(entry.getKey(), entry.getValue());
        }

        gf.save();
        return gf.getId().toString();
    }

    @Override
    public String saveFile(File file) throws IOException {
        FileMetadata metadata = new MongoFileMetadata();
        metadata.setFileName(file.getName());
        return saveFile(file, metadata);
    }

    @Override
    public String saveFile(File file, FileMetadata metadata) throws IOException {
        GridFSInputFile gf = gfs.createFile(file);

        if (StringUtils.isEmpty(metadata.getFileName()))
            metadata.setFileName(file.getName());

        setContentType(file, metadata);

        return save(gf, metadata);
    }

    @Override
    public String saveFile(byte[] bytes, String filename) {
        FileMetadata metadata = new MongoFileMetadata();
        metadata.setFileName(filename);
        return saveFile(bytes, metadata);
    }

    @Override
    public String saveFile(byte[] bytes, FileMetadata metadata) {
        GridFSInputFile gf = gfs.createFile(bytes);
        return save(gf, metadata);
    }

    /**
     * 根据文件名称，在数据库中查找文件
     * 
     * @param filename
     * @return
     */
    public List<GridFSDBFile> findFilesByName(String filename) {
        return gfs.find(filename);
    }

    public List<GridFSDBFile> findFilesByName(String filename, int start, int limit, String... orderBy) {
        DBObject query = new BasicDBObject(FileMetadata.fileName, filename);
        DBObject order = getOrder(orderBy);
        return gfs.find(query, order, start, limit);
    }

    public GridFSDBFile findFileByName(String filename) {
        return gfs.findOne(filename);
    }

    public GridFSDBFile findFileById(ObjectId id) {
        return gfs.findOne(id);
    }

    public GridFSDBFile findFileById(String id) {
        return gfs.findOne(new ObjectId(id));
    }

    @Override
    public InputStream getInputStreamByName(String filename) {
        return gfs.findOne(filename).getInputStream();
    }

    @Override
    public InputStream getInputStreamById(String id) {
        return gfs.find(new ObjectId(id)).getInputStream();
    }

    public List<GridFSDBFile> findFilesByNetworkId(String networkId, int start, int limit, String... orderBy) {
        DBObject query = new BasicDBObject(FileMetadata.networkId, networkId);
        DBObject order = getOrder(orderBy);
        return gfs.find(query, order, start, limit);
    }

    public int countByNetworkId(String networkId) {
        DBObject query = new BasicDBObject(FileMetadata.networkId, networkId);
        return gfs.count(query);
    }

    private DBObject getOrder(String... orderBy) {
        DBObject order = null;
        if (orderBy != null) {
            order = new BasicDBObject();
            for (String s : orderBy) {
                if (s.startsWith("-"))
                    order.put(s.substring(1), -1);
                else
                    order.put(s, 1);
            }
        }
        return order;
    }

    public List<GridFSDBFile> findFilesByNetworkAndGroupId(String networkId, String groupId, int start, int limit,
            String... orderBy) {
        DBObject query = new BasicDBObject(FileMetadata.networkId, networkId);
        query.put(FileMetadata.groupId, groupId);
        DBObject order = getOrder(orderBy);
        return gfs.find(query, order, start, limit);
    }

    public int countByNetworkAndGroupId(String networkId, String groupId) {
        DBObject query = new BasicDBObject(FileMetadata.networkId, networkId);
        query.put(FileMetadata.groupId, groupId);
        return gfs.count(query);
    }

    public List<GridFSDBFile> findFilesByNetworkAndUserId(String networkId, String userId, int start, int limit,
            String... orderBy) {
        DBObject query = new BasicDBObject(FileMetadata.networkId, networkId);
        query.put(FileMetadata.userId, userId);
        DBObject order = getOrder(orderBy);
        return gfs.find(query, order, start, limit);
    }
    
    public int countByNetworkAndUserId(String networkId, String userId) {
        DBObject query = new BasicDBObject(FileMetadata.networkId, networkId);
        query.put(FileMetadata.userId, userId);
        return gfs.count(query);
    }
    
    public List<GridFSDBFile> findFilesByNetworkAndUserId(String networkId, String userId, String appId, int start, int limit,
            String... orderBy) {
        DBObject query = new BasicDBObject(FileMetadata.networkId, networkId);
        query.put(FileMetadata.userId, userId);
        query.put(FileMetadata.appId, appId);
        DBObject order = getOrder(orderBy);
        return gfs.find(query, order, start, limit);
    }

    public int countByNetworkAndUserId(String networkId, String userId, String appId) {
        DBObject query = new BasicDBObject(FileMetadata.networkId, networkId);
        query.put(FileMetadata.userId, userId);
        query.put(FileMetadata.appId, appId);
        return gfs.count(query);
    }

    public List<GridFSDBFile> findFilesByNetworkAndAppId(String networkId, String appId, int start, int limit,
            String... orderBy) {
        DBObject query = new BasicDBObject(FileMetadata.networkId, networkId);
        query.put(FileMetadata.appId, appId);
        DBObject order = getOrder(orderBy);
        return gfs.find(query, order, start, limit);
    }

    public int countByNetworkAndAppId(String networkId, String appId) {
        DBObject query = new BasicDBObject(FileMetadata.networkId, networkId);
        query.put(FileMetadata.appId, appId);
        return gfs.count(query);
    }

    public List<GridFSDBFile> findFilesByUserId(String userId, int start, int limit, String... orderBy) {
        DBObject query = new BasicDBObject(FileMetadata.userId, userId);
        DBObject order = getOrder(orderBy);
        return gfs.find(query, order, start, limit);
    }

    public int countByUserId(String userId) {
        DBObject query = new BasicDBObject(FileMetadata.userId, userId);
        return gfs.count(query);
    }

    public List<GridFSDBFile> find(DBObject query) {
        return gfs.find(query);
    }
    
    public void remove(DBObject query) {
        gfs.remove(query);
    }
    
    public int count(DBObject query) {
        return gfs.count(query);
    }

    @Override
    public void removeFileByName(String filename) {
        gfs.remove(filename);
    }

    @Override
    public void removeFileById(String id) {
        removeFile(new ObjectId(id));
    }

    public void removeFile(BasicDBObject query) {
        gfs.remove(query);
    }

    public void removeFile(ObjectId id) {
        gfs.remove(id);
    }

    @Override
    public FileMetadata createFileMetadata() {
        return new MongoFileMetadata();
    }

    @Override
    public void update(String id, FileMetadata metadata) {
        DBCollection dbc = gfs.getFilesCollection();
        DBObject query = new BasicDBObject("_id", new ObjectId(id));
        metadata.setConfirm(true);
        DBObject dbo = ((MongoFileMetadata) metadata).getDBObject();
        Updates up = new com.mongo.morphia.complex.core_dal.impl.UpdatesImpl();
        Set<String> keys = dbo.keySet();
        for (String key : keys) {
            if ("id".equalsIgnoreCase(key) || "_id".equalsIgnoreCase(key) || "chunkSize".equalsIgnoreCase(key))
                continue;

            up.set(key, dbo.get(key));
        }

        dbc.update(query, new BasicDBObject(up.getOperations()));
    }

    public List<GridFSDBFile> findFileByIds(List<String> ids, String... orderBy) {
        if (ids == null || ids.size() == 0) {
            return Collections.emptyList();
        }
        
        List<ObjectId> oids = new ArrayList<ObjectId>(ids.size());
        for (String s : ids) {
            oids.add(new ObjectId(s));
        }
        DBObject query = new BasicDBObject("_id", new BasicDBObject(QueryOperators.IN, oids));
        DBObject order = getOrder(orderBy);
        return gfs.find(query, order, 0, 0);
    }
    
    @SuppressWarnings("rawtypes")
    public List<GridFSDBFile> find(Query query) {
        return gfs.find(((QueryImpl) query).getQueryObject(), ((QueryImpl) query).getSortObject(),
                ((QueryImpl) query).getOffset(), ((QueryImpl) query).getLimit());
    }
}
