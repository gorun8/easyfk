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

import java.io.IOException;
import java.io.FilterWriter;
import java.io.Writer;

import cn.gorun8.easyfk.base.lang.SourceMonitored;

@SourceMonitored
public class IndentingWriter extends FilterWriter {
    protected final StringBuilder indent = new StringBuilder();
    protected final boolean doSpace;
    protected final boolean doNewline;
    protected boolean lastWasNewline;

    public static IndentingWriter makeIndentingWriter(Writer writer) {
        return writer instanceof IndentingWriter ? (IndentingWriter) writer : new IndentingWriter(writer);
    }

    public IndentingWriter(Writer out, boolean doSpace, boolean doNewline) {
        super(out);
        this.doSpace = doSpace;
        this.doNewline = doNewline;
    }

    public IndentingWriter(Writer out) {
        this(out, true, true);
    }

    public IndentingWriter newline() throws IOException {
        lastWasNewline = true;
        if (doNewline) super.write('\n');
        return this;
    }

    protected void checkAfterNewline() throws IOException {
        if (lastWasNewline) {
            if (doSpace) {
                if (doNewline) {
                    super.write(indent.toString(), 0, indent.length());
                } else {
                    super.write(' ');
                }
            }
            lastWasNewline = false;
        }
    }

    public IndentingWriter push() {
        indent.append(' ');
        return this;
    }

    public IndentingWriter pop() {
        indent.setLength(indent.length() - 1);
        return this;
    }

    public IndentingWriter space() throws IOException {
        checkAfterNewline();
        if (doSpace) super.write(' ');
        return this;
    }

    @Override
    public void write(char[] buf) throws IOException {
        write(buf, 0, buf.length);
    }

    @Override
    public void write(char[] buf, int offset, int length) throws IOException {
        int i;
        for (i = offset; i < length; i++) {
            if (buf[i] == '\n') {
                checkAfterNewline();
                super.write(buf, offset, i - offset + 1);
                offset = i + 1;
                lastWasNewline = true;
            }
        }
        checkAfterNewline();
        super.write(buf, offset, i - offset);
    }

    @Override
    public void write(int c) throws IOException {
        checkAfterNewline();
        super.write(c);
        if (c == '\n') {
            lastWasNewline = true;
            checkAfterNewline();
        }
    }

    @Override
    public void write(String s) throws IOException {
        write(s, 0, s.length());
    }

    @Override
    public void write(String s, int offset, int length) throws IOException {
        int i;
        for (i = offset; i < length; i++) {
            if (s.charAt(i) == '\n') {
                checkAfterNewline();
                super.write(s, offset, i - offset + 1);
                offset = i + 1;
                lastWasNewline = true;
            }
        }
        checkAfterNewline();
        super.write(s, offset, i - offset);
    }
}
