package com.aem.learning.core.workflow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.workflow.WorkflowException;
import com.day.cq.workflow.WorkflowSession;
import com.day.cq.workflow.exec.WorkItem;
import com.day.cq.workflow.exec.WorkflowData;
import com.day.cq.workflow.exec.WorkflowProcess;
import com.day.cq.workflow.metadata.MetaDataMap;

@Component(immediate = true, service = WorkflowProcess.class, property = {
		Constants.SERVICE_DESCRIPTION + "= Setting Absolute time in Instance Metadata",
		Constants.SERVICE_VENDOR + "= Study", "process.label" + "= Absolute Time Setter in Metadata" })

public class AbsoluteTimeSetter implements WorkflowProcess {

	private static final Logger logger = LoggerFactory.getLogger(AbsoluteTimeSetter.class);
	
	private final static String DATE_FORMAT ="dd-M-yyyy hh:mm:ss a";

	ResourceResolver resourceResolver;
	@Reference
	private ResourceResolverFactory resourceResolverFactory;

	private static final String STUDY_SYSTEM_USER = "sp.service";
	
	String path = StringUtils.EMPTY;

	@Override
	public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap)
			throws WorkflowException {
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put(ResourceResolverFactory.SUBSERVICE, STUDY_SYSTEM_USER);
			resourceResolver = resourceResolverFactory.getServiceResourceResolver(param);
			
			Session sesion = resourceResolver.adaptTo(Session.class);
			
			Resource pageResource = resourceResolver.getResource(path+"/jcr:content");
			Node pagePathNode = pageResource.adaptTo(Node.class);
			
			WorkflowData workflowData = workItem.getWorkflowData();
			path = workflowData.getPayload().toString();
			
			if(pagePathNode.hasProperty("scheduledDate"));{
            
				SimpleDateFormat dateFormat  = new SimpleDateFormat(DATE_FORMAT);
				String absoluteDate = dateFormat.format(pagePathNode.getProperty("scheduledDate").getValue().getDate().getTime());
				Date scheduledDate = dateFormat.parse(absoluteDate);
				long dateInMiliSecond = scheduledDate.getTime();
				
				if(dateInMiliSecond>0){
				   workItem.getWorkflowData().getMetaDataMap().put("absoluteTime", dateInMiliSecond);
				}
				pagePathNode.getSession().save();
				
			}
			sesion.save();
			
		} catch (LoginException | RepositoryException | ParseException e) {
			logger.error("Error is Setting Scheduled Date :"+e.getMessage());
		}

	}

}
