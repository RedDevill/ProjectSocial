package com.niit.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

  public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	private static final Logger Logger = LoggerFactory.getLogger(AppInitializer.class);
	
	@Override
	protected Class[] getRootConfigClasses() {
		Logger.debug("Starting of the method getRootConfigClasses");
		return new Class[] {AppConfig.class,ApplicationContextConfig.class};
	}
	
	@Override
	protected Class[] getServletConfigClasses() {
		logger.debug("Starting of the metnod getServletConfigClasses");
		return new Class[] { AppConfig.class,ApplicationContextConfig.class};
	}
	
	@Override
	protected String[] getServletMappings() {
		Logger.debug("Starting of the method getServletMappings");
		return new String[] {"/"};
	}
}