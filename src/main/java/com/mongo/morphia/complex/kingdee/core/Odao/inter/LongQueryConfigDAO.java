package com.mongo.morphia.complex.kingdee.core.Odao.inter;

import com.mongo.morphia.complex.kingdee.core.dao.BaseDAO;
import com.mongo.morphia.complex.kingdee.core.entity.LongQueryConfig;


/**
 * 
 * @since 2012-7-3
 * @author guolei_mao
 */
public interface LongQueryConfigDAO extends BaseDAO<LongQueryConfig> {
    LongQueryConfig getLongQueryConfig();
}
