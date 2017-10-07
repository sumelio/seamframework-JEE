package com.leasing.popular.wf.generico.view.validator;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext; 

public class ValidarGeneric {
	protected ResourceBundle resourceBundle = ResourceBundle.getBundle("com.leasing.popular.wf.config.resourcesValidacion");
	
	private String objeto = null;
	private String form = null;
	private String mensaje = null;
		
	public void ValidarGeneric(String form_, String objeto_,String mensaje_)
	{
		objeto = objeto_;
		form = form_;
		mensaje = mensaje_; 
	}
	
	public FacesMessage mensajeError(String msg) {
		FacesMessage errMsg = new FacesMessage(msg);
		errMsg.setSeverity(FacesMessage.SEVERITY_ERROR);
		return errMsg;
	}
	
	public void mensajeError(){
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = new FacesMessage();
		facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		facesMessage.setSummary(resourceBundle.getString(mensaje));
		facesMessage.setDetail("First name is blank, it must contain at least two characters");
		//"login_no_usuario"  "LoginBean:username"
		context.addMessage(form + ":" + objeto , facesMessage);
	}

}
