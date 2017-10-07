//$Id: Register.java 5579 2007-06-27 00:06:49Z gavin $
package com.leasing.popular.wf.generico.view.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.leasing.popular.wf.table.seguridad.estado.MsegEstados;
import com.leasing.popular.wf.table.seguridad.roles.MsegRoles;
import com.leasing.popular.wf.table.seguridad.usuarios.MsegRolusuarios;

public class ConverterRolUsuarios implements Converter {

	private List<MsegRolusuarios> listaMsegRolUsuarios;

	public ConverterRolUsuarios(List<MsegRolusuarios> lista){
		listaMsegRolUsuarios = lista; 
	}

 
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2==null)
			return null;
		String id =arg2;// (String)arg2;
	   if (id == null || id.equals("Seleccione un rol")) 
			return null;
		MsegRolusuarios lRet = null;  
		if (listaMsegRolUsuarios != null) {
			for (MsegRolusuarios l : listaMsegRolUsuarios) {
				if (l.getMsegRoles().getRolNombreRol().equals(id)){
					lRet = l;
					break;
				}
			}
		}
		return lRet;    
	}
	
	

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		MsegRolusuarios arg22;
		if (arg2==null || arg2== "Seleccione un rol" ) 
			return "Seleccione un rol";
	    try{
			arg22 = (MsegRolusuarios)arg2;
		}catch (ClassCastException e) {
			return "Seleccione un rol";
		}
		if( arg22.getMsegRoles()== null){
			return "Seleccione un rol";
		}
		return String.valueOf(arg22.getMsegRoles().getRolNombreRol());
	}
	
	
	
	public String getAsString(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2==null) return "Seleccione un rol";
		String arg22 = (String)arg2;
		return String.valueOf(arg22);
	}

}
