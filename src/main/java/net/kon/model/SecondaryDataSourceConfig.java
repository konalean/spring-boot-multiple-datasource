package net.kon.model;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * method名字要與其他datasource config不一樣，若一樣則bean name要取不同名稱
 * 
 * @author kon
 *
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "secondaryEntityManagerFactory", 
        transactionManagerRef="secondaryTransactionManager",
        basePackages = { "net.kon.model.secondary" })
public class SecondaryDataSourceConfig {

	@Bean
    @ConfigurationProperties(prefix="datasource.secondary")
    public DataSource secondDataSource() {  
        return DataSourceBuilder.create().build();
    }
	

	@Bean(name = "secondaryEntityManager")  
	public EntityManager entityManager(EntityManagerFactoryBuilder builder) {  
		return secondaryEntityManagerFactory(builder).getObject().createEntityManager();  
	}  
	

    @Bean(name = "secondaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory(EntityManagerFactoryBuilder builder) {
    	DataSource dataSource = secondDataSource();
    	
    	JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    	LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
    	entityManager.setPackagesToScan(new String[]{"net.kon.model.secondary"});
    	entityManager.setDataSource(dataSource);
    	entityManager.setJpaVendorAdapter(vendorAdapter);
    	entityManager.setPersistenceUnitName("mysql");
    	return entityManager;
    	
    }
    
    

    @Bean(name = "secondaryTransactionManager")
    public PlatformTransactionManager secondaryTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(secondaryEntityManagerFactory(builder).getObject());
    }
}
