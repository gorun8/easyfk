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
 * Author:hezhiping   Email:110476592@qq.com   Date: 16-1-29
 */
package cn.gorun8.easyfk.security.utils;

import cn.gorun8.easyfk.base.util.Debug;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.*;
import org.springframework.context.annotation.*;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.*;


import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.StringUtils;

public class ClassPathPermissionScanner extends ClassPathScanningCandidateComponentProvider {
    private final static String module = ClassPathPermissionScanner.class.getName();
    private boolean addToConfig = true;
    private Class<? extends Annotation> annotationClass;
    private Class<?> markerInterface;

    public ClassPathPermissionScanner(boolean useDefaultFilters) {
        super(useDefaultFilters);
    }


    public void setAddToConfig(boolean addToConfig) {
        this.addToConfig = addToConfig;
    }

    public void setAnnotationClass(Class<? extends Annotation> annotationClass) {
        this.annotationClass = annotationClass;
    }

    public void setMarkerInterface(Class<?> markerInterface) {
        this.markerInterface = markerInterface;
    }

    public void registerFilters() {
        boolean acceptAllInterfaces = true;
        if(this.annotationClass != null) {
            this.addIncludeFilter(new AnnotationTypeFilter(this.annotationClass));
            acceptAllInterfaces = false;
        }

        if(this.markerInterface != null) {
            this.addIncludeFilter(new AssignableTypeFilter(this.markerInterface) {
                protected boolean matchClassName(String className) {
                    return false;
                }
            });
            acceptAllInterfaces = false;
        }

        if(acceptAllInterfaces) {
            this.addIncludeFilter(new TypeFilter() {
                public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
                    return true;
                }
            });
        }

        this.addExcludeFilter(new TypeFilter() {
            public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
                String className = metadataReader.getClassMetadata().getClassName();
                return className.endsWith("package-info");
            }
        });
    }


    public Set<BeanDefinitionHolder> scan(String... basePackages) {
        Assert.notEmpty(basePackages, "At least one base package must be specified");
        LinkedHashSet beanDefinitions = new LinkedHashSet();
        for(int index = 0; index < basePackages.length; ++index) {
            String basePackage = basePackages[index];
            Set candidates = this.findCandidateComponents(basePackage);
            Iterator iterator = candidates.iterator();

            while(iterator.hasNext()) {
                BeanDefinition candidate = (BeanDefinition)iterator.next();
                //ScopeMetadata scopeMetadata = this.scopeMetadataResolver.resolveScopeMetadata(candidate);
               // candidate.setScope(scopeMetadata.getScopeName());
//                String beanName = this.beanNameGenerator.generateBeanName(candidate, this.registry);
//                if(candidate instanceof AbstractBeanDefinition) {
//                    this.postProcessBeanDefinition((AbstractBeanDefinition)candidate, beanName);
//                }
//
//                if(candidate instanceof AnnotatedBeanDefinition) {
//                    AnnotationConfigUtils.processCommonDefinitionAnnotations((AnnotatedBeanDefinition) candidate);
//                }
//
//                if(this.checkCandidate(beanName, candidate)) {
//                    BeanDefinitionHolder definitionHolder = new BeanDefinitionHolder(candidate, beanName);
//                    definitionHolder = AnnotationConfigUtils.applyScopedProxyMode(scopeMetadata, definitionHolder, this.registry);
//                    beanDefinitions.add(definitionHolder);
//                    this.registerBeanDefinition(definitionHolder, this.registry);
//                }
            }
        }

        return beanDefinitions;
    }



    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
    }

    protected boolean checkCandidate(String beanName, BeanDefinition beanDefinition) throws IllegalStateException {
//        if(super.checkCandidate(beanName, beanDefinition)) {
//            return true;
//        } else {
//            this.logger.warn("Skipping MapperFactoryBean with name \'" + beanName + "\' and \'" + beanDefinition.getBeanClassName() + "\' mapperInterface" + ". Bean already defined with the same name!");
//            return false;
//        }
        return true;
    }
}
