//$Id: UsuarioBuscarAction.java 5509 2007-06-25 16:19:40Z gavin $
package com.leasing.popular.wf.modulos.seguridad.usuario.bean;

import com.leasing.popular.wf.table.seguridad.usuarios.MsegRolusuarios;
 
/**
 * @author       Usuario
 */
public class BooleanCheckboxUsuRol
{
	
	
	
	public BooleanCheckboxUsuRol(MsegRolusuarios msegRolusuarios, boolean asignar){
		this.msegRolusuarios = msegRolusuarios;
	    this.asignar = asignar;
	}
	
	/**
	 * @return
	 * @uml.property  name="msegRolusuarios"
	 */
	public MsegRolusuarios getMsegRolusuarios() {
		return msegRolusuarios;
	}
	/**
	 * @param  msegRolusuarios
	 * @uml.property  name="msegRolusuarios"
	 */
	public void setMsegRolusuarios(MsegRolusuarios msegRolusuarios) {
		this.msegRolusuarios = msegRolusuarios;
	}
	/**
	 * @return
	 * @uml.property  name="asignar"
	 */
	public boolean isAsignar() {
		return asignar;
	}
	/**
	 * @param  asignar
	 * @uml.property  name="asignar"
	 */
	public void setAsignar(boolean asignar) {
		this.asignar = asignar;
	}
	/**
	 * @uml.property  name="msegRolusuarios"
	 * @uml.associationEnd  
	 */
	public MsegRolusuarios msegRolusuarios;
	/**
	 * @uml.property  name="asignar"
	 */
	private boolean asignar;
	
}