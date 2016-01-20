package com.mongo.morphia.complex.kingdee.domain;

/**
 * Copyright 1993-2013 Kingdee Software (China), Co., Ltd. , Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */




import java.io.Serializable;

import com.google.code.morphia.annotations.Embedded;

/**
 *
 * @since 2013-9-12
 * @author yifeng_xie
 * 考勤微博分享内容Bean
 */
@Embedded
public class AttendanceMBShare implements Serializable{
    private static final long serialVersionUID = 1464379881662869874L;
    
    private String normalMBShare; //正常考勤微博分享内容    
    private String rankingMBShare; //排名考勤微博分享内容
    private String nightMBShare; //凌晨下班微博分享内容
 
    private String StartWorkMBShare; //上班考勤微博分享内容
    private String endWorkMBShare; //下班考勤微博分享内容
    
    private String rankingTip; //排名考勤提示
    
    /**
     * @return the rankingTip
     */
    public String getRankingTip() {
        return rankingTip;
    }
    /**
     * @param rankingTip the rankingTip to set
     */
    public void setRankingTip(String rankingTip) {
        this.rankingTip = rankingTip;
    }
    /**
     * @return the nightTip
     */
    public String getNightTip() {
        return nightTip;
    }
    /**
     * @param nightTip the nightTip to set
     */
    public void setNightTip(String nightTip) {
        this.nightTip = nightTip;
    }
    private String nightTip; //凌晨考勤提示
    
    /**
     * @return the nightMBShare
     */
    public String getNightMBShare() {
        return nightMBShare;
    }
    /**
     * @param nightMBShare the nightMBShare to set
     */
    public void setNightMBShare(String nightMBShare) {
        this.nightMBShare = nightMBShare;
    }
    
    /**
     * @return the normalMBShare
     */
    public String getNormalMBShare() {
        return normalMBShare;
    }
    /**
     * @param normalMBShare the normalMBShare to set
     */
    public void setNormalMBShare(String normalMBShare) {
        this.normalMBShare = normalMBShare;
    }
    /**
     * @return the rankingMBShare
     */
    public String getRankingMBShare() {
        return rankingMBShare;
    }
    /**
     * @param rankingMBShare the rankingMBShare to set
     */
    public void setRankingMBShare(String rankingMBShare) {
        this.rankingMBShare = rankingMBShare;
    }
    /**
     * @return the startWorkMBShare
     */
    public String getStartWorkMBShare() {
        return StartWorkMBShare;
    }
    /**
     * @param startWorkMBShare the startWorkMBShare to set
     */
    public void setStartWorkMBShare(String startWorkMBShare) {
        StartWorkMBShare = startWorkMBShare;
    }
    /**
     * @return the endWorkMBShare
     */
    public String getEndWorkMBShare() {
        return endWorkMBShare;
    }
    /**
     * @param endWorkMBShare the endWorkMBShare to set
     */
    public void setEndWorkMBShare(String endWorkMBShare) {
        this.endWorkMBShare = endWorkMBShare;
    }
}
