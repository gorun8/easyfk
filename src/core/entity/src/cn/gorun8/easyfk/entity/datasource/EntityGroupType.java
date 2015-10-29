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
package cn.gorun8.easyfk.entity.datasource;

/**
 *  数据源的类型。系统默认提供了主数据源、从数据源、多租户三种。
 *  其中主数据源、从数据源在easyfk-spring-common.xml中进行了配置,多租户暂时没有使用。
 *  在easyfk-spring-common.xml中配置cn.gorun8.easyfk.entity.datasource.EasyFKDataSource
 *  时，targetDataSources的key名必须与EntityGroupType的key相同。因此，如果需要扩展数据源的
 *  分类，应相应当在此作相应的扩展，否则会出现找到数据源的问题。
 */
public enum  EntityGroupType {
    /**
     * 主数据源
     */
    MASTER("master"),
    /**
     * 从数据源
     */
    SLAVE("slave"),

    /**
     * 多租户
     */
    TENANT("tenant");

    private String key = "";
    private EntityGroupType(String key){
        this.key = key;
    }
    @Override
    public String toString() {
        return this.key;
    }

}
