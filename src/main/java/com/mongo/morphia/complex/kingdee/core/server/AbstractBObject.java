package com.mongo.morphia.complex.kingdee.core.server;




import java.io.Serializable;



/**
 * 
 * @since 2010-6-10
 * @author pl
 */
public abstract class AbstractBObject implements BObject, Serializable {

	private static final long serialVersionUID = 1926071137525387427L;

	@com.google.code.morphia.annotations.Id
	private String id;

	private String tenantId;

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getTenantId() {
		return tenantId;
	}

	@Override
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
}
