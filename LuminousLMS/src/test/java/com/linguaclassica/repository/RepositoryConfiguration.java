package com.linguaclassica.repository;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.linguaclassica")
public class RepositoryConfiguration
{
	@Bean 
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean()
	{
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setPackagesToScan("com.linguaclassica.entity");
		factoryBean.setDataSource(dataSource());
		
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setDatabase(Database.MYSQL);
		jpaVendorAdapter.setShowSql(false);
		jpaVendorAdapter.setGenerateDdl(false);
		factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
		
		return factoryBean;
	}

	@Bean
	public DataSource dataSource()
	{
		return new DriverManagerDataSource("jdbc:mysql://localhost/eolms?characterEncoding=UTF-8", "eoadmin", "arethusa232");
	}

	@Bean
	public JpaTransactionManager transactionManager()
	{
		return new JpaTransactionManager(entityManagerFactoryBean().getObject());
	}
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation()
	{
		return new PersistenceExceptionTranslationPostProcessor();
	}
}
