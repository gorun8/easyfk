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
 * Author:hezhiping   Email:110476592@qq.com   Date: 15-11-3
 */
package cn.gorun8.easyfk.common.dao;

import cn.gorun8.easyfk.base.util.Debug;
import cn.gorun8.easyfk.base.util.UtilIOC;
import cn.gorun8.easyfk.entity.GenericEntityException;
import cn.gorun8.easyfk.entity.GenericValue;
import cn.gorun8.easyfk.entity.dao.WriteDaoBase;
import cn.gorun8.easyfk.entity.datasource.ReadSqlSessionTemplate;
import cn.gorun8.easyfk.entity.datasource.WriteSqlSessionTemplate;
import org.apache.ibatis.annotations.Param;


public abstract class CommonWriteDao extends WriteDaoBase {
    private static final String module = CommonWriteDao.class.getName();

    public CommonWriteDao(){
        super("cn.gorun8.easyfk.common.dao.CommonWriteDao");
    }

    public WriteSqlSessionTemplate getSqlSessionTemplate(){
        WriteSqlSessionTemplate writeSqlSessionTemplate = UtilIOC.getBean("commonWriteSqlSession");
        if(writeSqlSessionTemplate == null){
            Debug.logError("commonWriteSqlSession not found", module);
            return null;
        }
        return  writeSqlSessionTemplate;
    }
}
