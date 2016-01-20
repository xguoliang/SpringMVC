package com.mongo.morphia.complex.model.inmodel;

import com.mongo.morphia.complex.Enum.NetworkSubType;
import com.mongo.morphia.complex.Enum.NetworkType;
import com.mongo.morphia.complex.core.impl.AbstractBObject;




/**
 * 网络信息，仅用于SESSION中
 * 
 * @since 2010-11-3
 * @author xichu_yu
 */
public class NetworkInfo extends AbstractBObject {
    private static final long serialVersionUID = 8816601621801785734L;

    private String name;
    private NetworkType type;
    private NetworkSubType subType;//lian_lin 2013-11-26 判断当前网络是否团队时用到
    private String subDomainName;
    private String domainId;
    //private String domainName;
    private String domainIdentity;
    private long activeUserCount = 0;

    public NetworkInfo() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NetworkType getType() {
        return type;
    }

    public void setType(NetworkType type) {
        this.type = type;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

//    public String getDomainName() {
//        return domainName;
//    }
//
//    public void setDomainName(String domainName) {
//        this.domainName = domainName;
//    }

    public void setDomainIdentity(String domainIdentity) {
        this.domainIdentity = domainIdentity;
    }

    public String getDomainIdentity() {
        return domainIdentity;
    }

    public void setSubDomainName(String subDomainName) {
        this.subDomainName = subDomainName;
    }

    public String getSubDomainName() {
        return subDomainName;
    }

    public void setActiveUserCount(long activeUserCount) {
        this.activeUserCount = activeUserCount;
    }

    public long getActiveUserCount() {
        return activeUserCount;
    }

	public NetworkSubType getSubType() {
		return subType;
	}

	public void setSubType(NetworkSubType subType) {
		this.subType = subType;
	}
}
