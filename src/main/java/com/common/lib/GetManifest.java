package com.common.lib;

import java.util.Iterator;
import java.util.Set;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongo.morphia.complex.kingdee.core.dao.factory.DataSourceConfigManager;

public class GetManifest {
	private static Logger logger = LoggerFactory.getLogger(GetManifest.class);
	
	public void getMANIFEST(ServletContext context){

        String libPath = context.getRealPath("/WEB-INF/lib/");
        if (libPath != null)
            logger.info("Begin scan entities in " + libPath);
		//获取lib文件夹下所有lib包的文件名称集合
        Set<String> libs = context.getResourcePaths("/WEB-INF/lib/");
		//判断是否获取的lib包集合有无文件数据
        if (libs == null || libs.size() == 0) {
            logger.info(libPath + " is empty");
            return;
        } else {
            logger.info("jar size: " + libs.size());
        }
		//循环每个jar包
        for (Iterator<String> ite = libs.iterator(); ite.hasNext();) {
            try {
                String lib = ite.next();//获取jar包名称，并判断是否是jar包
                if (!lib.toLowerCase().endsWith(".jar")) {//如果不是jar，则忽略
                    logger.info(lib + " not jar, ignore scan entities.");
                    continue;
                }

                JarFile jf = new JarFile(context.getRealPath(lib));//获取该jar包的JartFile对象
                Manifest manifest = jf.getManifest(); //获取该jar文件的MANIFEST.MF的文件对象
				//    private static String getEntityPackages(Manifest manifest) {
				String entityPackages;
				//下面是获取MANIFEST.MF的某个属性值PersistenceUnitConstants.MF_PERSISTENCE_PACKAGE=MF_PERSISTENCE_PACKAGE”自定义的常理，下面同理
				entityPackages = manifest.getMainAttributes().getValue(PersistenceUnitConstants.MF_PERSISTENCE_PACKAGE);
				if (StringUtils.isEmpty(entityPackages))
					entityPackages = manifest.getMainAttributes().getValue(PersistenceUnitConstants.MF_ENTITY_PACKAGE);
				if (Until.StringUtils.isEmpty(entityPackages)) {
					if (manifest.getMainAttributes().getValue("CBOS-AppBundle") != null) {
						entityPackages = manifest.getMainAttributes().getValue("Bundle-SymbolicName") + ".entity";
					}
				}
				//return entityPackages;
				//}
				}catch(Exception e){
				}
		}			
	}
	

}
