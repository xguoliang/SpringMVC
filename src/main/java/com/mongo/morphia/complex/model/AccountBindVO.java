package com.mongo.morphia.complex.model;

/**
 * @since 2014-02-28 账户绑定的状态
 * @author lian_lin
 *
 */
public class AccountBindVO {
	
	 
	private String accountName;
	private String accountType;
	private boolean needSetPassword;
	private String status; // UN_BIND,BINDING,BINDED

	public AccountBindVO() {
		super();
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isNeedSetPassword() {
		return needSetPassword;
	}

	public void setNeedSetPassword(boolean needSetPassword) {
		this.needSetPassword = needSetPassword;
	}
}
