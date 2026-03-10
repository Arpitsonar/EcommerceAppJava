package com.main;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.hibernate.HibernateTransactionManager;
import org.springframework.orm.jpa.hibernate.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
public class ORMConfig {
	@Bean
	public LocalSessionFactoryBean localSessionFactoryBean() {
		LocalSessionFactoryBean ls = new LocalSessionFactoryBean();
		ls.setDataSource(dataSource());
		ls.setPackagesToScan("com.Products", "com.Login", "com.Orders");
		ls.setHibernateProperties(properties());
		return ls;
	}
	
	
	@Bean
	public Properties properties() {
		Properties props = new Properties();
		props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		props.put("hibernate.hbm2ddl.auto", "update");
//		props.put("hibernate.show_sql", "true");
//		props.put("hibernate.format_sql", "true");
		return props;
	}



	@Bean
	public DataSource dataSource() {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName("com.mysql.cj.jdbc.Driver");
		config.setJdbcUrl("");  // Use env variables on deployment
		config.setUsername(""); // Use env variables on deployment
		config.setPassword(""); // Use env variables on deployment

		HikariDataSource ds = new HikariDataSource(config);
		return ds;
	}
	
	@Bean
	public HibernateTransactionManager hibernateTransactionManager(SessionFactory sf) {
		return new HibernateTransactionManager(sf);
	}
}
