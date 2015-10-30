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
package cn.gorun8.easyfk.base.conversion;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/** Net Converter classes. */
public class NetConverters implements ConverterLoader {
    public static final String module = NetConverters.class.getName();

    public static class StringToInetAddress extends AbstractConverter<String, InetAddress> {
        public StringToInetAddress() {
            super(String.class, InetAddress.class);
        }

        public InetAddress convert(String obj) throws ConversionException {
            try {
                return InetAddress.getByName(obj);
            } catch (IOException e) {
                throw (ConversionException) new ConversionException(e.getMessage()).initCause(e);
            }
        }
    }

    public static class InetAddressToString extends AbstractConverter<InetAddress, String> {
        public InetAddressToString() {
            super(InetAddress.class, String.class);
        }

        public String convert(InetAddress obj) throws ConversionException {
            String hostName = obj.getHostName();
            if (hostName != null) return hostName;
            return obj.getHostAddress();
        }
    }

    public static class StringToURI extends AbstractConverter<String, URI> {
        public StringToURI() {
            super(String.class, URI.class);
        }

        public URI convert(String obj) throws ConversionException {
            try {
                return new URI(obj);
            } catch (URISyntaxException e) {
                throw (ConversionException) new ConversionException(e.getMessage()).initCause(e);
            }
        }
    }

    public static class URIToString extends AbstractConverter<URI, String> {
        public URIToString() {
            super(URI.class, String.class);
        }

        public String convert(URI obj) throws ConversionException {
            return obj.toString();
        }
    }

    public static class StringToURL extends AbstractConverter<String, URL> {
        public StringToURL() {
            super(String.class, URL.class);
        }

        public URL convert(String obj) throws ConversionException {
            try {
                return new URL(obj);
            } catch (MalformedURLException e) {
                throw (ConversionException) new ConversionException(e.getMessage()).initCause(e);
            }
        }
    }

    public static class URLToString extends AbstractConverter<URL, String> {
        public URLToString() {
            super(URL.class, String.class);
        }

        public String convert(URL obj) throws ConversionException {
            return obj.toString();
        }
    }

    public static class URIToURL extends AbstractConverter<URI, URL> {
        public URIToURL() {
            super(URI.class, URL.class);
        }

        public URL convert(URI obj) throws ConversionException {
            try {
                return obj.toURL();
            } catch (MalformedURLException e) {
                throw (ConversionException) new ConversionException(e.getMessage()).initCause(e);
            }
        }
    }

    public static class URLToURI extends AbstractConverter<URL, URI> {
        public URLToURI() {
            super(URL.class, URI.class);
        }

        public URI convert(URL obj) throws ConversionException {
            try {
                return obj.toURI();
            } catch (URISyntaxException e) {
                throw (ConversionException) new ConversionException(e.getMessage()).initCause(e);
            }
        }
    }

    public void loadConverters() {
        Converters.loadContainedConverters(NetConverters.class);
    }
}
