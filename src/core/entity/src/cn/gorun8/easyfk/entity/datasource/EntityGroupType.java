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
