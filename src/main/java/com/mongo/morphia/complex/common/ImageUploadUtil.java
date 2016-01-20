package com.mongo.morphia.complex.common;


import java.util.Arrays;
import java.util.List;


public class ImageUploadUtil {
    
    //private static final Logger logger = LoggerFactory.getLogger(ImageUploadUtil.class);
    
    /**
     * 限制文件上传大小为1M以下
     */
    public static final long FILE_SIZE_MAX = 2* 1024 * 1024;
    
    public static final List<String> SUPPORT_SUFFIX = Arrays.asList(new String[] {"jpg","jpeg","jpe","gif","png"});
    
        
    
}
