package net.kon.model;


import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "primaryEntityManagerFactory", 
        transactionManagerRef="primaryTransactionManager",
        basePackages = { "net.kon.model.primary" })
public class PrimaryDataSourceConfig {
	
	@Value("${datasource.primary.jndi-name}")
	private String primaryJndi;
	
	@Bean
	@Primary
    @ConfigurationProperties(prefix="datasource.primary")
    public DataSource dataSource() {
		  JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
		  dsLookup.setResourceRef(true);
		  DataSource dataSource = dsLookup.getDataSource(primaryJndi);
		  return dataSource;
//        return DataSourceBuilder.create().build();
    }
	
	
    
	@Primary
	@Bean(name = "primaryEntityManager")  
	public EntityManager entityManager(EntityManagerFactoryBuilder builder) {  
		return primaryEntityManagerFactory(builder).getObject().createEntityManager();  
	}  
	
    @Primary
    @Bean(name = "primaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(EntityManagerFactoryBuilder builder) {
    	DataSource dataSource = dataSource();
    	
    	JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    	LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
    	entityManager.setPackagesToScan(new String[]{"net.kon.model.primary"});
    	entityManager.setDataSource(dataSource);
    	entityManager.setJpaVendorAdapter(vendorAdapter);
    	entityManager.setPersistenceUnitName("oracle");
    	return entityManager;
    	
    }
    
    
    @Primary
    @Bean(name = "primaryTransactionManager")
    public PlatformTransactionManager primaryTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(primaryEntityManagerFactory(builder).getObject());
    }
	
}
