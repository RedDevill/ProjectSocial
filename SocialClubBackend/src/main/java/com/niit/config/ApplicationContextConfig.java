package com.niit.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.dao.UserDAO;
import com.niit.model.User;

@Configuration
@ComponentScan("com.niit.collaboration")
@EnableTransactionManagement
public class ApplicationContextConfig {
	
	
	@Bean(name = "dataSource")
	public DataSource getOracleDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		
		dataSource.setUsername("COLB_DB"); //Schema name
		dataSource.setPassword("root");

		Properties connectionProperties = new Properties();
		
		connectionProperties.setProperty("hibernate.dialect",
				   "org.hibernate.dialect.Oracle10gDialect");
		
		
		dataSource.setConnectionProperties(connectionProperties);
		return dataSource;
	}
/*	
	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		//sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.addAnnotatedClass(User.class);

		sessionBuilder.addAnnotatedClass(Blog.class);
		sessionBuilder.addAnnotatedClass(Chat.class);
		sessionBuilder.addAnnotatedClass(Event.class);
		sessionBuilder.addAnnotatedClass(Friend.class);
		sessionBuilder.addAnnotatedClass(Job.class);
		sessionBuilder.addAnnotatedClass(JobApplication.class);


		sessionBuilder.addAnnotatedClass(ChatForum.class);
		sessionBuilder.addAnnotatedClass(ChatForumComment.class);
		sessionBuilder.addAnnotatedClass(User.class);

		return sessionBuilder.buildSessionFactory();
	}

*/	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

		return transactionManager;
	}

	@Autowired
	@Bean(name = "userDetailsDAO")
	public UserDAO getUserDetailsDAO(SessionFactory sessionFactory) {
		return new UserDAO(sessionFactory);
	}



}
