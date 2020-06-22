package com.aem.learning.core.workflow;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.mailer.MessageGateway;
import com.day.cq.mailer.MessageGatewayService;
import com.day.cq.workflow.WorkflowException;
import com.day.cq.workflow.WorkflowSession;
import com.day.cq.workflow.exec.WorkItem;
import com.day.cq.workflow.exec.WorkflowProcess;
import com.day.cq.workflow.metadata.MetaDataMap;

@Component(immediate = true, service = WorkflowProcess.class, property = {
		Constants.SERVICE_DESCRIPTION + "= Email Notiifcation to Approver", Constants.SERVICE_VENDOR + "= Study",
		"process.label" + "= SP Workflow Email Notification" })

public class SPEmailNotification implements WorkflowProcess {

	private static final Logger logger = LoggerFactory.getLogger(SPEmailNotification.class);

	/** Inject a MessageGatewayService */
	@Reference
	private MessageGatewayService messageGatewayService;

	@Override
	public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap)
			throws WorkflowException {
		try {

			/** Declare a MessageGateway service */
			MessageGateway<Email> messageGateway;

			/** Set up Email Message */
			Email email = new SimpleEmail();

			/** Set the mail values */
			String emailToRecipients = "hpshivprakash@outlook.com";
			/*String emailCcRecipients = "hpshivprakash@outlook.com";*/

			email.addTo(emailToRecipients);
			/*email.addCc(emailCcRecipients);*/
			email.setSubject("AEM Custom Step Mail");
			email.setFrom("hpshivprakash@gmail.com");
			email.setMsg("This message is to inform you that the CQ content has been deleted");

			/** Inject a MessageGateway Service and send the message */
			messageGateway = messageGatewayService.getGateway(Email.class);

			/** Check the logs to see that messageGateway is not null */
			messageGateway.send((Email) email);

		} catch (EmailException e) {
			logger.error("Error in Sending Email to Author :" + e.getMessage());
		}

	}

}
