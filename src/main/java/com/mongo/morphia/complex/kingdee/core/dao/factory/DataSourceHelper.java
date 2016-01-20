package com.mongo.morphia.complex.kingdee.core.dao.factory;

import com.mongo.morphia.complex.core.ContextToken;
import com.mongo.morphia.complex.kingdee.WARListener;
import com.mongo.morphia.complex.kingdee.common.PersistenceInfo;
import com.mongo.morphia.complex.kingdee.common.PersistenceUnitInfo;

public class DataSourceHelper {
	public static DataSource getDataSource(ContextToken ct, String persistenceUnit) {
		PersistenceInfo pi = DataSourceConfigManager.getByContextToken(ct);
		PersistenceUnitInfo pui = pi.getByName(persistenceUnit);
		return pui.getDataSource();
	}
	
	/*
	 * @param beanType 实体类型
	 */
	public static DataSource getDataSource(ContextToken ct, Class<?> beanType) {
		//特殊处理下
		if( beanType.getName().equals("com.mongo.morphia.complex.kingdee.domain.AttendanceSet")){
			return getDataSource(ct, "att");
		}
		return getDataSource(ct, WARListener.getPersistenceUnit(beanType));
	}

	/*
	 * @param bean 业务bean
	 */
	public static DataSource getDataSource(ContextToken ct, Object bean) {
		return getDataSource(ct, bean.getClass());
	}
}
