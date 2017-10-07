package com.leasing.popular.wf.modulos.parametros.valores;

import java.util.List;

import javax.ejb.Local;
import javax.faces.convert.Converter;

import com.leasing.popular.wf.table.parametros.parametro.MpaParametro;
import com.leasing.popular.wf.table.parametros.valores.MpaValores;




/**
 * @author          Usuario
 */
@Local
public interface ValoresRegistro{

 
	/**
	 * @uml.property  name="mpaValoresReg"
	 * @uml.associationEnd  
	 */
	public MpaValores getMpaValoresReg();
	/**
	 * @param  mpaValores
	 * @uml.property  name="mpaValoresReg"
	 */
	public void setMpaValoresReg(MpaValores mpaValores);
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
	public void guardarValor();
	
	public Converter getConverter();

	/**
	 * @return
	 * @uml.property  name="listaParametros"
	 */
	public List<MpaParametro> getListaParametros();

	/**
	 * @param  listaParametros
	 * @uml.property  name="listaParametros"
	 */
	public void setListaParametros(List<MpaParametro> listaParametros);
	public void destroy();

}