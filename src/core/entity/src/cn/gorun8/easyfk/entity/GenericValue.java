/*
 * Project:Easy Web Framework
 *
 * Description: This project is based on much more open source projects than ever before,
 *              and can be applied to mostly web development environment.
 * Author:hezhiping   Email:110476592@qq.com
 *
 *
 *==========================================================================================
 *
 */
package cn.gorun8.easyfk.entity;

import cn.gorun8.easyfk.base.util.Assert;
import cn.gorun8.easyfk.base.util.UtilMisc;
import cn.gorun8.easyfk.entity.util.ModelUtil;
import javolution.util.FastMap;

import java.io.Serializable;
import java.util.*;


public class GenericValue extends HashMap<String, Object> {

    public String getString(String key)
    {
        return (String)get(key);
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
}
