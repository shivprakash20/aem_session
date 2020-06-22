package com.aem.learning.core.services.impl;

import org.osgi.service.component.annotations.Component;

import com.aem.learning.core.services.SPService;

@Component(immediate=true, service=SPService.class)
public class SPServiceImpl implements SPService{

	String dataDetails = "ServiceImpl class Implementation";
	
	@Override
	public String getData() {
		
		return dataDetails;
	}

	

}
