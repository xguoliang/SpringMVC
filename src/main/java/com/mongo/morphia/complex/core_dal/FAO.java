/**
 *
 * 
 * 修订历史记录：
 * 
 * Revision	1.0	 2010-10-21  guolei_mao  创建。
 * 
 */

package com.mongo.morphia.complex.core_dal;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 类说明:
 * 
 * @author <a href="mailto:guolei_mao@kingdee.com">guolei_mao</a>
 * @version 1.0, 2010-10-21
 */
public interface FAO {
    /**
     * 将文件流保存到数据库
     * 
     * @param in
     * @param filename
     * @return 文件id
     */
    public String saveFile(InputStream in, String filename);

    public String saveFile(InputStream in, FileMetadata metadata);

    /**
     * 把文件保存到数据库
     * 
     * @param file
     * @throws IOException
     */
    public String saveFile(File file) throws IOException;

    public String saveFile(File file, FileMetadata metadata) throws IOException;

    public String saveFile(byte[] bytes, String filename);

    public String saveFile(byte[] bytes, FileMetadata metadata);

    /**
     * 获得数据库中文件的inputstream流
     * 
     * @param filename
     * @return
     */
    public InputStream getInputStreamByName(String filename);

    public InputStream getInputStreamById(String id);

    /**
     * 根据文件名删除文件
     * 
     * @param filename
     */
    public void removeFileByName(String filename);

    public void removeFileById(String id);

    public FileMetadata createFileMetadata();

    public void update(String id, FileMetadata metadata);
}
