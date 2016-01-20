/**
 * Copyright 1993-2011 Kingdee Software (China), Co., Ltd. , Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

package com.mongo.morphia.complex.common;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

/**
 *
 * @since 2011-12-9
 * @author yuxin_fu
 */
public class EMailUtils {
    
    /**EMail 正则表达式*/
    private static final Pattern P_MAIL = Pattern.compile("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$");
    
    /**
     * 验证邮件是否合法
     * @param email
     * @return
     */
    public static boolean isLegal(String email){
        if(!StringUtils.hasText(email)){
            return false;
        }
        Matcher m = P_MAIL.matcher(email);
        return m.matches();
    }
    
    public static String getDomain(String email){
        if(isLegal(email))
            return email.substring(email.indexOf("@") + 1);
        return null;
    }
}
