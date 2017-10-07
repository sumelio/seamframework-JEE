package com.leasing.popular.wf.modulos.parametros.parametro;

import javax.ejb.Local;

import com.leasing.popular.wf.table.parametros.parametro.MpaParametro;




/**
 * @author          Usuario
 */
@Local
public interface ParametroRegistro{
	/**
	 * @uml.property  name="mpaParametroReg"
	 * @uml.associationEnd  
	 */
	public MpaParametro getMpaParametroReg() ;
	/**
	 * @param  mpaParametroReg
	 * @uml.property  name="mpaParametroReg"
	 */
	public void setMpaParametroReg(MpaParametro mpaParametroReg);
	/**
	 * @param  verify
	 * @uml.property  name="verify"
	 */
	public void setVerify(String verify);
	/**
	 * @return
	 * @uml.property  name="verify"
	 */
	public String getVerify();
	public boolean isRegistered();
	public void invalid();
	public void guardar();
	public void destroy();
}