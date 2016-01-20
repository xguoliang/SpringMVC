package com.mongo.morphia.complex.kingdee.core.entity;

import com.mongo.morphia.complex.kingdee.core.server.AbstractDynaBObject;




/**
 * 
 * @since 2012-7-2
 * @author guolei_mao
 */
public class LongQueryConfig extends AbstractDynaBObject {
    private static final long serialVersionUID = -2503276029273473753L;
    private boolean open;
    private long time = 1000;// ms
    private long interval = 60;// s

    public LongQueryConfig() {

    }

    public LongQueryConfig(boolean open) {
        this.open = open;
    }

    public LongQueryConfig(boolean open, long time, long interval) {
        this.open = open;
        this.time = time;
        this.interval = interval;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isOpen() {
        return open;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public long getInterval() {
        if (interval <= 0)
            return 60;
        return interval;
    }

    @Override
    public int hashCode() {
        return Boolean.valueOf(open).hashCode() + Long.valueOf(time).hashCode() + Long.valueOf(interval).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LongQueryConfig))
            return false;
        if (this == obj)
            return true;
        LongQueryConfig lqc = (LongQueryConfig) obj;
        return this.open == lqc.open && this.time == lqc.time && this.interval == lqc.interval;
    }

    @Override
    public String toString() {
        return "LongQueryConfig{open:" + open + ",time:" + time + ",interval:" + interval + "}";
    }
}
