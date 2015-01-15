package com.radchenko.contact.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.radchenko.contact.services.ConfigApplicationService;

/**
 * @author radchenko.as
 * @version 1.0
 */
@Service
public class ConfigApplicationServiceImpl implements ConfigApplicationService {

	/*@Value("${com.radchenko.contact.supportEmailAddress}")
	private String supportEmailAddress;*/
	
	@Value("${contac.manager.cssJsVersion}")
	private String cssJsVersion;
	
	/*@Override
	public String getSupportEmailAddress() {
		return supportEmailAddress;
	}*/

	@Override
	public String getCssJsVersion() {
		return cssJsVersion;
	}

}
