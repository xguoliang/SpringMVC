/**
 *
 * 
 * 修订历史记录：
 * 
 * Revision	1.0	 2010-11-24  guolei_mao  创建。
 * 
 */

package com.mongo.morphia.complex.core_dal;

import java.util.List;
import java.util.Map;

/**
 * 类说明:
 * 
 * @author <a href="mailto:guolei_mao@kingdee.com">guolei_mao</a>
 * @version 1.0, 2010-11-24
 */
public interface FileMetadata {
    String fileName = "filename";// 与GridFSFile中一致
    String contentType = "contentType";
    String length = "length";
    String uploadDate = "uploadDate";
    String md5 = "md5";
    String aliases = "aliases";

    String userId = "userId";
    String networkId = "networkId";
    String groupId = "groupId";
    String appId = "appId";
    String description = "description";
    String version = "version";
    String confirm = "confirm";
    String isImage = "isImage";

    String getFileName();

    void setFileName(String fileName);

    String getUserId();

    void setUserId(String userId);

    String getNetworkId();

    void setNetworkId(String networkId);

    String getGroupId();

    void setGroupId(String groupId);

    String getAppId();

    void setAppId(String appId);

    String getContentType();

    void setContentType(String contentType);

    List<String> getAliases();

    void setAliases(List<String> aliases);

    void addAlias(String alias);

    String getDescription();

    void setDescription(String description);

    int getVersion();

    void setVersion(int version);
    
    boolean isConfirm();

    void setConfirm(boolean confirm);

    void put(String key, Object value);

    Object get(String key);

    Map<String, Object> getMetadata();
    
    boolean isExtKey(String key);
    
    boolean isImage();
}
