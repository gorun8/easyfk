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
package cn.gorun8.easyfk.entity.util;

import cn.gorun8.easyfk.base.util.UtilIOC;
import cn.gorun8.easyfk.entity.GenericValue;
import javolution.util.FastList;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class UtilEntity {

    /**
     * 关闭sqlSession
     * @param sqlSession
     */
    public static void close(SqlSession sqlSession){
        if(sqlSession != null){
            // Manual close is not allowed over a Spring managed SqlSession
            //sqlSession.close();
        }
    }

    /**
     * 从指定数据源中获取数据库连接。取出用完后应该关闭
     * @param dataSourceName
     * @return
     * @throws SQLException
     */
    public static Connection getConnection(String dataSourceName) throws SQLException {
        DataSource dataSource = UtilIOC.getBean(dataSourceName);
        return dataSource.getConnection();
    }

    /**
     * 关闭数据库连接
     * @param con
     */
    public static void close(Connection con){
        if(con != null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭Statement
     * @param stm
     */
    public static void close(Statement stm){
        if(stm != null){
            try {
                stm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 关闭ResultSet
     * @param cls
     */
    public static void close(ResultSet cls){
        if(cls != null){
            try {
                cls.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static List<Map> toMap(List<GenericValue> gvList){
        List<Map> tmpList = FastList.newInstance();
        for(GenericValue gv : gvList){
            tmpList.add(gv.toMap());
        }

        return tmpList;
    }



}
