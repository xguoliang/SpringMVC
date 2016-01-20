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
import java.util.Arrays;
import java.util.List;
import java.util.Map;



import com.mongo.morphia.complex.core_dal.FileMetadata;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * 类说明:
 * 
 * @author <a href="mailto:guolei_mao@kingdee.com">guolei_mao</a>
 * @version 1.0, 2010-11-24
 */
public class MongoFileMetadata implements FileMetadata {
    private DBObject metadata = new BasicDBObject();

    public MongoFileMetadata() {
        setVersion(1);
    }

    @Override
    public String getFileName() {
        return (String) metadata.get(FileMetadata.fileName);
    }

    @Override
    public void setFileName(String fileName) {
        metadata.put(FileMetadata.fileName, fileName);
    }

    @Override
    public String getContentType() {
        return (String) metadata.get(FileMetadata.contentType);
    }

    @Override
    public void setContentType(String contentType) {
        if (contentType == null)
            return;
        metadata.put(FileMetadata.contentType, contentType);
        metadata.put(FileMetadata.isImage, contentType.startsWith("image/"));
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String> getAliases() {
        return (List<String>) metadata.get(FileMetadata.aliases);
    }

    @Override
    public void setAliases(List<String> aliases) {
        metadata.put(FileMetadata.aliases, aliases);
    }

    @Override
    public void addAlias(String alias) {
        List<String> aliases = getAliases();
        if (aliases == null) {
            aliases = new ArrayList<String>(4);
        }
        aliases.add(alias);
        setAliases(aliases);
    }

    @Override
    public String getDescription() {
        return (String) metadata.get(FileMetadata.description);
    }

    @Override
    public void setDescription(String description) {
        metadata.put(FileMetadata.description, description);
    }

    @Override
    public void put(String key, Object value) {
        metadata.put(key, value);
    }

    @Override
    public Object get(String key) {
        return metadata.get(key);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> getMetadata() {
        return metadata.toMap();
    }

    @Override
    public String getUserId() {
        return (String) metadata.get(FileMetadata.userId);
    }

    @Override
    public void setUserId(String userId) {
        metadata.put(FileMetadata.userId, userId);
    }

    @Override
    public String getNetworkId() {
        return (String) metadata.get(FileMetadata.networkId);
    }

    @Override
    public void setNetworkId(String networkId) {
        metadata.put(FileMetadata.networkId, networkId);
    }

    @Override
    public String getGroupId() {
        return (String) metadata.get(FileMetadata.groupId);
    }

    @Override
    public void setGroupId(String groupId) {
        metadata.put(FileMetadata.groupId, groupId);
    }

    @Override
    public String getAppId() {
        return (String) metadata.get(FileMetadata.appId);
    }

    @Override
    public void setAppId(String appId) {
        metadata.put(FileMetadata.appId, appId);
    }

    @Override
    public int getVersion() {
        return (Integer) metadata.get(FileMetadata.appId);
    }

    @Override
    public void setVersion(int version) {
        metadata.put(FileMetadata.version, version);
    }

    @Override
    public boolean isConfirm() {
        return (Boolean) metadata.get(FileMetadata.confirm);
    }

    @Override
    public void setConfirm(boolean confirm) {
        metadata.put(FileMetadata.confirm, confirm);
    }

    public DBObject getDBObject() {
        return metadata;
    }

    private static List<String> keys = Arrays.asList(FileMetadata.fileName, FileMetadata.contentType,
            FileMetadata.aliases, FileMetadata.description, FileMetadata.userId, FileMetadata.networkId,
            FileMetadata.groupId, FileMetadata.appId, FileMetadata.version, FileMetadata.confirm, FileMetadata.md5,
            FileMetadata.uploadDate, FileMetadata.length);

    @Override
    public boolean isExtKey(String key) {
        return !keys.contains(key);
    }

    @Override
    public boolean isImage() {
        if (metadata.get(FileMetadata.isImage) != null)
            return (Boolean) metadata.get(FileMetadata.isImage);
        else if (getContentType() != null)
            return getContentType().startsWith("image/");
        else
            return false;
    }
}
