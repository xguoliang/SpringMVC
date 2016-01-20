package com.mongo.morphia.complex.kingdee.core.dao;



import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 
 * @since 2010-9-20
 * @author guolei_mao
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class DAOListenerManager {
    private static Logger logger = LoggerFactory.getLogger(DAOListenerManager.class);
    private static Map<String, Class<? extends DAOListener>> idToClass = new HashMap<String, Class<? extends DAOListener>>();

    public static synchronized void register(String id, Class<? extends DAOListener> listenerImpl) {
        idToClass.put(id, listenerImpl);
        logger.info("register DAOListener: id=" + id + " , className=" + listenerImpl.getName());
    }

    public static synchronized void register(String id, DAOListener listener) {
        register(id, (Class<DAOListener>) listener.getClass());
    }

    public static synchronized void unregister(String id) {
        Class<? extends DAOListener> listenerImpl = idToClass.remove(id);
        logger.info("unregister DAOListener: id=" + id + " , className="
                + (listenerImpl == null ? "" : listenerImpl.getName()));
    }

    public static synchronized void unregister(Class<? extends DAOListener> listenerImpl) {
        for (Iterator<Map.Entry<String, Class<? extends DAOListener>>> iterator = idToClass.entrySet().iterator(); iterator
                .hasNext();) {
            Map.Entry<String, Class<? extends DAOListener>> entry = (Map.Entry<String, Class<? extends DAOListener>>) iterator
                    .next();
            if (entry.getValue().equals(listenerImpl)) {
                iterator.remove();
                logger.info("unregister DAOListener: className=" + listenerImpl.getName());
            }
        }
    }

    public static synchronized void unregister(DAOListener listener) {
        unregister((Class<DAOListener>) listener.getClass());
    }

    public static synchronized Class<? extends DAOListener> getById(String id) {
        return idToClass.get(id);
    }

    public static synchronized void register(ServletContext sc) {
        try {
            WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
            scanBean(wac, "daoListeners");
            scanBean(wac, "appDAOListeners");
        } catch (Throwable t) {
            logger.error("", t);
        }
    }

    private static void scanBean(WebApplicationContext wac, String beanID) {
        if (!wac.containsBean(beanID)) {
            logger.debug("Can't find bean: " + beanID);
            return;
        }

        Map sourceMap = null;
        sourceMap = (Map) wac.getBean(beanID);

        if (sourceMap != null) {
            for (Iterator<Map.Entry<String, String>> iterator = sourceMap.entrySet().iterator(); iterator.hasNext();) {
                Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
                try {
                    Class c = Class.forName(entry.getValue(), true, Thread.currentThread().getContextClassLoader());
                    register(entry.getKey(), c);
                } catch (ClassNotFoundException e) {
                    logger.error("", e);
                }
            }
        }
    }
}
