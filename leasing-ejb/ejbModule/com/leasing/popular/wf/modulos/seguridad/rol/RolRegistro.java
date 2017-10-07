package com.leasing.popular.wf.modulos.seguridad.rol;

import javax.ejb.Local;

import com.leasing.popular.wf.table.seguridad.roles.MsegRoles;




/**
 * @author          Usuario
 */
@Local
public interface RolRegistro{
	/**
	 * @uml.property  name="msegRoles"
	 * @uml.associationEnd  
	 */
	public MsegRoles getMsegRoles();
	/**
	 * @param  msegRoles
	 * @uml.property  name="msegRoles"
	 */
	public void setMsegRoles(MsegRoles msegRoles);
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