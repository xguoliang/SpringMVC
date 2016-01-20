/**
 * Copyright 1993-2010 Kingdee , Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

package com.mongo.morphia.complex.core_dal;

import java.util.HashSet;
import java.util.Set;

import com.google.common.base.Throwables;
import com.mongo.morphia.complex.core.BObject;
import com.mongo.morphia.complex.core.Context;
import com.mongo.morphia.complex.core.ContextToken;
import com.mongo.morphia.complex.core.impl.ContextImpl;
import com.mongo.morphia.complex.core.impl.SerializedContextToken;
import com.mongo.morphia.complex.core_dal.datasource.DataSourceConfigManager;
import com.mongo.morphia.complex.core_dal.datasource.PersistenceInfo;
import com.mongo.morphia.complex.core_dal.datasource.PersistenceUnitInfo;
import com.mongo.morphia.complex.core_dal.exceptions.DataSourceLookupFailureException;
import com.mongo.morphia.complex.core_dal.impl.morphia.MongoDAOImpl;

//import org.osgi.framework.Bundle;
//import org.osgi.framework.FrameworkUtil;

/**
 * 
 * @since 2010-6-9
 * @author guolei_mao
 */
public class DAOFactory {
    public static <E extends BObject> DAO<E> getInstance(ContextToken ct, Class<E> entityClass) {
        return new com.mongo.morphia.complex.core_dal.impl.morphia.MongoDAOImpl<E>(ct, entityClass);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <E extends BObject> DAO<E> getInstance(ContextToken ct, String entityName) {
        Class entityClass = null;
        try {
            entityClass = Class.forName(entityName, true, Thread.currentThread().getContextClassLoader());
        } catch (ClassNotFoundException e) {
            Throwables.propagate(e);
        }

        return new MongoDAOImpl<E>(ct, entityClass);
    }

    public static <E extends BObject> boolean isMongo(ContextToken ct, String entityName) {
        PersistenceInfo pi = DataSourceConfigManager.getByContextToken(ct);
        String persistenceUnit = WARListener.getPersistenceUnit(entityName);

        if (persistenceUnit == null) {
            String msg = entityName
                    + "'s PersistenceUnit is null. Please check "
                    + entityName
                    + "s PersistenceUnit and Persistence-Unit in MANIFEST.MF does or not define in DataSource config file.";
            throw new DataSourceLookupFailureException(msg);
        }

        PersistenceUnitInfo pui = pi.getByName(persistenceUnit);
        return pui.getMongoUrl() != null;
    }

    // 临时实现
    public static Set<String> getTenantIds() {
        return new HashSet<String>(DataSourceConfigManager.getPersistences().keySet());
    }

    /**
     * 获得一个与租户无关的DAO访问对象，适用于与租户无关的全局数据的存取。
     * 
     * @param <E>
     * @param entity
     * @return
     */
    public static <E extends BObject> DAO<E> getInstance(Class<E> entity) {
        // TODO 暂时使用system与global表示租户无关
        Context ctx = new ContextImpl("system", "global");
        ContextToken token = new SerializedContextToken(ctx);
        DAO<E> dao = DAOFactory.getInstance(token, entity);
        dao.setFilterByTenant(false);
        return dao;
    }
}
