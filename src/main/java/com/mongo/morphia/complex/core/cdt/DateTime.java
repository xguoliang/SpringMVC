/**
 * Copyright 1993-2010 Kingdee Software (China), Co., Ltd. , Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

package com.mongo.morphia.complex.core.cdt;

import java.io.Serializable;
import java.lang.reflect.Method;

import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;
import org.joda.time.chrono.ISOChronology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * morphia不支持org.joda.time.DateTime，所以包装成这个类
 * 
 * @since 2010-7-29
 * @author guolei_mao
 */
public class DateTime implements Serializable {
    private static final long serialVersionUID = -2866084851662743968L;
    private static Logger logger = LoggerFactory.getLogger(DateTime.class);
    private long milliseconds;
    private String chronologyName;
    private String zoneID;
    private String dateTimeString;// 冗余字段，方便人使用mongo客户端查看

    public DateTime() {
        this(new org.joda.time.DateTime());
    }

    public DateTime(org.joda.time.DateTime dt) {
        this.milliseconds = dt.getMillis();
        this.chronologyName = dt.getChronology().getClass().getName();
        this.zoneID = dt.getChronology().getZone().getID();
        this.dateTimeString = dt.toString();
    }

    public DateTime(long milliseconds) {
        this(new org.joda.time.DateTime(milliseconds));
    }

    public DateTime(long instant, DateTimeZone zone) {
        this(new org.joda.time.DateTime(instant, zone));
    }

    public DateTime(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minuteOfHour, int secondOfMinute,
            int millisOfSecond) {
        this(new org.joda.time.DateTime(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, secondOfMinute,
                millisOfSecond));
    }

    public DateTime(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minuteOfHour, int secondOfMinute,
            int millisOfSecond, DateTimeZone zone) {
        this(new org.joda.time.DateTime(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, secondOfMinute,
                millisOfSecond, zone));
    }

    /**
     * @param milliseconds
     *            the milliseconds to set
     */
    public void setMilliseconds(long milliseconds) {
        this.milliseconds = milliseconds;
    }

    /**
     * @return the milliseconds
     */
    public long getMilliseconds() {
        return milliseconds;
    }

    /**
     * @param chronologyString
     *            the chronologyString to set
     */
    public void setChronologyName(String chronologyName) {
        this.chronologyName = chronologyName;
    }

    /**
     * @return the chronologyString
     */
    public String getChronologyName() {
        return chronologyName;
    }

    public void setChronology(Chronology cy) {
        this.chronologyName = cy.toString();
    }

    public Chronology getChronology() {
        if (chronologyName == null || ISOChronology.class.getName().equals(chronologyName))
            return ISOChronology.getInstance();

        try {
            Class<?> clazz = ISOChronology.class.getClassLoader().loadClass(chronologyName);
            Method m = clazz.getMethod("getInstance", DateTimeZone.class);
            return (Chronology) m.invoke(null, DateTimeZone.getProvider().getZone(zoneID));
        } catch (Exception e) {
            logger.warn("", e);
        }
        return null;
    }

    /**
     * @param zoneID
     *            the zoneID to set
     */
    public void setZoneID(String zoneID) {
        this.zoneID = zoneID;
    }

    /**
     * @return the zoneID
     */
    public String getZoneID() {
        return zoneID;
    }

    public org.joda.time.DateTime get() {
        return new org.joda.time.DateTime(milliseconds, getChronology());
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return get().hashCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return get().equals(obj);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return get().toString();
    }

    /**
     * @return the dateTimeString
     */
    public String getDateTimeString() {
        return dateTimeString;
    }

}
