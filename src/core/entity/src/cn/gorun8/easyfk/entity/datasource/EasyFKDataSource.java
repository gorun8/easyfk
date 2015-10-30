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

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源，实现根据上下文环境动态确定数据源，实现数据库的读写分离。
 * 在数据库层面大都采用读写分离技术，就是一个Master数据库，多个Slave数据库。
 * Master库负责数据更新和实时数据查询，Slave库当然负责非实时数据查询。
 * 因为在实际的应用中，数据库都是读多写少（读取数据的频率高，更新数据的频率
 * 相对较少），而读取数据通常耗时比较长，占用数据库服务器的CPU较多，从而影响
 * 用户体验。我们通常的做法就是把查询从主库中抽取出来，采用多个从库，使用负载
 * 均衡，减轻每个从库的查询压力。
 * 采用读写分离技术的目标：有效减轻Master库的压力，又可以把用户查询数据的请求
 * 分发到不同的Slave库，从而保证系统的健壮性。
 * 随着网站的业务不断扩展，数据不断增加，用户越来越多，数据库的压力也就越来越大，
 * 采用传统的方式，比如：数据库或者SQL的优化基本已达不到要求，这个时候可以采用读
 * 写分离的策 略来改变现状。
 */
public class EasyFKDataSource extends AbstractRoutingDataSource {

    /**
     * 从上下文件中取出当前的数据源标识。
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return EntityGroupContext.getDataSouce();
    }
}
