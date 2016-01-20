package com.mongo.morphia.complex.kingdee.domain;



import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.google.code.morphia.annotations.Embedded;

@Embedded
public class WifiEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3226035050100196014L;

	private String ssid;
	private String bssid;
	private Date lastUsedDate;
	private int type; // 0表示已关联,1表示未关联
	private boolean hasNotify = false;

	public WifiEntity() {
		super();
	}
	
	public WifiEntity(String ssid, String bssid) {
		this.bssid = bssid;
		this.ssid = ssid;
	}

	public String getSsid() {
		return ssid;
	}

	public void setSsid(String ssid) {
		this.ssid = ssid;
	}

	public String getBssid() {
		return bssid;
	}

	public void setBssid(String bssid) {
		this.bssid = bssid;
	}
	
	public Date getLastUsedDate() {
		return lastUsedDate;
	}
	
	public void setLastUsedDate(Date lastUsedDate) {
		this.lastUsedDate = lastUsedDate;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isHasNotify() {
		return hasNotify;
	}

	public void setHasNotify(boolean hasNotify) {
		this.hasNotify = hasNotify;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof WifiEntity) {
			WifiEntity other = (WifiEntity) obj;
			if (StringUtils.isNotBlank(this.bssid) && this.bssid.equals(other.getBssid())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

}
