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
package cn.gorun8.easyfk.base.lang;

/** An immutable range of values. */
@SourceMonitored
@ThreadSafe
public class ComparableRange<T extends Comparable<T>> implements Range<T>, Comparable<ComparableRange<T>> {

    protected final T start;
    protected final T end;
    protected final boolean isPoint;

    public ComparableRange(T start, T end) {
        if (start.getClass() != end.getClass()) {
            throw new IllegalArgumentException("start Class and end Class must be the same");
        }
        if (end.compareTo(start) >= 0) {
            this.start = start;
            this.end = end;
        } else {
            this.start = end;
            this.end = start;
        }
        this.isPoint = start.equals(end);
    }

    @Override
    public boolean after(Range<T> range) {
        return this.start.compareTo(range.end()) > 0;
    }

    @Override
    public boolean after(T value) {
        return this.start.compareTo(value) > 0;
    }

    @Override
    public boolean before(Range<T> range) {
        return this.end.compareTo(range.start()) < 0;
    }

    @Override
    public boolean before(T value) {
        return this.end.compareTo(value) < 0;
    }

    @Override
    public T end() {
        return this.end;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        try {
            ComparableRange<?> that = (ComparableRange<?>) obj;
            return this.start.equals(that.start()) && this.end.equals(that.end());
        } catch (Exception e) {}
        return false;
    }

    public int compareTo(ComparableRange<T> range) {
        if (this == range) {
            return 0;
        }
        return this.start.equals(range.start()) ? this.end.compareTo(range.end()) : this.start.compareTo(range.start());
    }

    @Override
    public boolean includes(Range<T> range) {
        return this.includes(range.start()) && this.includes(range.end());
    }

    @Override
    public boolean includes(T value) {
        if (this.isPoint) {
            return value.equals(this.start());
        }
        return value.compareTo(this.start()) >= 0 && value.compareTo(this.end()) <= 0;
    }

    @Override
    public boolean isPoint() {
        return this.isPoint;
    }

    @Override
    public boolean overlaps(Range<T> range) {
        return range.includes(this.start()) || range.includes(this.end()) || this.includes(range);
    }

    @Override
    public T start() {
        return this.start;
    }

    @Override
    public String toString() {
        return this.start + " - " + this.end;
    }
}
