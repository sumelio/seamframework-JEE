package com.leasing.popular.wf.generico.controller;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class GenericoAction {
	protected ResourceBundle resourceBundle = ResourceBundle.getBundle("com.leasing.popular.wf.config.resourcesValidacion");
    
	protected static final String _OK = "ok";
	protected static final String _NO = "no";
	protected FacesContext context = FacesContext.getCurrentInstance();
	protected FacesMessage facesMessage = new FacesMessage();
	
	protected String getBundle(String resource_){
		return resourceBundle.getString(resource_);
	}
}
