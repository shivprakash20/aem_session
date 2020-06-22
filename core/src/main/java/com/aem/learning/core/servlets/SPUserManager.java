package com.aem.learning.core.servlets;

import java.io.IOException;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.Servlet;

import org.apache.jackrabbit.api.security.user.Authorizable;
import org.apache.jackrabbit.api.security.user.UserManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = Servlet.class, property = { Constants.SERVICE_DESCRIPTION + "=Shiva User Manager",
		"sling.servlet.paths=" + "/bin/userManager", "sling.servlet.methods=" + HttpConstants.METHOD_GET })
public class SPUserManager extends SlingSafeMethodsServlet {

	private static final long serialVersionUID = 8715016416592529987L;

	private static final Logger logger = LoggerFactory.getLogger(SPUserManager.class);
	
	/**private static final String  STUDY_SYSTEM_USER = "study_system_user";*/
	
	@Reference
	private ResourceResolverFactory resourceResolverFactory;
	
	ResourceResolver resourceResolverRequest;
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {

		try {
		    
			resourceResolverRequest = request.getResourceResolver();
			
			UserManager userManager = resourceResolverRequest.adaptTo(UserManager.class);
			Session session = resourceResolverRequest.adaptTo(Session.class);
			String UserName = session.getUserID();
			
			Authorizable authName = userManager.getAuthorizable(session.getUserID());
			String authUserName = authName.getID();
			
			/**Map<String, Object> param = new HashMap<String, Object>();
			param.put(ResourceResolverFactory.SUBSERVICE, STUDY_SYSTEM_USER);
			resourceResolverParm = resourceResolverFactory.getServiceResourceResolver(param);
			Session paramSession = resourceResolverParm.adaptTo(Session.class);
			String paramString = paramSession.getUserID();*/
			
		    // Writing the entire JSON string on the browser
			response.getWriter().println("Current User of AEM Session Only :"+UserName);
			response.getWriter().println("Current User of AEM Using Authorizable :"+authUserName);
			
		} catch (IOException | RepositoryException e) {
         logger.error("Failed to print the Response "+ e.getMessage());
		}

	}

}
