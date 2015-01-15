package com.radchenko.contact.components.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import com.radchenko.contact.exceptions.InvalidUserInputException;

/**
 * @author radchenko.as
 * @version 1.0
 */
@Component
public class DefaultExceptionResolver extends AbstractHandlerExceptionResolver {
	private static final Logger LOGGER = Logger.getLogger(DefaultExceptionResolver.class);
	public DefaultExceptionResolver() {
		setOrder(0);
	}
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, 
					Object handler, Exception ex) {
		try{
			Throwable causeException = ex.getCause() == null ? ex : ex.getCause();
			if(handler != null && !(causeException instanceof InvalidUserInputException) ){
				Logger.getLogger(handler.getClass()).error("", ex);
			}
			LOGGER.warn("Unsupported exception: "+ ex.getClass(), ex);
			return new ModelAndView("redirect:/error?url="+request.getRequestURI());
		} catch (Exception e) {
			LOGGER.error("Can't send error", e);
			return new ModelAndView();
		}
	}
	@Override
	protected void logException(Exception ex, HttpServletRequest request) {
		//do nothing
	}
	@Override
	protected boolean shouldApplyTo(HttpServletRequest request, Object handler) {
		return true;
	}
}
