//$Id: Register.java 5579 2007-06-27 00:06:49Z gavin $
package com.leasing.popular.wf.generico.view.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.leasing.popular.wf.table.parametros.parametro.MpaParametro;

public class ConverterParametro implements Converter {

	private List<MpaParametro> listaMpaParametro;

	public ConverterParametro(List<MpaParametro> lista){
		listaMpaParametro = lista; 
	}

 
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2==null)
			return null;
		String id =arg2;// (String)arg2;
	   if (id == null || id.equals("Seleccione un parametro")) 
			return null;
		MpaParametro lRet = null;  
		if (listaMpaParametro != null) {
			for (MpaParametro l : listaMpaParametro) {
				if (l.getParNombre().equals(id)){
					lRet = l;
					break;
				}
			}
		}
		return lRet;    
	}
	
	

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		MpaParametro arg22;
		if (arg2==null || arg2== "Seleccione un parametro" ) 
			return "Seleccione un parametro";
	    try{
			arg22 = (MpaParametro)arg2;
		}catch (ClassCastException e) {
			return "Seleccione un parametro";
		}
		if( arg22.getParNombre() == null){
			return "Seleccione un parametro";
		}
		return String.valueOf(arg22.getParNombre());
	}
	
	
	
	public String getAsString(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2==null) return "Seleccione un parametro";
		String arg22 = (String)arg2;
		return String.valueOf(arg22);
	}

}
