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
package cn.gorun8.easyfk.base.util.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class IteratorWrapper<DEST, SRC> implements Iterator<DEST> {
    private final Iterator<? extends SRC> it;
    private boolean nextCalled;
    private DEST lastDest;
    private SRC lastSrc;

    protected IteratorWrapper(Iterator<? extends SRC> it) {
        this.it = it;
    }

    public boolean hasNext() {
        if (nextCalled) return true;
        if (!it.hasNext()) return false;
        do {
            lastSrc = it.next();
            DEST nextDest = convert(lastSrc);
            if (isValid(lastSrc, nextDest)) {
                nextCalled = true;
                lastDest = nextDest;
                return true;
            }
        } while (it.hasNext());
        return false;
    }

    public DEST next() {
        if (!nextCalled) {
            if (!hasNext()) throw new NoSuchElementException();
        }
        nextCalled = false;
        return lastDest;
    }

    public void remove() {
        if (lastSrc != null) {
            noteRemoval(lastDest, lastSrc);
            it.remove();
            lastDest = null;
            lastSrc = null;
        } else {
            throw new IllegalStateException();
        }
    }

    protected boolean isValid(SRC src, DEST dest) {
        return true;
    }

    protected abstract void noteRemoval(DEST dest, SRC src);
    protected abstract DEST convert(SRC src);
}

