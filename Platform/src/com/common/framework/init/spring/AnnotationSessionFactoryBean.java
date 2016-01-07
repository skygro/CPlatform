package com.common.framework.init.spring;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Properties;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.Oracle10gDialect;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.util.ClassUtils;

import com.common.util.data.sql.ORACEL;
import com.common.util.resource.PropertiesFactory;
import com.common.util.resource.PropertiesHelper;

public class AnnotationSessionFactoryBean extends LocalSessionFactoryBean implements ResourceLoaderAware{private static Configuration tempConfiguration;
private static final String RESOURCE_PATTERN = "**/*.class";
private Class[] annotatedClasses;
private String[] annotatedPackages;
private String[] packagesToScan;
private TypeFilter[] entityTypeFilters = { 
  new AnnotationTypeFilter((Class<? extends Annotation>) Entity.class, false), 
  new AnnotationTypeFilter((Class<? extends Annotation>) Embeddable.class, false), 
  new AnnotationTypeFilter((Class<? extends Annotation>) MappedSuperclass.class, 
  false) };
private ResourcePatternResolver resourcePatternResolver;

public AnnotationSessionFactoryBean()
{
  setHibernateProperties(null);
  this.resourcePatternResolver = new PathMatchingResourcePatternResolver();
  setConfigurationClass(AnnotationConfiguration.class);
}

public void setConfigurationClass(Class configurationClass)
{
  if ((configurationClass == null) || 
    (!AnnotationConfiguration.class
    .isAssignableFrom(configurationClass))) {
    throw new IllegalArgumentException(
      "AnnotationSessionFactoryBean only supports AnnotationConfiguration or subclasses");
  }
  super.setConfigurationClass(configurationClass);
}

public void setAnnotatedClasses(Class[] annotatedClasses)
{
  this.annotatedClasses = annotatedClasses;
}

public void setAnnotatedPackages(String[] annotatedPackages) {
  this.annotatedPackages = annotatedPackages;
}

public void setPackagesToScan(String[] packagesToScan) {
  this.packagesToScan = packagesToScan;
}

public void setEntityTypeFilters(TypeFilter[] entityTypeFilters) {
  this.entityTypeFilters = entityTypeFilters;
}

public void setResourceLoader(ResourceLoader resourceLoader) {
  this.resourcePatternResolver = (resourceLoader == null ? new PathMatchingResourcePatternResolver() : 
    ResourcePatternUtils.getResourcePatternResolver(resourceLoader));
}

protected void postProcessMappings(Configuration config) throws HibernateException
{
  AnnotationConfiguration annConfig = (AnnotationConfiguration)config;
  if (this.annotatedClasses != null) {
    for (int i = 0; i < this.annotatedClasses.length; i++) {
      annConfig.addAnnotatedClass(this.annotatedClasses[i]);
    }
  }
  if (this.annotatedPackages != null) {
    for (int i = 0; i < this.annotatedPackages.length; i++) {
      annConfig.addPackage(this.annotatedPackages[i]);
    }
  }
  scanPackages(annConfig);
}

protected void scanPackages(AnnotationConfiguration config) {
  if (this.packagesToScan != null)
    try {
      String[] arr = this.packagesToScan;
      int len = arr.length;
      for (int i = 0; i < len; i++) {
        String pkg = arr[i];
        String pattern = 
          "classpath*:" + 
          ClassUtils.convertClassNameToResourcePath(pkg) + 
          "**/*.class";
        Resource[] resources = this.resourcePatternResolver
          .getResources(pattern);
        MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(
          this.resourcePatternResolver);
        Resource[] res = resources;
        int reslen = res.length;
        for (int j = 0; j < reslen; j++) {
          Resource resource = res[j];
          if (resource.isReadable())
          {
            MetadataReader reader = readerFactory
              .getMetadataReader(resource);
            String className = reader.getClassMetadata()
              .getClassName();
            if (matchesFilter(reader, readerFactory))
              config.addAnnotatedClass(this.resourcePatternResolver
                .getClassLoader().loadClass(className));
          }
        }
      }
    }
    catch (IOException ex) {
      throw new MappingException(
        "Failed to scan classpath for unlisted classes", ex);
    } catch (ClassNotFoundException ex) {
      throw new MappingException(
        "Failed to load annotated classes from classpath", ex);
    }
}

private boolean matchesFilter(MetadataReader reader, MetadataReaderFactory readerFactory) throws IOException
{
  if (this.entityTypeFilters != null) {
    TypeFilter[] arr$ = this.entityTypeFilters;
    int len$ = arr$.length;
    for (int i$ = 0; i$ < len$; i$++) {
      TypeFilter filter = arr$[i$];
      if (filter.match(reader, readerFactory))
        return true;
    }
  }
  return false;
}

protected final void postProcessConfiguration(Configuration config) throws HibernateException
{
  postProcessAnnotationConfiguration((AnnotationConfiguration)config);
  tempConfiguration = config;
}

protected void postProcessAnnotationConfiguration(AnnotationConfiguration annotationconfiguration)
  throws HibernateException
{
}

public void setHibernateProperties(Properties hibernatePro)
{
  Properties hibernateProperties = new Properties();
  PropertiesHelper pro = 
    PropertiesFactory.getPropertiesHelper("jdbc");
  hibernateProperties.setProperty("hibernate.show_sql", pro
    .getValue("hibernate.show_sql"));
  hibernateProperties.setProperty("hibernate.cache.provider_class", 
    "org.hibernate.cache.EhCacheProvider");
  hibernateProperties.setProperty("hibernate.cache.use_query_cache", pro
    .getValue("hibernate.cache.use_query_cache"));
  hibernateProperties.setProperty("hibernate.jdbc.fetch_size", pro
    .getValue("hibernate.jdbc.fetch_size"));
  hibernateProperties.setProperty("hibernate.jdbc.batch_size", pro
    .getValue("hibernate.jdbc.batch_size"));
  String type = System.getProperty("zxDao.db");
  String dialect = Oracle10gDialect.class.getName();
  if (ORACEL.getInstance().getText().equals(type))
    dialect = Oracle10gDialect.class.getName();
 
  hibernateProperties.setProperty("hibernate.dialect", dialect);
  super.setHibernateProperties(hibernateProperties);
}

public static Configuration getTempConfiguration() {
  return tempConfiguration;
}

public static void setTempConfiguration() {
  tempConfiguration = null;
}}
