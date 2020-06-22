package com.aem.learning.core.servlets;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = Servlet.class, property = { Constants.SERVICE_DESCRIPTION + "=Shiva Learning Servlet",
		"sling.servlet.paths=" + "/bin/slingpostservlet", "sling.servlet.methods=" + HttpConstants.METHOD_POST })

/**
 * @author knight
 */
public class SPSlingPostServlet extends SlingAllMethodsServlet {

	private static final long serialVersionUID = 6619857588976093308L;

	private static final Logger logger = LoggerFactory.getLogger(SPSlingPostServlet.class);

	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) {
		
		try {
			
		/**
		 * Calling getResolveResolver Method present in SlingHttpServletRequest
		 * org.apache.sling.api Interface SlingHttpServletRequest
		 */
		ResourceResolver resourceResolver = request.getResourceResolver();
		/**
		 * org.apache.sling.api.resource Interface ResourceResolver
		 */
		Resource resource = resourceResolver.getResource("/content/study/study-page/servletpage");
		/**
		 * import javax.jcr.Node;
		 */
		Node node = resource.adaptTo(Node.class);
		logger.info("Full Node Information is : " + node);


			Node spNode = node.addNode("spServletNode", "nt:unstructured");
			
			spNode.setProperty("nodeName", "Shiv Prakash");
			
			resourceResolver.commit();
			
		} catch (RepositoryException e) {
			logger.error(e.getMessage(), e);
			
		} catch (PersistenceException e) {
			logger.error(e.getMessage(), e);
		}

	}

}
