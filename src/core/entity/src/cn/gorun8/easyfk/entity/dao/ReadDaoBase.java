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

import cn.gorun8.easyfk.base.util.Debug;
import cn.gorun8.easyfk.entity.GenericEntityException;
import cn.gorun8.easyfk.entity.GenericValue;
import cn.gorun8.easyfk.entity.datasource.ReadSqlSessionTemplate;
import cn.gorun8.easyfk.entity.util.UtilEntity;
import javolution.util.FastList;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public abstract class ReadDaoBase extends SqlSessionDaoSupport {
    private static final String module = ReadDaoBase.class.getName();

    private String nameSpace;

    public ReadDaoBase(String nameSpace) {
        this.nameSpace = nameSpace;
        ReadSqlSessionTemplate readSqlSessionTemplate= getSqlSessionTemplate();
        if(readSqlSessionTemplate == null){
            return;
        }
        setSqlSessionTemplate(readSqlSessionTemplate);
    }

    public abstract ReadSqlSessionTemplate getSqlSessionTemplate();

    public void setSqlSessionTemplate(ReadSqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }


        /**
         * 查询记录条数
         * 调用该接口，对应的XML Mapper文件中，必须存在<code>findCount</code>的接口定义。
         * @param params
         *        条件参数
         * @return
         *        记录条件
         * @throws GenericEntityException
         *
         */
    public long findCount(GenericValue params) throws GenericEntityException {
        return findCount("findCount",params);
    }

    /**
     * 查询记录条数
     * @param mapperId
     *        XML Mapper文件中对应的接口名
     * @param params
     *        条件参数
     * @return
     *        记录条件
     * @throws GenericEntityException
     */
    public long findCount(String mapperId,GenericValue params) throws GenericEntityException{
        String mapper = getMapper(mapperId);
        SqlSession sqlSession = null;
        int count =0;
        try {
            sqlSession = this.getSqlSession();
            count = sqlSession.selectOne(mapper,params);
        }catch (Exception e){
            throw  new GenericEntityException(e);
        }finally {
            UtilEntity.close(sqlSession);
        }
        return count;
    }

    /**
     * 按条查询记录条数，优先在Cache中查找，找到直接返回，
     *        如果找不到再到数据库查找，找到后先将结果放入缓存，再返回
     * 调用该接口，对应的XML Mapper文件中，必须存在<code>findCount</code>的接口定义。
     * @param params
     *        条件参数
     * @return
     *        记录集
     * @throws GenericEntityException
     *
     */
    public long findCountCache(GenericValue params) throws GenericEntityException {
        List<GenericValue> listgv =  findListCache(params);
        if(listgv != null){
            return  listgv.size();
        }

        return findCount(params);
    }

    /**
     * 按条查询记录条数，优先在Cache中查找，找到直接返回，
     *        如果找不到再到数据库查找，找到后先将结果放入缓存，再返回
     * @param  mapperId
     *        对应的XML Mapper文件中，对应接口定义。
     * @param params
     *        条件参数
     * @return
     *        记录集
     * @throws GenericEntityException
     *
     */
    public long findCountCache(String mapperId,GenericValue params) throws GenericEntityException{

        List<GenericValue> listgv =  findListCache(mapperId, params);
        if(listgv != null){
            return  listgv.size();
        }

        return findCount(mapperId,params);
    }

    /**
     * 按条查询记录集
     * 调用该接口，对应的XML Mapper文件中，必须存在<code>findList</code>的接口定义。
     * @param params
     *        条件参数
     * @return
     *        记录条件
     * @throws GenericEntityException
     *
     */
    public List<GenericValue> findList(GenericValue params) throws GenericEntityException {
        return  findList("findList",params);
    }

    /**
     * 按条查询记录集
     * @param mapperId
     *        XML Mapper文件中对应的接口名
     * @param params
     *        条件参数
     * @return
     *        记录集
     * @throws GenericEntityException
     *
     */
    public List<GenericValue> findList(String mapperId,GenericValue params) throws GenericEntityException{
        String mapper = getMapper(mapperId);
        SqlSession sqlSession = null;
        List<GenericValue> relList = FastList.newInstance();
        try {
            sqlSession = this.getSqlSession();
            relList = sqlSession.selectList(mapper, params);
        }catch (Exception e){
            throw  new GenericEntityException(e);
        }finally {
            UtilEntity.close(sqlSession);
        }
        return relList;
    }

    /**
     * 按条查询记录集,优先在Cache中查找，找到直接返回，
     *        如果找不到再到数据库查找，找到后先将结果放入缓存，再返回
     * 调用该接口，对应的XML Mapper文件中，必须存在<code>findList</code>的接口定义。
     * @param params
     *        条件参数
     * @return
     *        记录集
     * @throws GenericEntityException
     *
     */
    public List<GenericValue> findListCache(GenericValue params) throws GenericEntityException{
        List<GenericValue> listgv = getCacheLines(params);
        if(listgv != null){
            return  listgv;
        }

        listgv =  findList(params);
        if(listgv != null){
            setCacheLines(listgv);
        }
        return  listgv;
    }

    /**
     * 按条查询记录集,优先在Cache中查找，找到直接返回，
     *        如果找不到再到数据库查找，找到后先将结果放入缓存，再返回
     * @param mapperId
     *        XML Mapper文件中对应的接口名
     * @param params
     *        条件参数
     * @return
     *        记录集
     * @throws GenericEntityException
     *
     */
    public List<GenericValue> findListCache(String mapperId,GenericValue params ) throws GenericEntityException{

        List<GenericValue> listgv = getCacheLines(params);
        if(listgv != null){
            return  listgv;
        }

        listgv =  findList(mapperId, params);
        if(listgv != null){
            setCacheLines(listgv);
        }
        return  listgv;

    }


    /**
     * 按条查询记录
     * 调用该接口，对应的XML Mapper文件中，必须存在<code>findOne</code>的接口定义。
     * @param params
     *        条件参数
     * @return
     *        记录
     * @throws GenericEntityException
     *
     */
    public GenericValue findOne( GenericValue params) throws GenericEntityException {
        return findOne("findOne",params);
    }

    /**
     * 按条查询记录
     * @param params
     *        条件参数
     * @param mapperId
     *        对应的XML Mapper文件中，对应的接口名
     * @return
     *        记录
     * @throws GenericEntityException
     *
     */
    public GenericValue findOne(String mapperId, GenericValue params) throws GenericEntityException{
        String mapper = getMapper(mapperId);
        SqlSession sqlSession = null;
        GenericValue genericValue =null;
        try {
            sqlSession = this.getSqlSession();
            genericValue = sqlSession.selectOne(mapper, params);
        }catch (Exception e){
            throw  new GenericEntityException(e);
        }finally {
            UtilEntity.close(sqlSession);
        }
        return genericValue;
    }

    /**
     * 按条查询记录,优先在Cache中查找，找到直接返回，
     *        如果找不到再到数据库查找，找到后先将结果放入缓存，再返回
     * 调用该接口，对应的XML Mapper文件中，必须存在<code>findOne</code>的接口定义。
     * @param params
     *        条件参数
     *
     * @return
     *        记录
     * @throws GenericEntityException
     *
     */
    public GenericValue findOneCache( GenericValue params) throws GenericEntityException {
        GenericValue gv = getCacheLine(params);
        if(gv != null){
            return  gv;
        }

        gv =  findOne(params);
        if(gv != null){
            setCacheLine(gv);
        }
        return  gv;
    }

    /**
     * 按条查询记录,优先在Cache中查找，找到直接返回，
     *        如果找不到再到数据库查找，找到后先将结果放入缓存，再返回
     * 调用该接口，对应的XML Mapper文件中，必须存在<code>findList</code>的接口定义。
     * @param mapperId
     *        对应的XML Mapper文件中，对应的接口名
     * @param params
     *        条件参数
     * @return
     *        记录
     * @throws GenericEntityException
     *
     */
    public GenericValue findOneCache(String mapperId, GenericValue params) throws GenericEntityException{
        GenericValue gv = getCacheLine(params);
        if(gv != null){
            return  gv;
        }

        gv =  findOne(mapperId,params);
        if(gv != null){
            setCacheLine(gv);
        }
        return  gv;
    }

    /**
     * 将指定值放入缓存中
     * @param value
     */
    public GenericValue getCacheLine(GenericValue value){
        //TODO 实现缓存
        return null;
    }

    /**
     * 将指定值放入缓存中
     * @param value
     */
    public void setCacheLine(GenericValue value){
        //TODO 实现缓存
    }

    /**
     * 将指定值放入缓存中
     * @param value
     */
    public List<GenericValue> getCacheLines(GenericValue value){
        //TODO 实现缓存
        return null;
    }
    /**
     * 将指定值放入缓存中
     * @param listv
     */
    public void setCacheLines(List<GenericValue> listv){
        //TODO 实现缓存
    }

    /**
     * 获取Mapper接口的全名.
     * @param id
     * @return
     */
    protected String getMapper(String id){
        return  nameSpace+"."+id;
    }
}
