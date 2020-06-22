package com.aem.learning.core.models;

import org.apache.sling.models.annotations.Model;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.injectorspecific.RequestAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

@Model(adaptables = Resource.class)
public class URLValidationModel {

    private static final Logger logger = LoggerFactory.getLogger(URLValidationModel.class);

    @RequestAttribute
    private String nodeUrl;

    String theme = "Hello India";

    @PostConstruct
    protected void init() {

        logger.debug("Post Construct Started");
    }

    public String getTheme() {
        return theme;
    }

    public String getNodeUrl() {
        return nodeUrl;
    }
}