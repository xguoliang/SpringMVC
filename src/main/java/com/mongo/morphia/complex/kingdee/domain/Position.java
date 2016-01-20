package com.mongo.morphia.complex.kingdee.domain;

import java.io.Serializable;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Transient;

/**
 * 考勤地点配置
 * @since 2013-8-22
 * @author yifeng_xie
 */
@Embedded
public class Position implements Serializable{
    private static final long serialVersionUID = 2464279881662869874L;
    
    private String positionName;//考勤地点名称(来源高德地图)
    private double lng; //二维地理位置 经度
    private double lat; //二维地理位置 纬度
    private int offset; //偏移量 (默认200)
    private String address; //详细地址
    private String alias; //别名
    
    @Transient
    private String id;
    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * @return the positionName
     */
    public String getPositionName() {
        return positionName;
    }
    /**
     * @param positionName the positionName to set
     */
    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
  
    /**
     * @return the lng
     */
    public double getLng() {
        return lng;
    }
    /**
     * @param lng the lng to set
     */
    public void setLng(double lng) {
        this.lng = lng;
    }
    /**
     * @return the lat
     */
    public double getLat() {
        return lat;
    }
    /**
     * @param lat the lat to set
     */
    public void setLat(double lat) {
        this.lat = lat;
    }
    /**
     * @return the offset
     */
    public int getOffset() {
        return offset;
    }
    /**
     * @param offset the offset to set
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

}
