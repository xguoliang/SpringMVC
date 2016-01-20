package com.mongo.morphia.complex.model.inmodel;

/**
 * Copyright 1993-2012 Kingdee Software (China), Co., Ltd. , Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */
 



import java.io.Serializable;
import java.util.Map;

import com.google.code.morphia.annotations.Embedded;

/**
 *
 * @since 2012-4-6
 * @author maohui_jiang
 */
@Embedded
public class AccountStatus implements Serializable{
    private static final long serialVersionUID = -1052583266703460610L;
    
    /**是否发送日摘要状态: <br/>
     * true:发<br/>
     * false:不发<br/>
     * 默认为true<br/>
     */
    private boolean dayDigest = true; 
    
    /**是否发送周摘要状态: <br/>
     * true:发<br/>
     * false:不发<br/>
     * 默认为true<br/>
     */
    private boolean weekDigest = true;
    
    /**
     * 验证相应类别邮件用户是否设置接收
     */
    private Map<String, Object> extMap;
    
    /**
     * @return the extMap
     */
    public Map<String, Object> getExtMap() {
        return extMap;
    }
    /**
     * @param extMap the extMap to set
     */
    public void setExtMap(Map<String, Object> extMap) {
        this.extMap = extMap;
    }
    /**
     * @return the dayDigest
     */
    public boolean isDayDigest() {
        return dayDigest;
    }
    /**
     * @param dayDigest the dayDigest to set
     */
    public void setDayDigest(boolean dayDigest) {
        this.dayDigest = dayDigest;
    }
    /**
     * @return the weekDigest
     */
    public boolean isWeekDigest() {
        return weekDigest;
    }
    /**
     * @param weekDigest the weekDigest to set
     */
    public void setWeekDigest(boolean weekDigest) {
        this.weekDigest = weekDigest;
    }
}
