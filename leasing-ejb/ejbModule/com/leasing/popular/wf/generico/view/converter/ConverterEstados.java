//$Id: Register.java 5579 2007-06-27 00:06:49Z gavin $
package com.leasing.popular.wf.generico.view.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.leasing.popular.wf.table.seguridad.estado.MsegEstados;

public class ConverterEstados implements Converter {

	private List<MsegEstados> listaMsegEstados;

	public ConverterEstados(List<MsegEstados> lista){
		listaMsegEstados = lista; 
	}

/*	public Object getAsObject(FacesContext arg0, UIComponent arg1, MsegEstados arg2) {
		if (arg2==null)
			return null;
		String id ="dd";// (String)arg2;
		if (id == null || id.equals("Seleccione un estado")) return null;
		MsegEstados lRet = null;  
		if (listaMsegEstados != null) {
			for (MsegEstados l : listaMsegEstados) {
				if (l.getEstNombre().equals(id)){
					lRet = l;
					break;
				}
			}
		}
		return lRet;    
	}
	*/
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2==null)
			return null;
		String id =arg2;// (String)arg2;
	   if (id == null || id.equals("Seleccione un estado")) 
			return null;
		MsegEstados lRet = null;  
		if (listaMsegEstados != null) {
			for (MsegEstados l : listaMsegEstados) {
				if (l.getEstNombre().equals(id)){
					lRet = l;
					break;
				}
			}
		}
		return lRet;    
	}
	
	

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		MsegEstados arg22;
		if (arg2==null || arg2== "Seleccione un estado") 
			return "Seleccione un estado";
	    try{
			arg22 = (MsegEstados)arg2;
		}catch (ClassCastException e) {
			return "Seleccione un estado";
		}
		return String.valueOf(arg22.getEstNombre());
	}
	
	
	
	public String getAsString(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2==null) return "Seleccione uno estado";
		String arg22 = (String)arg2;
		return String.valueOf(arg22);
	}

}
