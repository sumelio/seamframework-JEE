package com.leasing.popular.wf.modulos.negocio.persona;

import javax.ejb.Local;

import com.leasing.popular.wf.table.negocio.persona.McomPersonas;




/**
 * @author          Usuario
 */
@Local
public interface PerRegistro{
	/**
	 * @uml.property  name="mcomPersonasReg"
	 * @uml.associationEnd  
	 */
	public McomPersonas getMcomPersonasReg();
	/**
	 * @param  msegRoles
	 * @uml.property  name="mcomPersonasReg"
	 */
	public void setMcomPersonasReg(McomPersonas msegRoles);
	public boolean isRegistered();
	public void seleccionPersona(McomPersonas mcomPersonas);
	public void nuevo();
	public void invalid();
	public void guardar(); 
	public boolean isEdicion();
	public void destroy();

}