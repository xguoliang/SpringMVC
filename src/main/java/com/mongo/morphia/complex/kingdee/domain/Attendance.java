package com.mongo.morphia.complex.kingdee.domain;

/**
 * Copyright 1993-2013 Kingdee Software (China), Co., Ltd. , Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */




import java.io.Serializable;

import com.google.code.morphia.annotations.Embedded;

/**
 * 考勤时间配置
 * @since 2013-8-21
 * @author yifeng_xie
 */
@Embedded
public class Attendance implements Serializable{
    private static final long serialVersionUID = 1464279881662869874L;
    
    private String startWorkBegin; //上班开始时间
    private String startWorkEnd; //上班结束时间
    private String endWorkBegin; //下班时开始间
    private String endWorkEnd; //下班结束时间
    private String attendanceName; //考勤名称
    private float middayRest; // 休息时长
    private String range; //考勤有效范围(默认2个小时)
    private float workLong; //工作时长
    
    private String keyTime_1;
    private String keyTime_2;
    private String keyTime_3;
    private String keyTime_4;
    
    private String keyLong_1;
    private String keyLong_2;
    private String keyLong_3;
    private String keyLong_4;
    
    private int lateTime = 10; //迟到十分钟算迟到
    private int earlyLeaveTime = 30; //早退30分钟算早退
    
    /**
     * @return the lateTime
     */
    public int getLateTime() {
        return lateTime;
    }
    /**
     * @param lateTime the lateTime to set
     */
    public void setLateTime(int lateTime) {
        this.lateTime = lateTime;
    }
    /**
     * @return the earlyLeaveTime
     */
    public int getEarlyLeaveTime() {
        return earlyLeaveTime;
    }
    /**
     * @param earlyLeaveTime the earlyLeaveTime to set
     */
    public void setEarlyLeaveTime(int earlyLeaveTime) {
        this.earlyLeaveTime = earlyLeaveTime;
    }
    /**
     * @return the keyTime_1
     */
    public String getKeyTime_1() {
        return keyTime_1;
    }
    /**
     * @param keyTime_1 the keyTime_1 to set
     */
    public void setKeyTime_1(String keyTime_1) {
        this.keyTime_1 = keyTime_1;
    }
    /**
     * @return the keyTime_2
     */
    public String getKeyTime_2() {
        return keyTime_2;
    }
    /**
     * @param keyTime_2 the keyTime_2 to set
     */
    public void setKeyTime_2(String keyTime_2) {
        this.keyTime_2 = keyTime_2;
    }
    /**
     * @return the keyTime_3
     */
    public String getKeyTime_3() {
        return keyTime_3;
    }
    /**
     * @param keyTime_3 the keyTime_3 to set
     */
    public void setKeyTime_3(String keyTime_3) {
        this.keyTime_3 = keyTime_3;
    }
    /**
     * @return the keyTime_4
     */
    public String getKeyTime_4() {
        return keyTime_4;
    }
    /**
     * @param keyTime_4 the keyTime_4 to set
     */
    public void setKeyTime_4(String keyTime_4) {
        this.keyTime_4 = keyTime_4;
    }
    /**
     * @return the keyLong_1
     */
    public String getKeyLong_1() {
        return keyLong_1;
    }
    /**
     * @param keyLong_1 the keyLong_1 to set
     */
    public void setKeyLong_1(String keyLong_1) {
        this.keyLong_1 = keyLong_1;
    }
    /**
     * @return the keyLong_2
     */
    public String getKeyLong_2() {
        return keyLong_2;
    }
    /**
     * @param keyLong_2 the keyLong_2 to set
     */
    public void setKeyLong_2(String keyLong_2) {
        this.keyLong_2 = keyLong_2;
    }
    /**
     * @return the keyLong_3
     */
    public String getKeyLong_3() {
        return keyLong_3;
    }
    /**
     * @param keyLong_3 the keyLong_3 to set
     */
    public void setKeyLong_3(String keyLong_3) {
        this.keyLong_3 = keyLong_3;
    }
    /**
     * @return the keyLong_4
     */
    public String getKeyLong_4() {
        return keyLong_4;
    }
    /**
     * @param keyLong_4 the keyLong_4 to set
     */
    public void setKeyLong_4(String keyLong_4) {
        this.keyLong_4 = keyLong_4;
    }
    /**
     * @return the workLong
     */
    public float getWorkLong() {
        return workLong;
    }
    /**
     * @param workLong the workLong to set
     */
    public void setWorkLong(float workLong) {
        this.workLong = workLong;
    }
    /**
     * @return the startWorkBegin
     */
    public String getStartWorkBegin() {
        return startWorkBegin;
    }
    /**
     * @param startWorkBegin the startWorkBegin to set
     */
    public void setStartWorkBegin(String startWorkBegin) {
        this.startWorkBegin = startWorkBegin;
    }
    /**
     * @return the startWorkEnd
     */
    public String getStartWorkEnd() {
        return startWorkEnd;
    }
    /**
     * @param startWorkEnd the startWorkEnd to set
     */
    public void setStartWorkEnd(String startWorkEnd) {
        this.startWorkEnd = startWorkEnd;
    }
    /**
     * @return the endWorkBegin
     */
    public String getEndWorkBegin() {
        return endWorkBegin;
    }
    /**
     * @param endWorkBegin the endWorkBegin to set
     */
    public void setEndWorkBegin(String endWorkBegin) {
        this.endWorkBegin = endWorkBegin;
    }
    /**
     * @return the endWorkEnd
     */
    public String getEndWorkEnd() {
        return endWorkEnd;
    }
    /**
     * @param endWorkEnd the endWorkEnd to set
     */
    public void setEndWorkEnd(String endWorkEnd) {
        this.endWorkEnd = endWorkEnd;
    }
    /**
     * @return the attendanceName
     */
    public String getAttendanceName() {
        return attendanceName;
    }
    /**
     * @param attendanceName the attendanceName to set
     */
    public void setAttendanceName(String attendanceName) {
        this.attendanceName = attendanceName;
    }
    /**
     * @return the middayRest
     */
    public float getMiddayRest() {
        return middayRest;
    }
    /**
     * @param middayRest the middayRest to set
     */
    public void setMiddayRest(float middayRest) {
        this.middayRest = middayRest;
    }
    
    /**
     * @return the range
     */
    public String getRange() {
        return range;
    }
    /**
     * @param range the range to set
     */
    public void setRange(String range) {
        this.range = range;
    } 
}
