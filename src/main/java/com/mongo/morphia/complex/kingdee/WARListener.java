package com.mongo.morphia.complex.kingdee;



import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kingdee.cbos.common.cache.CacheConfigManager;
import com.mongo.morphia.complex.core_dal.SubSystemUtils;
import com.mongo.morphia.complex.core_dal.annotation.NotEntity;
import com.mongo.morphia.complex.core_dal.annotation.PersistenceUnit;
import com.mongo.morphia.complex.kingdee.common.PersistenceInfo;
import com.mongo.morphia.complex.kingdee.common.PersistenceUnitInfo;
import com.mongo.morphia.complex.kingdee.common.consts.PersistenceUnitConstants;
import com.mongo.morphia.complex.kingdee.core.dao.DAOListenerManager;
import com.mongo.morphia.complex.kingdee.core.dao.factory.DAOConstants;
import com.mongo.morphia.complex.kingdee.core.dao.factory.DataSourceConfigManager;
import com.mongo.morphia.complex.kingdee.core.server.AbstractBObject;
import com.mongo.morphia.complex.kingdee.core.server.AbstractDynaBObject;
import com.mongo.morphia.complex.kingdee.core.server.BObject;

/**
 * 
 * @since 2010-9-19
 * @author guolei_mao
 */
public class WARListener implements ServletContextListener {
    private static Logger logger = LoggerFactory.getLogger(WARListener.class);

    private static Map<String, String> entityNameToPU = new HashMap<String, String>();

    @Override
    public void contextDestroyed(ServletContextEvent event) {

    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();

        DataSourceConfigManager.init(context);

        scanEntity(context);

        CacheConfigManager.init(context);
    }

    private void scanEntity(ServletContext context) {
        String entityPackages = null;
        String pu = null;
        Map<String, String> subSystem = null;

        try {
            URL mf = context.getResource("/WEB-INF/classes/META-INF/MANIFEST.MF");
            if (mf != null) {
                Manifest manifest = new Manifest(mf.openStream());
                if (manifest != null) {
                    entityPackages = getEntityPackages(manifest);
                    pu = manifest.getMainAttributes().getValue(PersistenceUnitConstants.MF_PERSISTENCE_UNIT);
                    subSystem = getSubSystem(manifest);
                }
            }
        } catch (Exception e) {
            logger.warn("", e);
        }

        if (entityPackages != null) {
            String[] packages = entityPackages.split(";");
            for (int i = 0; i < packages.length; i++) {
                Set<String> entityClasses = new HashSet<String>();
                Set<String> set = context.getResourcePaths("/WEB-INF/classes/" + packages[i].trim().replace('.', '/')
                        + "/");
                if (set != null) {
                    for (Iterator<String> ite = set.iterator(); ite.hasNext();) {
                        String s = ite.next();
                        if (s.endsWith(".class")) {
                            s = s.substring("/WEB-INF/classes/".length());
                            entityClasses.add(s.replace('/', '.').substring(0, s.lastIndexOf('.')));
                        }
                    }
                }
                scanEntity(entityClasses, pu, subSystem);
            }
        }

        String libPath = context.getRealPath("/WEB-INF/lib/");
        if (libPath != null)
            logger.info("Begin scan entities in " + libPath);

        Set<String> libs = context.getResourcePaths("/WEB-INF/lib/");

        if (libs == null || libs.size() == 0) {
            logger.info(libPath + " is empty");
            return;
        } else {
            logger.info("jar size: " + libs.size());
        }

        for (Iterator<String> ite = libs.iterator(); ite.hasNext();) {
            try {
                String lib = ite.next();
                if (!lib.toLowerCase().endsWith(".jar")) {
                    logger.info(lib + " not jar, ignore scan entities.");
                    continue;
                }

                JarFile jf = new JarFile(context.getRealPath(lib));
                Manifest manifest = jf.getManifest();

                if (manifest == null) {
                    logger.info("Can't find META-INF/MANIFEST.MF in " + lib + " , ignore scan entities.");
                    continue;
                }

                entityPackages = getEntityPackages(manifest);

                if (StringUtils.isEmpty(entityPackages)) {
                    if (logger.isDebugEnabled()) {
                        logger.debug(lib + "  " + PersistenceUnitConstants.MF_ENTITY_PACKAGE + " is empty.");
                    }
                    continue;
                }

                pu = manifest.getMainAttributes().getValue(PersistenceUnitConstants.MF_PERSISTENCE_UNIT);
                subSystem = getSubSystem(manifest);

                String[] packages = entityPackages.split(";");
                for (int i = 0; i < packages.length; i++) {
                    packages[i] = packages[i].trim().replace('.', '/');
                    if (!packages[i].endsWith("/"))
                        packages[i] = packages[i] + "/";
                }

                Enumeration<JarEntry> enume = jf.entries();
                Set<String> entityClasses = new HashSet<String>();
                while (enume.hasMoreElements()) {
                    JarEntry je = enume.nextElement();
                    String name = je.getName();
                    if (match(name, packages)) {
                        name = name.substring(0, name.length() - 6);
                        name = name.replace('/', '.');
                        entityClasses.add(name);
                    }
                }

                logger.info("Find in " + lib + " entity size: " + entityClasses.size());
                scanEntity(entityClasses, pu, subSystem);
            } catch (Exception e) {
                logger.warn("", e);
            }
        }

        if (libPath != null)
            logger.info("End scan entities in " + libPath);
    }

    private static Map<String, String> getSubSystem(Manifest manifest) {
        Map<String, String> packageToSubSystem = new HashMap<String, String>();
        String subSystem = manifest.getMainAttributes().getValue(DAOConstants.SUBSYSTEM);
        if (!StringUtils.isEmpty(subSystem)) {
            String[] ss = subSystem.split(";");
            String entityPackages = getEntityPackages(manifest);
            String[] packages = entityPackages.split(";");
            if (ss.length > 1 && ss.length < packages.length) {
                throw new RuntimeException("The subSystem sizes not match entityPackages sizes.");
            } else {
                for (int i = 0; i < packages.length; i++) {
                    if (ss.length > 1)
                        packageToSubSystem.put(packages[i].trim(), ss[i].trim());
                    else
                        packageToSubSystem.put(packages[i].trim(), ss[0].trim());
                }
            }
        }
        return packageToSubSystem;
    }

    private static String getEntityPackages(Manifest manifest) {
        String entityPackages;
        entityPackages = manifest.getMainAttributes().getValue(PersistenceUnitConstants.MF_PERSISTENCE_PACKAGE);
        if (StringUtils.isEmpty(entityPackages))
            entityPackages = manifest.getMainAttributes().getValue(PersistenceUnitConstants.MF_ENTITY_PACKAGE);
        if (StringUtils.isEmpty(entityPackages)) {
            if (manifest.getMainAttributes().getValue("CBOS-AppBundle") != null) {
                entityPackages = manifest.getMainAttributes().getValue("Bundle-SymbolicName") + ".entity";
            }
        }
        return entityPackages;
    }

    private static boolean match(String entryName, String[] packages) {
        if (entryName.endsWith(".class")) {
            for (int i = 0; i < packages.length; i++) {
                if (entryName.startsWith(packages[i]) && entryName.indexOf("/", packages[i].length() + 1) < 0)
                    return true;
            }
        }
        return false;
    }

    private static void scanEntity(Set<String> entityClasses, String pu, Map<String, String> packageToSubSystem) {
        Map<String, PersistenceInfo> pers = DataSourceConfigManager.getPersistences();
        for (Iterator<Map.Entry<String, PersistenceInfo>> iterator = pers.entrySet().iterator(); iterator.hasNext();) {
            Map.Entry<String, PersistenceInfo> entry = iterator.next();
            PersistenceInfo pi = entry.getValue();
            PersistenceUnitInfo pui = pi.getByName(pu);
            for (Iterator<String> ite = entityClasses.iterator(); ite.hasNext();) {
                String s = ite.next();

                if (s.endsWith("Enum") || entityNameToPU.containsKey(s)) {
                    continue;
                }

                Class<?> clazz = null;
                try {
                    clazz = Class.forName(s, true, Thread.currentThread().getContextClassLoader());
                    if (clazz.isAnnotationPresent(NotEntity.class)) {
                        continue;
                    }
//暂时屏蔽
//                    if (!BObject.class.isAssignableFrom(clazz)) {
//                        if (logger.isDebugEnabled()) {
//                            logger.debug(s + " not implemnets " + BObject.class.getName());
//                        }
//                        continue;
//                    }
                } catch (ClassNotFoundException e) {
                    logger.warn("", e);
                    continue;
                }

                if (clazz != null && clazz.isAnnotationPresent(PersistenceUnit.class)) {
                    String puName = (clazz.getAnnotation(PersistenceUnit.class)).value();
                    PersistenceUnitInfo pui2 = pi.getByName(puName);
                    if (pui2 != null) {
                        addBaseEntity(pui2);
                        pui2.getManagedClassNames().add(s);
                        entityNameToPU.put(s, puName);
                    } else {
                        logger.warn(s + " 's PersistenceUnit " + puName + " not exist in " + pi.getID());
                    }
                } else {
                    if (pui != null) {
                        addBaseEntity(pui);
                        pui.getManagedClassNames().add(s);
                        entityNameToPU.put(s, pu);
                    }
                }

                if (packageToSubSystem.get(clazz.getPackage().getName()) != null) {
                    SubSystemUtils.setSubSystem(s, packageToSubSystem.get(clazz.getPackage().getName()));
                }
            }
        }
    }

    private static void addBaseEntity(PersistenceUnitInfo pui) {
        if (pui == null)
            return;
        if (!pui.getManagedClassNames().contains(AbstractBObject.class.getName())) {
            pui.getManagedClassNames().add(AbstractBObject.class.getName());
        }
        if (!pui.getManagedClassNames().contains(AbstractDynaBObject.class.getName())) {
            pui.getManagedClassNames().add(AbstractDynaBObject.class.getName());
        }
    }

    public static String getPersistenceUnit(Class<?> clazz) {
        return entityNameToPU.get(clazz.getName());
    }

    public static String getPersistenceUnit(String entityName) {
        return entityNameToPU.get(entityName);
    }

    public static void init() {
        DataSourceConfigManager.init();

        scanEntity();

        CacheConfigManager.init(null);
        //暂时屏蔽，不知道干啥的
//        DAOListenerManager.register(ObjectScopeDAOListener.class.getName(), ObjectScopeDAOListener.class);
    }

    /**
     * 使用url来初始化
     * 
     * @param urls
     */
    public static void init(URL[] urls) {
        DataSourceConfigManager.init(urls);

        scanEntity();

        CacheConfigManager.init(null);
        //暂时屏蔽，不知道干啥的
//        DAOListenerManager.register(ObjectScopeDAOListener.class.getName(), ObjectScopeDAOListener.class);
    }

    public static void initialize(String dataSourceConfigPath, String cacheConfigPath) {
        DataSourceConfigManager.initialize(dataSourceConfigPath);
        scanEntity();
        CacheConfigManager.initialize(cacheConfigPath);
        //暂时屏蔽，不知道干啥的
//        DAOListenerManager.register(ObjectScopeDAOListener.class.getName(), ObjectScopeDAOListener.class);
    }

    private static void scanEntity() {
        String classPath = System.getProperty("java.class.path");        
        String[] paths = classPath.split(File.pathSeparator);
        for (int i = 0; i < paths.length; i++) {
            String entityPackages = null;
            String pu = null;
            Map<String, String> subSystem = null;
            File path = new File(paths[i]);
            logger.info("path=" + path + ";File.pathSeparator=" + File.pathSeparator);
            if (path.isDirectory()) {
                try {
                    File mf = new File(path, "/META-INF/MANIFEST.MF");
                    if (mf.exists()) {
                        Manifest manifest = new Manifest(new FileInputStream(mf));
                        entityPackages = getEntityPackages(manifest);
                        if (StringUtils.isEmpty(entityPackages)) {
                            if (logger.isDebugEnabled()) {
                                logger.debug(mf + "  " + PersistenceUnitConstants.MF_ENTITY_PACKAGE + " is empty.");
                            }
                            continue;
                        }
                        pu = manifest.getMainAttributes().getValue(PersistenceUnitConstants.MF_PERSISTENCE_UNIT);
                        subSystem = getSubSystem(manifest);
                    } else {
                        continue;
                    }
                } catch (Exception e) {
                    logger.warn("", e);
                }

                String[] packages = entityPackages.split(";");
                for (int j = 0; j < packages.length; j++) {
                    Set<String> entityClasses = new HashSet<String>();
                    File entityPath = new File(path, packages[j].trim().replace('.', File.separatorChar));
                    if (entityPath.exists()) {
                        File[] files = entityPath.listFiles();
                        if (files != null && files.length > 0) {
                            for (int k = 0; k < files.length; k++) {
                                try {
                                    String s = files[k].getCanonicalPath();
                                    if (s.endsWith(".class")) {
                                        s = s.substring(path.getCanonicalPath().length() + 1);
                                        entityClasses.add(s.replace(File.separatorChar, '.').substring(0,
                                                s.lastIndexOf('.')));
                                    }
                                } catch (Exception e) {

                                }
                            }
                        }
                    }
                    scanEntity(entityClasses, pu, subSystem);
                }
            } else {
                try {
                    String s = path.getCanonicalPath();

                    if (!s.toLowerCase().endsWith(".jar")) {
                        if (logger.isDebugEnabled()) {
                            logger.debug(s + " not jar, ignore scan entities.");
                        }
                        continue;
                    }

                    JarFile jf = new JarFile(path);
                    Manifest manifest = jf.getManifest();

                    if (manifest == null) {
                        if (logger.isDebugEnabled()) {
                            logger.debug("Can't find META-INF/MANIFEST.MF in " + s + " , ignore scan entities.");
                        }
                        continue;
                    }

                    entityPackages = getEntityPackages(manifest);

                    if (StringUtils.isEmpty(entityPackages)) {
                        if (logger.isDebugEnabled()) {
                            logger.debug(path + "  " + PersistenceUnitConstants.MF_ENTITY_PACKAGE + " is empty.");
                        }
                        continue;
                    }

                    pu = manifest.getMainAttributes().getValue(PersistenceUnitConstants.MF_PERSISTENCE_UNIT);
                    subSystem = getSubSystem(manifest);

                    String[] packages = entityPackages.split(";");
                    for (int j = 0; j < packages.length; j++) {
                        packages[j] = packages[j].replace('.', '/');
                        if (!packages[j].endsWith("/"))
                            packages[j] = packages[j] + "/";
                    }

                    Enumeration<JarEntry> enume = jf.entries();
                    Set<String> entityClasses = new HashSet<String>();
                    while (enume.hasMoreElements()) {
                        JarEntry je = enume.nextElement();
                        String name = je.getName();
                        if (match(name, packages)) {
                            name = name.substring(0, name.length() - 6);
                            name = name.replace('/', '.');
                            entityClasses.add(name);
                        }
                    }
                    scanEntity(entityClasses, pu, subSystem);
                } catch (Exception e) {
                    logger.warn("", e);
                }
            }
        }
    }
}
