package com.leasing.popular.wf.modulos.parametros.valores;

import javax.ejb.Local;

import com.leasing.popular.wf.table.parametros.valores.MpaValores;




/**
 * @author          Usuario
 */
@Local
public interface ValoresEditar
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
	public void seleccionMpaValores(MpaValores mpaValores);
	public void destroy();  
	/**
	 * @uml.property  name="mpaValoresEditar"
	 * @uml.associationEnd  
	 */
	public MpaValores getMpaValoresEditar();
	/**
	 * @param  mpaValores
	 * @uml.property  name="mpaValoresEditar"
	 */
	public void setMpaValoresEditar(MpaValores mpaValores);

 
}