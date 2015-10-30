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

/** A range of values. */
public interface Range<T> {
    /** Returns <code>true</code> if the lowest value in this range
     * occurs after the greatest value in <code>range</code>.
     * 
     * @param range The range to test
     * @return <code>true</code> if the lowest value in this range
     * occurs after the greatest value in <code>range</code>
     */
    boolean after(Range<T> range);

    /** Returns <code>true</code> if this range occurs after <code>value</code>.
     * 
     * @param value The value to test
     * @return <code>true</code> if this range occurs after <code>value</code>
     */
    boolean after(T value);

    /** Returns <code>true</code> if the greatest value in this range
     * occurs before the lowest value in <code>range</code>.
     * 
     * @param range The range to test
     * @return <code>true</code> if the greatest value in this range
     * occurs before the lowest value in <code>range</code>
     */
    boolean before(Range<T> range);

    /** Returns <code>true</code> if this range occurs before <code>value</code>.
     * 
     * @param value The value to test
     * @return <code>true</code> if this range occurs before <code>value</code>
     */
    boolean before(T value);

    /** Returns the ending value of this range.
     * 
     * @return Ending value
     */
    T end();

    /** Returns <code>true</code> if this range includes <code>range</code>.
     * 
     * @param range The range to test
     * @return <code>true</code> if this range includes <code>range</code>
     */
    boolean includes(Range<T> range);

    /** Returns <code>true</code> if <code>value</code> occurs within this range.
     * 
     * @param value The value to test
     * @return <code>true</code> if <code>value</code> occurs within this range
     */
    boolean includes(T value);

    /** Returns <code>true</code> if the starting and ending values are equal.
     * 
     * @return <code>true</code> if the starting and ending values are equal
     */
    boolean isPoint();

    /** Returns <code>true</code> if this range overlaps <code>range</code>.
     * 
     * @param range The range to test
     * @return <code>true</code> if this range overlaps <code>range</code>
     */
    boolean overlaps(Range<T> range);

    /** Returns the starting value of this range.
     * 
     * @return Starting value
     */
    T start();

}
