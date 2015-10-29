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
package cn.gorun8.easyfk.base.util;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import cn.gorun8.easyfk.base.lang.ComparableRange;

/** An immutable range of dates. This class is here for backward compatibility -
 * new code should use <code>ComparableRange&lt;Date&gt;</code> instead.
 */
@SuppressWarnings("serial")
public class DateRange extends ComparableRange<Date> implements Serializable {
    /** A <code>Date</code> instance initialized to the earliest possible date.*/
    public static final Date MIN_DATE = UtilDateTime.getEarliestDate();
    /** A <code>Date</code> instance initialized to the latest possible date.*/
    public static final Date MAX_DATE = UtilDateTime.getLatestDate();
    /** A <code>DateRange</code> instance initialized to the widest possible range of dates.*/
    public static final DateRange FullRange = new DateRange(MIN_DATE, MAX_DATE);

    protected static Date timestampToDate(Date date) {
        // Testing for equality between a Date instance and a Timestamp instance
        // will always return false.
        if (date instanceof Timestamp) {
            return new Date(date.getTime());
        }
        return date;
    }

    /**
     * @param start If null, defaults to <a href="#MIN_DATE">MIN_DATE</a>
     * @param end If null, defaults to <a href="#MAX_DATE">MAX_DATE</a>
     */
    public DateRange(Date start, Date end) {
        super(start == null ? MIN_DATE : timestampToDate(start), end == null ? MAX_DATE : timestampToDate(end));
    }

    @Override
    public boolean after(Date date) {
        return super.after(timestampToDate(date));
    }

    @Override
    public boolean before(Date date) {
        return super.before(timestampToDate(date));
    }

    /** Returns this range's duration as a millisecond value.
     * @return Range duration in milliseconds
     */
    public long durationInMillis() {
        return this.end.getTime() - this.start.getTime();
    }

    @Override
    public Date end() {
        return (Date) this.end.clone();
    }

    /** Returns the ending date of this range as a <code>Timestamp</code> instance.
     * @return Range ending date <code>Timestamp</code>
     */
    public Timestamp endStamp() {
        return new Timestamp(this.end.getTime());
    }

    /** Returns <code>true</code> if <code>date</code> occurs within this range.
     * @param date
     * @return <code>true</code> if <code>date</code> occurs within this range
     */
    public boolean includesDate(Date date) {
        return super.includes(timestampToDate(date));
    }

    /** Returns <code>true</code> if <code>start</code> and <code>end</code>
     * intersect this range.
     * @param start If null, defaults to <a href="#MIN_DATE">MIN_DATE</a>
     * @param end If null, defaults to <a href="#MAX_DATE">MAX_DATE</a>
     * @return <code>true</code> if <code>start</code> and <code>end</code>
     * intersect this range
     */
    public boolean intersectsRange(Date start, Date end) {
        return intersectsRange(new DateRange(start, end));
    }

    /** Returns <code>true</code> if <code>range</code> intersects this range.
     * @param range
     * @return <code>true</code> if <code>range</code> intersects this range
     */
    public boolean intersectsRange(DateRange range) {
        if (isPoint() && range.isPoint() && this.start.equals(range.start)) {
            return true;
        }
        return !before(range) && !after(range);
    }

    @Override
    public Date start() {
        return (Date) this.start.clone();
    }

    /** Returns the starting date of this range as a <code>Timestamp</code> instance.
     * @return Range starting date <code>Timestamp</code>
     */
    public Timestamp startStamp() {
        return new Timestamp(this.start.getTime());
    }
}
