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
package cn.gorun8.easyfk.entity;

import cn.gorun8.easyfk.base.util.Assert;
import cn.gorun8.easyfk.base.util.UtilDateTime;
import cn.gorun8.easyfk.base.util.UtilMisc;
import cn.gorun8.easyfk.entity.util.ModelUtil;
import javolution.util.FastMap;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;


public class GenericValue extends HashMap<String, Object> {

    public GenericValue(){
        Timestamp now = UtilDateTime.nowTimestamp();
        set("lastUpdatedStamp", now);
        set("lastUpdatedTxStamp",now);
    }
    /**
     * 读取指定key的指，先按指定key读取，如果读取不再将key进行进行java命名到数据库命名的转换，再取。
     *注：会自动进行java命名到数据库命名的转换。 如：userName，会自动转换为USER_NAME。
     */
    public String getString(String key) {
        Object o = get(key);
        if(o == null) {
            String key1 = ModelUtil.javaNameToDbName(key);
            o = get(key1);
        }
        return (String)o;
    }

    public void set(String key,Object o)
    {
        String key1 = ModelUtil.javaNameToDbName(key);
        put(key1,o);
    }

    public void setNonPKFields(Map<String, ? extends Object> context,List<String> fields){
        for (String fd :fields) {
            Object v = context.get(fd);
            if(v != null){
                set(fd,v);
            }
        }
    }

    public void setNonPKFields(Map<String, ? extends Object> context){
        for (Map.Entry<String, ? extends  Object> e : context.entrySet()) {
            String key = e.getKey();
            Object v = e.getValue();
            set(key,v);
        }//end for
    }

    public boolean equals(GenericValue gv){
        for (Map.Entry<String, Object> e : gv.entrySet()) {
            String key = e.getKey();
            Object o = e.getValue();
            Object o2 = this.get(key);
            if(!o.equals(o2)){return false;}
        }

        return true;
    }

    /**
     * 产生主键，
     * @param tableName 数据表名
     * @param fieldName  ，tableName中用于存放主键的字段名。
     * @return
     * 注：参数支持Java命名规则到数据库命名的自动转换。
     */
    public String newPrimaryKey(String tableName,String fieldName){
        String id = SequenceFactory.getInstance().getNextSeqId(tableName);
        this.put(ModelUtil.javaNameToDbName(fieldName), id);
        return id;
    }

    /**
     * 将MAP转换为GenericValue。
     * 注：会自动进行java命名到数据库命名的转换。 如：userName，会自动转换为USER_NAME。
     * @param map
     * @return
     */
    public static GenericValue fromMap(Map<String, Object> map) {
        GenericValue v = new GenericValue();
        for (Map.Entry<String, Object> e : map.entrySet()) {
            v.put(ModelUtil.javaNameToDbName(e.getKey()), e.getValue());
        }
        return v;
    }

    /**
     * 将GenericValue转换为MAP。
     * 注：会自动进行数据库命名到java命名的转换。 如：USER_NAME，会自动转换为userName。
     * @return
     */
    public   Map toMap(){
        Map v = FastMap.newInstance();
        for (Map.Entry<String, Object> e : this.entrySet()) {
            v.put(ModelUtil.dbNameToVarName(e.getKey()), e.getValue());
        }
        return v;
    }
}
