package com.mongo.morphia.complex.common.antisamy;

/**
 * Copyright 1993-2011 Kingdee Software (China), Co., Ltd. , Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;
import org.springframework.web.util.HtmlUtils;


/**
 * 基于 OWASP 的html/css/js安全文本处理器
 * @since 2011-10-11
 * @author youxw
 */
public class SafeText {
    static Policy safePolicy;
    static Policy clearPolicy;
    
    static{
        try {
            safePolicy = Policy.getInstance(SafeText.class.getResource("antisamy.xml"));
        } catch (PolicyException e) {
            e.printStackTrace();
        }
        try {
            clearPolicy = Policy.getInstance(SafeText.class.getResource("antisamy-clear.xml"));
        } catch (PolicyException e) {
            e.printStackTrace();
        }
    }
    public static enum SafeLevel { NOHTML, TINY, CLEAR };
    
    
    public static SafeText getInstance(SafeLevel level){
        return new SafeText(level);
    }
    
    private SafeLevel level;
    private AntiSamy as = new AntiSamy();
    
    private SafeText(SafeLevel l){
        level = l;
    }
    
    public String getText(String unsafe){
        if(SafeLevel.TINY == level){
            try {
                CleanResults cr = as.scan(unsafe, safePolicy, AntiSamy.SAX);
                return cr.getCleanHTML();
            } catch (ScanException e) {
                e.printStackTrace();
            } catch (PolicyException e) {
                e.printStackTrace();
            }
        }else if(SafeLevel.CLEAR == level){
            try {
                CleanResults cr = as.scan(unsafe, clearPolicy, AntiSamy.SAX);
                return cr.getCleanHTML();
            } catch (ScanException e) {
                e.printStackTrace();
            } catch (PolicyException e) {
                e.printStackTrace();
            }
        }
        return HtmlUtils.htmlEscape(unsafe);
    }

    public static void main(String[] args){
        String unsafeText = "欢迎来<vedio onerror='test'>到\\'\"()&%1<ScRiPt >prompt(952437)</ScRiPt>小<img onerror='test'>组";
        SafeText noHtml = SafeText.getInstance(SafeLevel.NOHTML);
        System.out.println(noHtml.getText(unsafeText));
        System.out.println(SafeText.getInstance(SafeLevel.TINY).getText(unsafeText));
        System.out.println(SafeText.getInstance(SafeLevel.CLEAR).getText(unsafeText));
    }
}
