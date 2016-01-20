package com.mongo.morphia.complex.kingdee.core.Odao;

/**
 * Copyright 1993-2012 Kingdee Software (China), Co., Ltd. , Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */



import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





import com.mongo.morphia.complex.kingdee.core.Odao.inter.LongQueryConfigDAO;
import com.mongo.morphia.complex.kingdee.core.dao.BaseDAOImpl;
import com.mongo.morphia.complex.kingdee.core.entity.LongQueryConfig;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

/**
 * 
 * @since 2012-7-3
 * @author guolei_mao
 */
public class LongQueryConfigDAOImpl extends BaseDAOImpl<LongQueryConfig> implements LongQueryConfigDAO {
    private static Logger logger = LoggerFactory.getLogger(LongQueryConfigDAOImpl.class);
    private static Date lastTime = new Date();
    private static LongQueryConfig config = new LongQueryConfig(false);;

    @Override
    public LongQueryConfig getLongQueryConfig() {
//        Date now = new Date();
//        if ((now.getTime() - lastTime.getTime()) / 1000 > config.getInterval()) {
//            try {
//                MongoDAOImpl<LongQueryConfig> dao = (MongoDAOImpl<LongQueryConfig>) super.getDAO();
//                DBCollection dbc = dao.getMongoHelper().getCollection();
//                DBObject dbo = dbc.findOne();
//                if (dbo != null) {
//                    LongQueryConfig lqc = new LongQueryConfig();
//                    Object o = dbo.get("open");
//                    if (o != null)
//                        lqc.setOpen((Boolean) o);
//                    o = dbo.get("time");
//                    if (o != null)
//                        lqc.setTime(((Number) o).longValue());
//                    o = dbo.get("interval");
//                    if (o != null)
//                        lqc.setInterval(((Number) o).longValue());
//                    if (!config.equals(lqc)) {
//                        logger.warn(lqc.toString());
//                    }
//                    config = lqc;
//                }
//
//                lastTime = now;
//            } catch (Throwable t) {
//                logger.warn(t.getMessage(), t);
//            }
//        }
        return config;
    }
}
