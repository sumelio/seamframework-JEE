package com.leasing.popular.wf.modulos.parametros.status;

import javax.ejb.Local;

import com.leasing.popular.wf.table.parametros.status.MoperStatus;

/**
 * @author          Usuario
 */
@Local
public interface StatusRegistro{
    public MoperStatus getMoperStatusReg();
	public void setMoperStatusReg(MoperStatus moperStatusReg);
	
	public void setVerify(String verify);
	/**
	 * @return
	 * @uml.property  name="verify"
	 */
	public String getVerify();
	public boolean isRegistered();
	public void invalid();
	public void guardar();
	public void update();
	public void seleccionMoperStatus(MoperStatus moperStatus_);
	public void destroy();
}