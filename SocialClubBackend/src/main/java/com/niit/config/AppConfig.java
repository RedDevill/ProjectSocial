package com.niit.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.niit")
public class AppConfig implements TransactionManagementConfigurer {

		public ViewResolver viewResolver(){
			
			InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
			viewResolver.setViewClass(JstlView.class);
			viewResolver.setPrefix("/WebContent/");
			viewResolver.setSuffix(".html");
			return viewResolver;
		}
		
		public PlatformTransactionManager annotationDrivenTransactionManager() {
			// TODO Auto-generated method stub
			return null;
		}
}
