package com.aem.learning.core.models;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;

@Model(adaptables = SlingHttpServletRequest.class)
public class BreadcrumbModel {
	
	private static final Logger logger = LoggerFactory.getLogger(BreadcrumbModel.class);
	
	@Inject
	private Page resourcePage;
	
	@PostConstruct
	 protected void init() {
		
		logger.debug("Post Construct started");
		
		String currentPath = resourcePage.getPath();
		
		int pageDepth = resourcePage.getDepth();
		
		Page parentPage = resourcePage.getAbsoluteParent(2);
		
		String parentName = parentPage.getName();
		String navTitle = parentPage.getNavigationTitle();
		String parentPath = parentPage.getPath();
		String pageTitle = parentPage.getPageTitle();
		
		logger.debug("pageDepth"+ pageDepth);
		
	}


}
