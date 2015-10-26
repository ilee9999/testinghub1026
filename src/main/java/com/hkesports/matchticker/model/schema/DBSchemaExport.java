package com.hkesports.matchticker.model.schema;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.persistence.Entity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.ClassUtils;

/**
 * @author manboyu
 *
 */
public class DBSchemaExport {
	
	private final static Log log = LogFactory.getLog(DBSchemaExport.class);
	
	private static final String SEPARATOR = File.separator;
	private static final String OUTPUT_FILEPATH = System.getProperty("user.dir") + "/src/main/java/com/hkesports/matchticker/model/schema";
	
	private static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";
	private static final String PACKAGENAME = "com.hkesports.matchticker.model";
	
	private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
	private MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(this.resourcePatternResolver);
	private final TypeFilter entityFilter = new AnnotationTypeFilter(Entity.class);
	private String resourcePattern = DEFAULT_RESOURCE_PATTERN;
	
	public static void main(String[] args) throws URISyntaxException {
	    log.info("export start.....");
		DBSchemaExport n = new DBSchemaExport();
		Configuration configuration = new Configuration();
		n.postProcessAnnotationConfiguration(configuration);
		configuration.setProperty(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
		SchemaExport export = new SchemaExport(configuration); 
		export.setOutputFile(OUTPUT_FILEPATH + SEPARATOR + "math_ticker.sql");
		export.setDelimiter(";");
		export.setFormat(true);
		export.create(true, false);
		log.info("export finish.....output to : " + OUTPUT_FILEPATH);
	}
	
	@SuppressWarnings({ "rawtypes" })
	protected void postProcessAnnotationConfiguration(Configuration config) throws HibernateException {   
        try {   
            String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + ClassUtils.convertClassNameToResourcePath(PACKAGENAME) + "/" + this.resourcePattern;   
            Resource[] resources = this.resourcePatternResolver.getResources(packageSearchPath);   
            for (int i = 0; i < resources.length; i++) {   
                Resource resource = resources[i];   
                MetadataReader metadataReader = this.metadataReaderFactory.getMetadataReader(resource);   
                if (isEntity(metadataReader)) {   
                    String classFileFullPath = resource.getURL().getPath();   
                    String basePackageResourcePath = ClassUtils.convertClassNameToResourcePath(PACKAGENAME);   
                    int startIndex = classFileFullPath.indexOf(basePackageResourcePath);   
                    final String classFilePath = classFileFullPath.substring(startIndex, classFileFullPath.length() - ClassUtils.CLASS_FILE_SUFFIX.length());   
                    Class entityClass = null;   
                    try {   
                        entityClass = ClassUtils.forName(ClassUtils.convertResourcePathToClassName(classFilePath), Thread.currentThread().getContextClassLoader());   
                    } catch (ClassNotFoundException e) {   
                        throw new HibernateException("Entity class not found during classpath scanning", e);   
                    }   
                    config.addAnnotatedClass(entityClass);   
                }   
            }   
        } catch (IOException ex) {   
            throw new HibernateException("I/O failure during classpath scanning", ex);   
        }   
    }
	
	private boolean isEntity(MetadataReader metadataReader) throws IOException {
		if (entityFilter.match(metadataReader, this.metadataReaderFactory)) {
			return true;
		}
		return false;
	}
}
