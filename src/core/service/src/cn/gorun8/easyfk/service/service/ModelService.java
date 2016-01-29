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
package cn.gorun8.easyfk.service.service;

import cn.gorun8.easyfk.base.util.Debug;
import javolution.util.FastMap;


//import javax.wsdl.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Generic Service Model Class
 */
@SuppressWarnings("serial")
public class ModelService extends AbstractMap<String, Object> implements Serializable {
    private static final Field[] MODEL_SERVICE_FIELDS;
    private static final Map<String, Field> MODEL_SERVICE_FIELD_MAP = FastMap.newInstance();
    static {
        MODEL_SERVICE_FIELDS = ModelService.class.getFields();
        for (Field field: MODEL_SERVICE_FIELDS) {
            MODEL_SERVICE_FIELD_MAP.put(field.getName(), field);
        }
    }
    public static final String module = ModelService.class.getName();

    ;

    public static final String resource = "ServiceErrorUiLabels";

    /** The name of this service */
    public String name;

    /** The location of the definition this service */
    public String definitionLocation;

    /** The description of this service */
    public String description;

    /** The name of the service engine */
    public String engineName;

    /** The namespace of this service */
    public String nameSpace;

    /** The package name or location of this service */
    public String location;

    /** The method or function to invoke for this service */
    public String invoke;


    /** Does this service require authorization */
    public boolean auth;

    /** Can this service be exported via RPC, RMI, SOAP, etc */
    public boolean export;

    /** Enable verbose debugging when calling this service */
    public boolean debug;

    /** Validate the context info for this service */
    public boolean validate;



    public ModelService() {}

    public ModelService(ModelService model) {
        this.name = model.name;
        this.definitionLocation = model.definitionLocation;
        this.description = model.description;
        this.engineName = model.engineName;
        this.nameSpace = model.nameSpace;
        this.location = model.location;
        this.invoke = model.invoke;
        this.auth = model.auth;
        this.export = model.export;
        this.validate = model.validate;
    }

    @Override
    public Object get(Object name) {
        Field field = MODEL_SERVICE_FIELD_MAP.get(name.toString());
        if (field != null) {
            try {
                return field.get(this);
            } catch (IllegalAccessException e) {
                return null;
            }
        }
        return null;
    }

    private final class ModelServiceMapEntry implements Entry<String, Object> {
        private final Field field;

        protected ModelServiceMapEntry(Field field) {
            this.field = field;
        }

        public String getKey() {
            return field.getName();
        }

        public Object getValue() {
            try {
                return field.get(ModelService.this);
            } catch (IllegalAccessException e) {
                return null;
            }
        }

        public Object setValue(Object value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int hashCode() {
            return field.hashCode() ^ System.identityHashCode(ModelService.this);
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof ModelServiceMapEntry)) return false;
            ModelServiceMapEntry other = (ModelServiceMapEntry) o;
            return field.equals(other.field) && ModelService.this == other.getModelService();
        }

        private ModelService getModelService() {
            return ModelService.this;
        }
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return new AbstractSet<Entry<String, Object>>() {
            @Override
            public int size() {
                return MODEL_SERVICE_FIELDS.length;
            }

            @Override
            public Iterator<Entry<String, Object>> iterator() {
                return new Iterator<Entry<String, Object>>() {
                    private int i = 0;

                    public boolean hasNext() {
                        return i < MODEL_SERVICE_FIELDS.length;
                    }

                    public Entry<String, Object> next() {
                        return new ModelServiceMapEntry(MODEL_SERVICE_FIELDS[i++]);
                    }

                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }

    @Override
    public Object put(String o1, Object o2) {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append(name).append("::");
        buf.append(definitionLocation).append("::");
        buf.append(description).append("::");
        buf.append(engineName).append("::");
        buf.append(nameSpace).append("::");
        buf.append(location).append("::");
        buf.append(invoke).append("::");
        buf.append(auth).append("::");
        buf.append(export).append("::");
        buf.append(validate).append("::");

        return buf.toString();
    }

    public String debugInfo() {
        if (debug || Debug.verboseOn()) {
            return " [" + this.toString() + "]";
        }
        return "";
    }


}
