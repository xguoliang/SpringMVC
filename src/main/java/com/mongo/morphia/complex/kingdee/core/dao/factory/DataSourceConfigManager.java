package com.mongo.morphia.complex.kingdee.core.dao.factory;

import java.io.File;
import java.io.FilenameFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.ServletContext;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.mongo.morphia.complex.core.Context;
import com.mongo.morphia.complex.core.ContextToken;
import com.mongo.morphia.complex.kingdee.common.PersistenceInfo;
import com.mongo.morphia.complex.kingdee.common.PersistenceUnitInfo;
import com.mongo.morphia.complex.kingdee.common.consts.PersistenceUnitConstants;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;

/**
 * 
 * @since 2010-5-28
 * @author guolei_mao
 */
public class DataSourceConfigManager {
    private static Logger LOGGER = LoggerFactory.getLogger(DataSourceConfigManager.class);
    private static ConcurrentMap<String, PersistenceInfo> persistences = new ConcurrentHashMap<String, PersistenceInfo>();
    private static boolean init = false;

    public static void init(ServletContext context) {
        if (init)
            return;

        Set<String> dss = context.getResourcePaths("/WEB-INF/datasource/");
        if (dss != null) {
            for (Iterator<String> ite = dss.iterator(); ite.hasNext();) {
                String dataSourceFile = ite.next();
                try {
                    URL persistenceXml = context.getResource(dataSourceFile);
                    processDataSource(persistenceXml);
                } catch (Throwable ex) {
                    LOGGER.error("Process datasource file {} failed, cause by {}", dataSourceFile, ex);
                }
            }

            init = true;
        } else {
            LOGGER.error("Datasource not found!");
        }
    }

    public static void initialize(String dataSourceConfigPath) {
        File cacheConfigFile = new File(dataSourceConfigPath);
        if (!cacheConfigFile.exists()) {
            init = true;
            LOGGER.error(dataSourceConfigPath + " not exist!");
            return;
        }

        try {
            URL dataSourceConfigURL = cacheConfigFile.toURI().toURL();
            URL[] urls = { dataSourceConfigURL };
            init(urls);
        } catch (MalformedURLException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            init = true;
        }
    }

    public static void clear() {
        init = false;
        persistences.clear();
    }

    public static PersistenceInfo getByTenantID(String id) {
        if (id != null && persistences.containsKey(id))
            return persistences.get(id);

        if (new HashSet<PersistenceInfo>(persistences.values()).size() == 1)
            return persistences.values().iterator().next();

        return null;
    }

    public static PersistenceInfo getByContext(Context ctx) {
        String tenantId = null;
        if (ctx != null) {
            tenantId = ctx.getTenantId();
        }
        return getByTenantID(tenantId);
    }

    public static PersistenceInfo getByContextToken(ContextToken ct) {
        String tenantId = null;
        if (ct != null && ct.getContext() != null) {
            tenantId = ct.getContext().getTenantId();
        }
        return getByTenantID(tenantId);
    }

    public static ConcurrentMap<String, PersistenceInfo> getPersistences() {
        return persistences;
    }

    public static PersistenceInfo processDataSource(URL persistenceXmlURL) throws Exception {
        PersistenceInfo pi = new PersistenceInfo(persistenceXmlURL.toString());

        XMLInputFactory xif = XMLInputFactory.newInstance();
        XMLEventReader reader = xif.createXMLEventReader(persistenceXmlURL.openStream());
        try {
            PersistenceUnitInfo unit = null;
            while (reader.hasNext()) {
                XMLEvent e = reader.nextEvent();
                if (e.isStartElement()) {
                    StartElement se = e.asStartElement();
                    String name = se.getName().getLocalPart();

                    if (PersistenceUnitConstants.PERSISTENCE_UNIT.equalsIgnoreCase(name)) {
                        Attribute attr = se.getAttributeByName(new javax.xml.namespace.QName(
                                PersistenceUnitConstants.NAME));
                        unit = new PersistenceUnitInfo();
                        unit.setPersistenceUnitName(attr.getValue());
                        unit.setPersistenceUnitRootUrl(persistenceXmlURL);

                        pi.addPersistenceUnit(unit);
                    } else if (PersistenceUnitConstants.MONGODB.equalsIgnoreCase(name)) {
                        Attribute url = se.getAttributeByName(new QName(PersistenceUnitConstants.URL));
                        unit.setMongoUrl(url.getValue());
                        Attribute dbName = se.getAttributeByName(new QName(PersistenceUnitConstants.DBNAME));
                        unit.setMongoDbName(dbName.getValue());
                        Attribute user = se.getAttributeByName(new QName(PersistenceUnitConstants.USER));
                        if (user != null)
                            unit.setMongoUser(user.getValue());
                        Attribute pw = se.getAttributeByName(new QName(PersistenceUnitConstants.PASSWORD));
                        if (pw != null)
                            unit.setMongoPw(pw.getValue());

                        Attribute connectionsPerHost = se.getAttributeByName(new QName(
                                PersistenceUnitConstants.CONNECTIONS_PER_HOST));
                        if (connectionsPerHost != null)
                            unit.setConnectionsPerHost(Integer.parseInt(connectionsPerHost.getValue()));
                        Attribute threadsAllowedToBlockForConnectionMultiplier = se.getAttributeByName(new QName(
                                PersistenceUnitConstants.THREADS_ALLOWED_TO_BLOCK_FOR_CONNECTION_MULTIPLIER));
                        if (threadsAllowedToBlockForConnectionMultiplier != null)
                            unit.setThreadsAllowedToBlockForConnectionMultiplier(Integer
                                    .parseInt(threadsAllowedToBlockForConnectionMultiplier.getValue()));
                        Attribute maxWaitTime = se
                                .getAttributeByName(new QName(PersistenceUnitConstants.MAX_WAIT_TIME));
                        if (maxWaitTime != null)
                            unit.setMaxWaitTime(Integer.parseInt(maxWaitTime.getValue()));
                        Attribute connectTimeout = se.getAttributeByName(new QName(
                                PersistenceUnitConstants.CONNECT_TIMEOUT));
                        if (connectTimeout != null)
                            unit.setConnectTimeout(Integer.parseInt(connectTimeout.getValue()));
                        Attribute socketTimeout = se.getAttributeByName(new QName(
                                PersistenceUnitConstants.SOCKET_TIMEOUT));
                        if (socketTimeout != null)
                            unit.setSocketTimeout(Integer.parseInt(socketTimeout.getValue()));
                        Attribute autoConnectRetry = se.getAttributeByName(new QName(
                                PersistenceUnitConstants.AUTO_CONNECT_RETRY));
                        if (autoConnectRetry != null)
                            unit.setAutoConnectRetry(Boolean.valueOf(autoConnectRetry.getValue()));

                        Attribute socketKeepAlive = se.getAttributeByName(new QName(
                                PersistenceUnitConstants.SOCKET_KEEPALIVE));
                        if (socketKeepAlive != null)
                            unit.setSocketKeepAlive(Boolean.valueOf(socketKeepAlive.getValue()));

                        Attribute writeConcern = se
                                .getAttributeByName(new QName(PersistenceUnitConstants.WRITE_CONCERN));
                        if (writeConcern != null)
                            unit.setWriteConcern(WriteConcern.valueOf(writeConcern.getValue()));

                        Attribute readPreference = se.getAttributeByName(new QName(
                                PersistenceUnitConstants.READ_PREFERENCE));
                        if (readPreference != null)
                            unit.setReadPreference(ReadPreference.valueOf(readPreference.getValue()));
                    } else if (PersistenceUnitConstants.TENANTID.equalsIgnoreCase(name)) {
                        pi.getTenantIDs().add(reader.getElementText());
                    }
                }
            }
        } finally {
            reader.close();
        }

        List<String> ids = pi.getTenantIDs();
        for (String tenantID : ids) {
            persistences.put(tenantID, pi);
        }

        return pi;
    }

    /**
     * 使用url来初始化
     * 
     * @param urls
     */
    public static void init(URL[] urls) {
        if (init)
            return;
        if (urls != null) {
            for (URL url : urls) {

                try {
                    processDataSource(url);
                } catch (Throwable ex) {
                    LOGGER.error("Process datasource file {} failed, cause by {}", url, ex);
                }
            }
            init = true;
        } else {
            LOGGER.error("Datasource not found!");
        }
    }

    public static void init() {
        File dsDir = null;

        if (System.getProperty("DataSourceDir") != null) {
            dsDir = new File(System.getProperty("DataSourceDir"));
        } else {
            String home = System.getProperty("CBOS_HOME");
            if (home == null) {
                LOGGER.error("Can't get CBOS_HOME system property!");
                return;
            }

            dsDir = new File(home + "/server/properties/datasource");
            if (!dsDir.exists())
                LOGGER.error(dsDir + " not exist!");
        }

        FilenameFilter filter = new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                if (name.startsWith(PersistenceUnitConstants.PREFIX) && name.toLowerCase().endsWith(".xml"))
                    return true;
                return false;
            }

        };
        File[] dss = dsDir.listFiles(filter);
        for (int i = 0; i < dss.length; i++) {
            try {
                processDataSource(dss[i].toURI().toURL());
            } catch (Throwable t) {
                LOGGER.error("", t);
            }
        }
    }
}
