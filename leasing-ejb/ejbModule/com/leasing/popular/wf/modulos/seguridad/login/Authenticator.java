package com.leasing.popular.wf.modulos.seguridad.login;

import javax.ejb.Local;

/**
 * @author          Usuario
 */
@Local
public interface Authenticator
{
	public boolean authenticate(); 
	/**
	 * @return
	 * @uml.property  name="valor"
	 */
	public String getValor();
    /**
	 * @param  valor
	 * @uml.property  name="valor"
	 */
    public void setValor(String valor);
   // public String acceso();
	 
}
