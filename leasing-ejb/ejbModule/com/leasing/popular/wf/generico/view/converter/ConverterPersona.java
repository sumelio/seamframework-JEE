//$Id: Register.java 5579 2007-06-27 00:06:49Z gavin $
package com.leasing.popular.wf.generico.view.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.leasing.popular.wf.table.negocio.persona.McomPersonas;

public class ConverterPersona implements Converter {

	private List<McomPersonas> listaMcomPersonas;

	public ConverterPersona(List<McomPersonas> lista){
		listaMcomPersonas = lista; 
	}

 
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2==null)
			return null;
		String id =arg2;// (String)arg2;
	   if (id == null || id.equals("Seleccione un proveedor")) 
			return null;
		McomPersonas lRet = null;  
		if (listaMcomPersonas != null) {
			for (McomPersonas l : listaMcomPersonas) {
				if ((   l.getId().getPerIdentificacion()+" - "+l.getPerNombres()+" "+l.getPerApellidos()).equals(id)){
					lRet = l;
					break;
				}
			}
		}
		return lRet;    
	}
	
	

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		McomPersonas arg22;
		if (arg2==null || arg2== "Seleccione un proveedor" ) 
			return "Seleccione un proveedor";
	    try{
			arg22 = (McomPersonas)arg2;
		}catch (ClassCastException e) {
			return "Seleccione un proveedor";
		}
		if( arg22.getPerNombres() == null){
			return "Seleccione un proveedor";
		}
		return String.valueOf(arg22.getId().getPerIdentificacion()+" - "+arg22.getPerNombres()+" "+arg22.getPerApellidos());
	}
	
	
	
	public String getAsString(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2==null) return "Seleccione un proveedor";
		String arg22 = (String)arg2;
		return String.valueOf(arg22);
	}

}
