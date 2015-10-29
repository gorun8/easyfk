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
package cn.gorun8.easyfk.base.util.collections;

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.EmptyStackException;

/**
 * LifoSet - Set interface wrapper around a LinkedList
 *
 */
@SuppressWarnings("serial")
public class LifoSet<V> extends AbstractSet<V> implements Serializable {

    // This set's back LinkedList
    private LinkedList<V> backedList = new LinkedList<V>();
    private int maxCapacity = 10;

    /**
     * Constructs a set containing the elements of the specified
     * collection, in the order they are returned by the collection's
     * iterator.
     */
    public LifoSet() {}

    /**
     * Constructs a set containing the elements of the specified
     * collection, in the order they are returned by the collection's
     * iterator.
     *
     * @param capacity the collection whose elements are to be placed into this set.
     */
    public LifoSet(int capacity) {
        maxCapacity = capacity;
    }

    /**
     * Sets the max capacity for this LifoSet
     * @param capacity Max Size (as integer)
     */
    public void setCapactity(int capacity) {
        this.maxCapacity = capacity;
    }

    /**
     * @see java.util.Collection#size()
     */
    @Override
    public int size() {
        return backedList.size();
    }

    /**
     * @see java.util.Collection#add(java.lang.Object)
     */
    @Override
    public boolean add(V obj) {
        int index = backedList.indexOf(obj);

        if (index == -1) {
            backedList.addFirst(obj);
            while (size() > maxCapacity)
                backedList.removeLast();
        } else {
            backedList.remove(index);
            backedList.addFirst(obj);
        }
        return true;
    }

    /**
     * @see java.util.Collection#iterator()
     */
    @Override
    public Iterator<V> iterator() {
        return backedList.iterator();
    }

    // Stack Implementation (implements all Stack methods as per the java.util.Stack object

    /**
     * @see java.util.Stack#empty()
     *
     * @return true if and only if this stack contains no items; false otherwise
     */
    public boolean empty() {
        if (this.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * @see java.util.Stack#push(java.lang.Object)
     *
     * @param item The item to be pushed onto this stack
     */
    public void push(V item) {
        this.add(item);
    }

    /**
     * @see java.util.Stack#pop()
     *
     * @return The object at the top of this stack
     * @throws EmptyStackException If this stack is empty
     */
    public V pop() throws EmptyStackException {
        if (this.size() > 0) {
            return backedList.removeFirst();
        }
        throw new EmptyStackException();
    }

    /**
     * @see java.util.Stack#peek()
     *
     * @return The object at the top of this stack
     * @throws EmptyStackException If this stack is empty
     */
    public V peek() throws EmptyStackException {
        if (this.size() > 0) {
            return backedList.getFirst();
        }
        throw new EmptyStackException();
    }

    /**
     * @see java.util.Stack#search(java.lang.Object)
     *
     * @param item The desired object
     * @return The 1-based position from the top of the stack where the object is located;
     * the return value -1  indicates that the object is not on the stack
     */
    public int search(Object item) {
        int index = backedList.indexOf(item);
        if (index > -1) {
            return index + 1; // this method is 1 based (per java.util.Stack)
        }
        return -1;
    }
}

