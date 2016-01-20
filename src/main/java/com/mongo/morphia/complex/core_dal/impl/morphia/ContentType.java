/**
 *
 * 
 * 修订历史记录：
 * 
 * Revision	1.0	 2010-10-22  guolei_mao  创建。
 * 
 */

package com.mongo.morphia.complex.core_dal.impl.morphia;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类说明:
 * 
 * @author <a href="mailto:guolei_mao@kingdee.com">guolei_mao</a>
 * @version 1.0, 2010-10-22
 */
public class ContentType {
    private static Logger logger = LoggerFactory.getLogger(ContentType.class);
    private static Map<String, String> types = new HashMap<String, String>();

    static {
        // text
        types.put("html", "text/html");
        types.put("htm", "text/html");
        types.put("jsp", "text/html");
        types.put("txt", "text/plain");
        types.put("css", "text/css");
        types.put("js", "text/javascript");
        types.put("xml", "text/xml");

        // image
        types.put("gif", "image/gif");
        types.put("ief", "image/ief");
        types.put("jpeg", "image/jpeg");
        types.put("jpg", "image/jpeg");
        types.put("jpe", "image/jpeg");
        types.put("png", "image/png");
        types.put("tiff", "image/tiff");
        types.put("bmp", "image/x-ms-bmp");
        types.put("rgb", "image/rgb");
        types.put("g3f", "image/g3fax");
        types.put("xwd", "image/x-xwindowdump");
        types.put("pict", "image/x-pict");
        types.put("ppm", "image/x-portable-pixmap");
        types.put("pgm", "image/x-portable-graymap");
        types.put("pnm", "image/x-portable-anymap");
        types.put("cgm", "image/cgm");
        types.put("dwg", "image/vnd.dwg");

        // audio
        types.put("au", "audio/basic");
        types.put("snd", "audio/x-aiff");
        types.put("wav", "audio/x-wav");
        types.put("mpa", "audio/x-mpeg");
        types.put("mpega", "audio/x-mpeg");
        types.put("mp2a", "audio/x-mpeg-2");
        types.put("mpa2", "audio/x-mpeg-2");

        // video
        types.put("mpeg", "video/mpeg");
        types.put("mpg", "video/mpeg");
        types.put("mpe", "video/mpeg");
        types.put("mpv2", "video/mpeg-2");
        types.put("mp2v", "video/mpeg-2");
        types.put("qt", "video/quicktime");
        types.put("mov", "video/quicktime");
        types.put("avi", "video/x-msvideo");
        types.put("movie", "video/x-sgi-movie");

        // application
        types.put("ra", "application/x-pn-realaudio");
        types.put("ram", "application/x-pn-realaudio");

        types.put("rtf", "application/rtf");
        types.put("pdf", "application/pdf");
        types.put("gtar", "application/x-gtar");
        types.put("tar", "application/x-tar");
        types.put("zip", "application/zip");
        types.put("bin", "application/octet-stream");
        types.put("exe", "application/octet-stream");
        types.put("sh", "application/x-sh");
        types.put("csh", "application/x-csh");
        types.put("pl", "application/x-perl");
        types.put("ppt", "application/ms-powerpoint");
        types.put("exe", "application/octet-stream");
        types.put("doc", "application/msword");

    }

    public static String getType(String suffix) {
        if (suffix.startsWith("."))
            suffix = suffix.substring(1);

        return types.get(suffix);
    }

    public static String getType(File file) {
        FileInputStream is = null;
        try {
            is = new FileInputStream(file);
            MimeTypes mts = MimeTypes.getDefaultMimeTypes();
            MimeType mt = mts.getMimeType(is);

            if (mt != null)
                return mt.getName();
        } catch (Exception e) {
            logger.debug(e.getMessage(), e);
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {
                logger.debug(e.getMessage(), e);
            }
        }
        return null;
    }

    public static List<String> getSuffix(File file) {
        List<String> sfs = new ArrayList<String>();
        FileInputStream is = null;
        try {
            is = new FileInputStream(file);
            MimeTypes mts = MimeTypes.getDefaultMimeTypes();
            MimeType mt = mts.getMimeType(is);

            if (mt != null) {
                Map<String, MimeType> extensions = mts.getPatterns().getExtensions();
                for (Iterator<Map.Entry<String, MimeType>> iterator = extensions.entrySet().iterator(); iterator
                        .hasNext();) {
                    Map.Entry<String, MimeType> entry = iterator.next();
                    if (entry.getValue().equals(mt))
                        sfs.add(entry.getKey());
                }
            }
        } catch (Exception e) {
            logger.debug(e.getMessage(), e);
        } finally {
            try {
                is.reset();
            } catch (IOException e) {
                logger.debug(e.getMessage(), e);
            }
        }
        return sfs;
    }

    public static PushbackInputStream getPushbackInputStream(InputStream is) {
        return new PushbackInputStream(is, MimeTypes.getDefaultMimeTypes().getMinLength());
    }

    public static String getType(PushbackInputStream pis) {
        try {
            MimeTypes mts = MimeTypes.getDefaultMimeTypes();
            byte[] header = readMagicHeader(pis);
            MimeType mt = mts.getMimeType(header);
            pis.unread(header);

            if (mt != null)
                return mt.getName();
        } catch (Exception e) {
            logger.debug(e.getMessage(), e);
        }
        return null;
    }

    private static byte[] readMagicHeader(InputStream stream) throws IOException {
        if (stream == null) {
            throw new IllegalArgumentException("InputStream is missing");
        }

        byte[] bytes = new byte[MimeTypes.getDefaultMimeTypes().getMinLength()];
        int totalRead = 0;

        int lastRead = stream.read(bytes);
        while (lastRead != -1) {
            totalRead += lastRead;
            if (totalRead == bytes.length) {
                return bytes;
            }
            lastRead = stream.read(bytes, totalRead, bytes.length - totalRead);
        }

        byte[] shorter = new byte[totalRead];
        System.arraycopy(bytes, 0, shorter, 0, totalRead);
        return shorter;
    }

    public static List<String> getSuffix(PushbackInputStream pis) {
        List<String> sfs = new ArrayList<String>();
        try {
            MimeTypes mts = MimeTypes.getDefaultMimeTypes();
            byte[] header = readMagicHeader(pis);
            MimeType mt = mts.getMimeType(header);
            pis.unread(header);

            if (mt != null) {
                Map<String, MimeType> extensions = mts.getPatterns().getExtensions();
                for (Iterator<Map.Entry<String, MimeType>> iterator = extensions.entrySet().iterator(); iterator
                        .hasNext();) {
                    Map.Entry<String, MimeType> entry = iterator.next();
                    if (entry.getValue().equals(mt))
                        sfs.add(entry.getKey());
                }
            }
        } catch (Exception e) {
            logger.debug(e.getMessage(), e);
        }
        return sfs;
    }
}
