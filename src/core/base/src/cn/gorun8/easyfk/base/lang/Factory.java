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
package cn.gorun8.easyfk.base.lang;

/** Factory interface. */
public interface Factory<R, A> {

    /** Returns an instance of <code>R</code>. This is a basic factory interface
     * that is meant to be extended. Sub-interfaces declare types for
     * <code>A</code> (the <code>getInstance</code> argument), and
     * <code>R</code> (the type returned by <code>getInstance</code>).
     *
     * @param obj Optional object to be used in <code>R</code>'s construction,
     * or to be used as a selector key
     * @return An instance of <code>R</code>
     */
    public R getInstance(A obj);

}
