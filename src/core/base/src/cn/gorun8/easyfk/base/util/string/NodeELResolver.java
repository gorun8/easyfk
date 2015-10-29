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
package cn.gorun8.easyfk.base.util.string;

import java.beans.FeatureDescriptor;
import java.util.Iterator;
import java.util.List;

import javax.el.CompositeELResolver;
import javax.el.ELContext;
import javax.el.ELResolver;
import javax.el.PropertyNotWritableException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import javolution.util.FastList;

import org.apache.xerces.dom.NodeImpl;
import cn.gorun8.easyfk.base.util.Debug;
import cn.gorun8.easyfk.base.util.cache.UtilCache;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Defines property resolution behavior on Nodes. This resolver handles base objects that implement
 * org.w3c.dom.Node or org.apache.xerces.dom.NodeImpl. It accepts a String as a property and compiles
 * that String into an XPathExpression. The resulting value is the evaluation of the XPathExpression
 * in the context of the base Node. This resolver is currently only available in read-only mode, which
 * means that isReadOnly will always return true and {@link #setValue(ELContext, Object, Object, Object)}
 * will always throw PropertyNotWritableException. ELResolvers are combined together using {@link CompositeELResolver}
 * s, to define rich semantics for evaluating an expression. See the javadocs for {@link ELResolver}
 * for details.
 */
public class NodeELResolver extends ELResolver {
    private final XPath xpath;
    private final UtilCache<String, XPathExpression> exprCache = UtilCache.createUtilCache("nodeElResolver.ExpressionCache");
    private static final String module = NodeELResolver.class.getName();

    /**
     * Creates a new read-only NodeELResolver.
     */
    public NodeELResolver() {
        XPathFactory factory = XPathFactory.newInstance();
        xpath = factory.newXPath();
    }

    @Override
    public Class<?> getCommonPropertyType(ELContext context, Object base) {
        return isResolvable(base) ? String.class : null;
    }

    @Override
    public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext context, Object base) {
        return null;
    }

    @Override
    public Class<?> getType(ELContext context, Object base, Object property) {
        if (context == null) {
            throw new NullPointerException("context is null");
        }
        Class<?> result = null;
        if (isResolvable(base)) {
            result = Node.class;
            context.setPropertyResolved(true);
        }
        return result;
    }

    @Override
    public Object getValue(ELContext context, Object base, Object property) {
        if (context == null) {
            throw new NullPointerException("context is null");
        }
        Object result = null;
        if (isResolvable(base)) {
            try {
                Node node = (Node) base;
                String propertyString = (String) property;
                XPathExpression expr = getXPathExpressionInstance(propertyString);
                NodeList nodeList = (NodeList) expr.evaluate(node, XPathConstants.NODESET);
                if (nodeList.getLength() == 0) {
                    return null;
                } else if (nodeList.getLength() == 1) {
                    result = nodeList.item(0);
                } else {
                    List<Node> newList = FastList.newInstance();
                    for (int i = 0; i < nodeList.getLength(); i++) {
                        newList.add(nodeList.item(i));
                    }
                    result = newList;
                }
                context.setPropertyResolved(true);
            } catch (XPathExpressionException e) {
                Debug.logError("An error occurred during XPath expression evaluation, error was: " + e, module);
            }
        }
        return result;
    }

    @Override
    public boolean isReadOnly(ELContext context, Object base, Object property) {
        if (context == null) {
            throw new NullPointerException("context is null");
        }
        if (isResolvable(base)) {
            context.setPropertyResolved(true);
        }
        return true;
    }

    @Override
    public void setValue(ELContext context, Object base, Object property, Object value) {
        if (context == null) {
            throw new NullPointerException("context is null");
        }
        if (isResolvable(base)) {
            throw new PropertyNotWritableException("resolver is read-only");
        }
    }

    private final boolean isResolvable(Object base) {
        return base != null && (base instanceof Node || base instanceof NodeImpl);
    }

    private XPathExpression getXPathExpressionInstance(String xPathString) {
        XPathExpression xpe = exprCache.get(xPathString);
        if (xpe == null) {
            synchronized (exprCache) {
                xpe = exprCache.get(xPathString);
                if (xpe == null) {
                    try {
                        xpe = xpath.compile(xPathString);
                        exprCache.put(xPathString, xpe);
                    } catch (XPathExpressionException e) {
                        Debug.logError("An error occurred during XPath expression compilation, error was: " + e, module);
                    }
                }
            }
        }
        return xpe;
    }
}
