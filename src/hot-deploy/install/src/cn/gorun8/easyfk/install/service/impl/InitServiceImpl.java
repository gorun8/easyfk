/*
 * Project:Easy Web Framework
 * Description:
 * EasyFK stands for Easy Web Framework.It's an open source product for E-Business / E-Commerce.It
 * was launched by a chinese Hezhiping(QQ:110476592) in 2015.The goal of EasyFK is to  provide a
 * foundation and starting point for reliable, secure , simple-to-use ,cost-effective ,scalable
 * and suitable-for-Chinese E-Business / E-Commerce solutions. With EasyFK, you can get started
 * right away without the huge deployment and maintenance costs of E-Business / E-Commerce systems.
 * Of course, you can customize it or use it as a framework to implement your most challenging business needs.
 * EasyFk is licensed under the Apache License Version 2.0.  You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Author:hezhiping   Email:110476592@qq.com
 */
package cn.gorun8.easyfk.install.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import cn.gorun8.easyfk.base.util.UtilIO;
import javolution.util.FastList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gorun8.easyfk.base.component.ComponentConfig;
import cn.gorun8.easyfk.base.component.ComponentConfig.EntityResourceInfo;
import cn.gorun8.easyfk.base.util.Debug;
import cn.gorun8.easyfk.base.util.UtilProperties;
import cn.gorun8.easyfk.install.dao.InitDao;
import cn.gorun8.easyfk.install.service.InitService;

/**
 * 根据参择的数据库类型，加载配置模板config/<数据库类型>.properties(系统支持
 * 的数据库，都有一个以数据库类型名为标识的配置模板).
 * 再根据页面上的配置的内容，生成config/install-database-temp.properties
 * 安装成功后会将config/install-database-temp.properties的内容复制到
 * base/config/easyfk-database.properties。系统运行过程中从该文件加载配置
 * @author:hezhiping(110476592@qq.com)
 *
 */
@Service
public class InitServiceImpl implements InitService {
	public static final String module = InitServiceImpl.class.getName();
	
	@Autowired
	private InitDao initDao;
	
	@Override
	public String checkConnect(Properties properties) throws Exception {
		String dbtype = properties.getProperty("dbtype","mysql");
		
		Properties tempps = UtilProperties.getProperties(dbtype);
		Enumeration<Object> tmpkeys = tempps.keys();
		while (tmpkeys.hasMoreElements()) {
			String tmpkey = (String) tmpkeys.nextElement();
			String tmpvalue = tempps.getProperty(tmpkey);
			Enumeration<Object> keys = properties.keys();
			while (keys.hasMoreElements()) {
				String key = (String) keys.nextElement();
				String relkey ="db." + key;
				String relvalue = properties.getProperty(key);
				if(tmpkey.equals(relkey))
				{
					tmpvalue = relvalue;
					break;
				}else{
					tmpvalue = tmpvalue.replace("${"+relkey+"}", relvalue);
				}
			}//end while
			UtilProperties.setPropertyValue(DB_PROPERTIES_PATH_TMP,tmpkey, tmpvalue);
		}

		return initDao.checkConnect();
	}

	@Override
	public String install() throws Exception {
		String dbtype = UtilProperties.getPropertyValue(DB_PROPERTIES_PATH_TMP,"db.dbtype","mysql");
		String dataType = UtilProperties.getPropertyValue(DB_PROPERTIES_PATH_TMP,"db.dataType","Demo");


		Collection<ComponentConfig> allComponentList = ComponentConfig.getAllComponents();
		List<String> modelList = FastList.newInstance();
		List<String> datalList = FastList.newInstance();
		List<String> demoList = FastList.newInstance();


		for (ComponentConfig cc: allComponentList) {
			List<EntityResourceInfo>  entityList = cc.getEntityResourceInfos();
			String path = cc.getRootLocation();
			for(EntityResourceInfo it : entityList){
				String loc = it.location;
				loc = loc.replace("${db.type}", dbtype);
				File f = new File(path,loc);
				if(f.isFile()){
					if("model".equals(it.type)){
						modelList.add(f.getAbsolutePath());
					}else if("data".equals(it.type)){
						datalList.add(f.getAbsolutePath());
					}else if("demo".equals(it.type)){
						demoList.add(f.getAbsolutePath());
					}

				}else{
					Debug.logError(f.getAbsolutePath()+"is not a sql file ,please check",module);
				}
			}
		}//end for
		
		if(!modelList.isEmpty()){
			initDao.initDB(dbtype,modelList);

			if(!datalList.isEmpty()){
				initDao.initDB(dbtype,datalList);
			}

			if("Demo".equals(dataType)){
				if(!demoList.isEmpty()){
					initDao.initDB(dbtype,demoList);
				}
			}
		}
		return "success";
	}
	
	 
 

	@Override
	public String finish() {
		File sf = new File(DB_PROPERTIES_PATH_TMP);
		File df = new File(DB_PROPERTIES_PATH);
		try {
			UtilIO.copy(new FileInputStream(sf),true, new FileOutputStream(df),true);
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
		}

		File file = cn.gorun8.easyfk.base.util.FileUtil.getFile("component://base/config/install.lock");
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
	
}
