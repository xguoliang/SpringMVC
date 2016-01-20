package com.mongo.morphia.complex.kingdee.common;

/**
 * Copyright 1993-2010 Kingdee , Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.mongo.morphia.complex.kingdee.core.dao.factory.DAOConstants;

/**
 * 
 * @since 2010-5-28
 * @author guolei_mao
 */
public class PersistenceInfo implements Serializable{
    private static final long serialVersionUID = -7795766789040037461L;

    private String id;
	private List<String> tenantIDs = new ArrayList<String>();
	private List<PersistenceUnitInfo> units = new ArrayList<PersistenceUnitInfo>();
	
	public PersistenceInfo() {
	    
	}
	
	public PersistenceInfo(String id) {
       this.setID(id); 
    }

	public List<String> getTenantIDs() {
		return tenantIDs;
	}

	public void setTenantIDs(List<String> tenantIDs) {
		if (tenantIDs == null)
			tenantIDs = new ArrayList<String>();
		this.tenantIDs = tenantIDs;
	}

	public List<PersistenceUnitInfo> getPersistenceUnits() {
		return units;
	}

	public void addPersistenceUnit(PersistenceUnitInfo unit) {
		units.add(unit);
	}
	
	public void removePersistenceUnit(PersistenceUnitInfo unit) {
		units.remove(unit);
	}

    public PersistenceUnitInfo getByName(String name) {
        if (name == null)
            return null;

        for (int i = 0; i < units.size(); i++) {
            PersistenceUnitInfo unit = units.get(i);
            if (name.equals(unit.getPersistenceUnitName())) {
                return unit;
            }
        }
        return null;
    }
	
    public PersistenceUnitInfo getFileUnit() {
        PersistenceUnitInfo fileUnit = getByName(DAOConstants.MONGO_FILE);
        if (fileUnit != null) {
            return fileUnit;
        }

        for (int i = 0; i < units.size(); i++) {
            PersistenceUnitInfo unit = units.get(i);
            if (unit.getMongoUrl() != null)
                return unit;
        }

        return null;
    }
	
    public void setID(String id) {
        this.id = id;
    }

    public String getID() {
        return id;
    }
}
