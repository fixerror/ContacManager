package com.radchenko.contact.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import com.radchenko.contact.services.ConfigApplicationService;



/**
 * @author radchenko.as
 * @version 1.0
 */
public class InitContextListener implements ServletContextListener {
	private static final Logger LOGGER = Logger.getLogger(InitContextListener.class);
	protected WebApplicationContext getWebApplicationContext(ServletContextEvent event){
		ServletContext servletContext = event.getServletContext();
        return (WebApplicationContext) servletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
	}
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		String contextPath = context.getContextPath();
		context.setAttribute("context", contextPath);
		
		ConfigApplicationService configAppService = getWebApplicationContext(event).getBean(ConfigApplicationService.class);
		context.setAttribute("CSS_JS_VERSION", configAppService.getCssJsVersion());
		
		LOGGER.info("ContactManager has been started");
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		LOGGER.info("ContactManager has been destroyed");
	}

}
