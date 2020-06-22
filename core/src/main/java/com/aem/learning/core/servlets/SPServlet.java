package com.aem.learning.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import com.aem.learning.core.constants.StudyConstants;
import com.aem.learning.core.services.SPService;
import com.aem.learning.core.utils.StudyNetwork;

@Component(service = Servlet.class, property = { Constants.SERVICE_DESCRIPTION + "=Shiva Learning Servlet",
		"sling.servlet.paths=" + "/bin/readjson", "sling.servlet.methods=" + HttpConstants.METHOD_GET })
public class SPServlet extends SlingSafeMethodsServlet {

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = 3722859543804811132L;

	/**
	 * Logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(SPServlet.class);
	
	//Service calling in Servlets
	@Reference
	private SPService spService;

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {

		logger.info("Reading the data from the webservice");
		
		try {
			
		//Getting Service Details
		String serviceDetails = spService.getData();	
			
		// Getting the JSON string from the webservice
		String responseString = StudyNetwork.readJson(StudyConstants.URL);

		// Writing the entire JSON string on the browser
			response.getWriter().println(serviceDetails+" "+responseString);
			
		} catch (IOException e) {
         logger.error("Failed to print the Response "+ e);
		}

	}

}
