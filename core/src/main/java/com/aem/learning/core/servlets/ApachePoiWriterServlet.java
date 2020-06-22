package com.aem.learning.core.servlets;

import org.osgi.framework.Constants;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

import javax.servlet.Servlet;

import org.osgi.service.component.annotations.Component;

@Component(service = Servlet.class, property = {Constants.SERVICE_DESCRIPTION + "=Excel Writer Servlet",
        "sling.servlet.paths=" + "/bin/excelwriter", "sling.servlet.methods=" + HttpConstants.METHOD_POST})
public class ApachePoiWriterServlet extends SlingAllMethodsServlet{

	private static final long serialVersionUID = -1952282100608016127L;
	
	

}
