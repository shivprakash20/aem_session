package com.aem.learning.core.models;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.learning.core.services.SPService;

@Model(adaptables = Resource.class)

public class SlingModelExam {
	
 @Inject @Named("model") @Default(values = "NO Model Value")
 private String modelField;
 
 private static final Logger logger = LoggerFactory.getLogger(SlingModelExam.class);
 
 String finalValue = StringUtils.EMPTY;
 
 String serviceTitle = StringUtils.EMPTY;
 
 String industryTitle = StringUtils.EMPTY;

// Service calling in Model
 @Inject
 private SPService spService;
 
 @PostConstruct
 protected void  init(){
	 
	 String serviceVar = spService.getData();
	 
	 finalValue = "This is final Output " + modelField +" "+ " " + serviceVar;
	 logger.info("Final Value of Model is :" + finalValue);
	 
 }

public String getModelField() {
	return modelField;
}

public String getFinalValue() {
	return finalValue;
}
 
 
  
}
