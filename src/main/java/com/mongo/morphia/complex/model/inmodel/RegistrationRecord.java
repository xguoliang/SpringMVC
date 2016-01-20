package com.mongo.morphia.complex.model.inmodel;

/**
 * Copyright 1993-2012 Kingdee Software (China), Co., Ltd. , Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */



import java.util.Date;

/**
 * 
 * @since 2012-12-4
 * @author daqiang_kang
 */
public class RegistrationRecord {

    private String application;

    private boolean originated;

    private Date registrationTime;

    public RegistrationRecord() {
    }

    /**
     * @return the application
     */
    public String getApplication() {
        return application;
    }

    /**
     * @return the registrationTime
     */
    public Date getRegistrationTime() {
        return registrationTime;
    }

    /**
     * @return the originated
     */
    public boolean isOriginated() {
        return originated;
    }

    /**
     * @param application
     *            the application to set
     */
    public void setApplication(String application) {
        this.application = application;
    }

    /**
     * @param originated
     *            the originated to set
     */
    public void setOriginated(boolean originated) {
        this.originated = originated;
    }

    /**
     * @param registrationTime
     *            the registrationTime to set
     */
    public void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
    }
}
