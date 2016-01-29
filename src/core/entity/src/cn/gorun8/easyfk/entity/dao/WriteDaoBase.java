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
 * Author:hezhiping   Email:110476592@qq.com   Date: 15-12-1
 */
package cn.gorun8.easyfk.entity.dao;

import cn.gorun8.easyfk.entity.GenericEntityException;
import cn.gorun8.easyfk.entity.GenericValue;
import cn.gorun8.easyfk.entity.datasource.ReadSqlSessionTemplate;
import cn.gorun8.easyfk.entity.datasource.WriteSqlSessionTemplate;
import cn.gorun8.easyfk.entity.util.UtilEntity;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public abstract class WriteDaoBase extends SqlSessionDaoSupport {

    private String nameSpace;

    public WriteDaoBase(String nameSpace) {
        this.nameSpace = nameSpace;
        WriteSqlSessionTemplate  writeSqlSessionTemplate = getSqlSessionTemplate();
        if(writeSqlSessionTemplate == null){
            return ;
        }
        setSqlSessionTemplate(writeSqlSessionTemplate);
    }

    public abstract WriteSqlSessionTemplate getSqlSessionTemplate();

    public void setSqlSessionTemplate(WriteSqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    /**
     * 创建一条记录
     * 调用该接口，对应的XML Mapper文件中，必须存在<code>create</code>的接口定义。
     * @param params
     *        条件参数
     * @return
     *        记录条件
     * @throws GenericEntityException
     *
     */
    public GenericValue create(GenericValue params) throws GenericEntityException{
        return create("create",params);
    }
    /**
     * 创建一条记录
     * @param mapperId
     * 对应的XML Mapper文件中，对应的的接口名。
     * @param params
     *        条件参数
     * @return
     *        记录条件
     * @throws GenericEntityException
     *
     */
    public GenericValue create(String mapperId,GenericValue params) throws GenericEntityException{
        String mapper = getMapper(mapperId);
        SqlSession sqlSession = null;
        try {
            sqlSession = this.getSqlSession();
            int rel = sqlSession.insert(mapper, params);
            //sqlSession.commit();
        }catch (Exception e){
            throw  new GenericEntityException(e);
        }finally {
            UtilEntity.close(sqlSession);
        }
        return params;
    }

    /**
     * 创建一条记录，同时清除cache
     * 调用该接口，对应的XML Mapper文件中，必须存在<code>create</code>的接口定义。
     * @param params
     *        条件参数
     * @return
     *        记录条件
     * @throws GenericEntityException
     *
     */
    public GenericValue createCache(GenericValue params) throws GenericEntityException{
        create(params);
        clearCacheLine(params);
        return  params;
    }

    /**
     * 创建一条记录，同时清除cache
     * @param mapperId;
     *        对应的XML Mapper文件中，对应的接口名
     * @param params
     *        条件参数
     * @return
     *        记录条件
     * @throws GenericEntityException
     *
     */
    public GenericValue createCache(String mapperId,GenericValue params) throws GenericEntityException{
        create(mapperId,params);
        clearCacheLine(params);
        return  params;
    }

    /**
     * 更新一条记录
     * 调用该接口，对应的XML Mapper文件中，必须存在<code>store</code>的接口定义。
     * @param params
     *        条件参数
     * @return
     *        受影响的条数
     * @throws GenericEntityException
     *
     */
    public int store(GenericValue params) throws GenericEntityException{
        return store("store",params);
    }
    /**
     * 更新一条记录
     * @param mapperId;
     *        对应的XML Mapper文件中，对应的接口名
     * @param params
     *        条件参数
     * @return
     *        受影响的条数
     * @throws GenericEntityException
     *
     */
    public int store(String mapperId,GenericValue params) throws GenericEntityException{
        String mapper = getMapper(mapperId);
        SqlSession sqlSession = null;
        int count =0;
        try {
            sqlSession = this.getSqlSession();
            count = sqlSession.update(mapper, params);
            //sqlSession.commit();
        }catch (Exception e){
            throw  new GenericEntityException(e);
        }finally {
            UtilEntity.close(sqlSession);
        }
        return count;
    }


    /**
     * 更新一条记录，同时清除缓存
     * 调用该接口，对应的XML Mapper文件中，必须存在<code>store</code>的接口定义。
     * @param params
     *        条件参数
     * @return
     *        受影响的条数
     * @throws GenericEntityException
     *
     */
    public int storeCache(GenericValue params) throws GenericEntityException{
        int count = store(params);
        clearCacheLine(params);
        return count;
    }

    /**
     * 更新一条记录，同时清除缓存
     * @param mapperId
     *      对应的XML Mapper文件中，对应的接口名
     * @param params
     *        条件参数
     * @return
     *        受影响的条数
     * @throws GenericEntityException
     *
     */
    public int storeCache(String mapperId,GenericValue params) throws GenericEntityException{
        int count = store(mapperId,params);
        clearCacheLine(params);
        return count;
    }


    /**
     * 删除多条记录
     * 调用该接口，对应的XML Mapper文件中，必须存在<code>removeValue</code>的接口定义。
     * @param params
     *        条件参数
     * @return
     *        受影响的条数
     * @throws GenericEntityException
     *
     */
    public int removeAll(List<? extends GenericValue> params) throws GenericEntityException {
        return removeAll("removeValue",params);
    }

    /**
     * 删除多条记录
     * @param mapperId
     *        对应的XML Mapper文件中，对应的接口定义
     * @param params
     *        条件参数
     * @return
     *        受影响的条数
     * @throws GenericEntityException
     *
     */
    public int removeAll(String mapperId,List<? extends GenericValue> params) throws GenericEntityException{
         for (GenericValue gv :params){
             removeValue(mapperId,gv);
         }
        return 0;
    }

    /**
     * 删除一条记录，同时清除缓存
     * 调用该接口，对应的XML Mapper文件中，必须存在<code>removeValue</code>的接口定义。
     * @param params
     *        条件参数
     * @return
     *        受影响的条数
     * @throws GenericEntityException
     *
     */
    public int removeAllCache(List<? extends GenericValue> params ) throws GenericEntityException{
        int count = removeAll(params);

        for (GenericValue gv :params){
            clearCacheLine(gv);
        }
        return count;
    }

    /**
     * 删除一条记录，同时清除缓存
     * @param mapperId
     *       对应的XML Mapper文件中，对应的接口定义
     * @param params
     *        条件参数
     * @return
     *        受影响的条数
     * @throws GenericEntityException
     *
     */
    public int removeAllCache(String mapperId,List<? extends GenericValue> params) throws GenericEntityException{
        int count =  removeAll(mapperId,params);
        for (GenericValue gv :params){
            clearCacheLine(gv);
        }
        return count ;
    }


    /**
     * 删除一条记录
     * 调用该接口，对应的XML Mapper文件中，必须存在<code>removeValue</code>的接口定义。
     * @param params
     *        条件参数
     * @return
     *        受影响的条数
     * @throws GenericEntityException
     *
     */
    public int removeValue(GenericValue params) throws GenericEntityException {
        return removeValue("removeValue",params);
    }

    /**
     * 删除一条记录
     * @param mapperId
     *        对应的XML Mapper文件中，对应的接口名
     * @param params
     *        条件参数
     * @return
     *        受影响的条数
     * @throws GenericEntityException
     *
     */
    public int removeValue(String mapperId,GenericValue params) throws GenericEntityException{
        String mapper = getMapper(mapperId);
        SqlSession sqlSession = null;
        int count = 0;
        try {
            sqlSession = this.getSqlSession();
            count = sqlSession.delete(mapper, params);
            //sqlSession.commit();
        }catch (Exception e){
            throw  new GenericEntityException(e);
        }finally {
            UtilEntity.close(sqlSession);
        }
        return count;
    }

    /**
     * 删除一条记录，同时清除缓存
     * 调用该接口，对应的XML Mapper文件中，必须存在<code>removeValue</code>的接口定义。
     * @param params
     *        条件参数
     * @return
     *        受影响的条数
     * @throws GenericEntityException
     *
     */
    public int removeValueCache(GenericValue params) throws GenericEntityException{
        int count = removeValue(params);
        clearCacheLine(params);
        return count ;
    }

    /**
     * 删除一条记录，同时清除缓存
     * @param mapperId
     *        对应的XML Mapper文件中，对应的接口
     * @param params
     *        条件参数
     * @return
     *        受影响的条数
     * @throws GenericEntityException
     *
     */
    public int removeValueCache(String mapperId,GenericValue params ) throws GenericEntityException{
        int count = removeValue(mapperId,params);
        clearCacheLine(params);
        return count;
    }

    /**
     * 清除缓存中的指定值
     * @param value
     */
    public void clearCacheLine(GenericValue value){
        //TODO 实现缓存
    }


    /**
     * 清除缓存中的与该实体相关的所有信息
     */
    public void clearAllCaches(){
        //TODO 实现缓存
    }

    /**
     * 获取Mapper接口的全名.
     * @param id
     * @return
     */
    private String getMapper(String id){
        return  nameSpace+"."+id;
    }
}
