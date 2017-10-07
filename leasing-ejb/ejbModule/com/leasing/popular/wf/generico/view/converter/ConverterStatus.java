//$Id: Register.java 5579 2007-06-27 00:06:49Z gavin $
package com.leasing.popular.wf.generico.view.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.leasing.popular.wf.table.parametros.status.MoperStatus;
  
public class ConverterStatus implements Converter {

	private List<MoperStatus> listaMoperStatus;

	public ConverterStatus(List<MoperStatus> lista){
		listaMoperStatus = lista; 
	}

 
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		MoperStatus lRet = null; 
		if(listaMoperStatus != null && listaMoperStatus.size() > 0){
			if (arg2==null)
				return null;
			String id =arg2;// (String)arg2;
			if (id == null || id.equals("Seleccione un rol")) 
				return null;
			lRet = null;  
			if (listaMoperStatus != null) {
				for (MoperStatus l : listaMoperStatus) {
					if ( l.getStaNombre().equals(id)){
						lRet = l;
						break;
					}
				}
			}
		}
		return lRet;    
	}
	
	

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		MoperStatus arg22;
		if (arg2==null || arg2== "Seleccione un valor" ) 
			return "Seleccione un valor";
	    try{
			arg22 = (MoperStatus)arg2;
		}catch (ClassCastException e) {
			return "Seleccione un valor";
		}
		if( arg22.getStaNombre()== null){
			return "Seleccione un valor";
		}
		return String.valueOf(arg22.getStaNombre());
	}
	
	
	
	public String getAsString(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2==null) return "Seleccione un valor";
		String arg22 = (String)arg2;
		return String.valueOf(arg22);
	}

}
