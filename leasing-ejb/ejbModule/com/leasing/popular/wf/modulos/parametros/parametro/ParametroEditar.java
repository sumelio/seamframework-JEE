package com.leasing.popular.wf.modulos.parametros.parametro;

import javax.ejb.Local;

import com.leasing.popular.wf.table.parametros.parametro.MpaParametro;




/**
 * @author          Usuario
 */
@Local
public interface ParametroEditar
{ 
	/**
	 * @return
	 * @uml.property  name="updateValid"
	 */
	public boolean isUpdateValid();
	/**
	 * @param  updateValid
	 * @uml.property  name="updateValid"
	 */
	public void setUpdateValid(boolean updateValid);
	/**
	 * @return
	 * @uml.property  name="verify"
	 */
	public String getVerify();
	/**
	 * @param  verify
	 * @uml.property  name="verify"
	 */
	public void setVerify(String verify);
	public void update();
	public void seleccionMpaParametro(MpaParametro msegRoles);
	   
	public void destroy();  
	/**
	 * @uml.property  name="mpaParametroEditar"
	 * @uml.associationEnd  
	 */
	public MpaParametro getMpaParametroEditar();
   /**
 * @param  mpaTipoParametroEditar
 * @uml.property  name="mpaParametroEditar"
 */
public void setMpaParametroEditar(MpaParametro mpaTipoParametroEditar);
 
}