package com.leasing.popular.wf.modulos.parametros.valores;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.core.Events;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import com.leasing.popular.wf.table.parametros.valores.MpaValores;
import com.leasing.popular.wf.table.parametros.valores.MpaValoresActionDAO;
import com.leasing.popular.wf.table.parametros.valores.MpaValoresDAO;



/**
 * @author       Usuario
 */
@Stateful 
@Name("valoresEditarAction")
@Scope(ScopeType.SESSION)
@Restrict("#{identity.loggedIn}")
public class ValoresEditarAction implements ValoresEditar
{
	@PersistenceContext
	private EntityManager em;

	@In
	private   FacesMessages facesMessages;

	@Logger 
	private Log log;

	/**
	 * @uml.property  name="mpaValoresEditar"
	 * @uml.associationEnd  
	 */
	private MpaValores mpaValoresEditar ; 
	/**
	 * @uml.property  name="mpaValoresDAO"
	 * @uml.associationEnd  
	 */
	@In
	private MpaValoresActionDAO mpaValoresActionDAO;

	/**
	 * @uml.property  name="verify"
	 */
	@Out(required=false)
	private String verify;

	@In
	private Events events;

	/**
	 * @uml.property  name="updateValid"
	 */
	private boolean updateValid;

	/**
	 * @return
	 * @uml.property  name="updateValid"
	 */
	public boolean isUpdateValid() {
		return updateValid;
	}

	/**
	 * @param  updateValid
	 * @uml.property  name="updateValid"
	 */
	public void setUpdateValid(boolean updateValid) {
		this.updateValid = updateValid;
	}

	/**
	 * @return
	 * @uml.property  name="verify"
	 */
	public String getVerify() {
		return verify;
	}

	/**
	 * @param  verify
	 * @uml.property  name="verify"
	 */
	public void setVerify(String verify) {
		this.verify = verify;
	}

	public void update() {

		MpaValores instance  = mpaValoresActionDAO.findById(  mpaValoresEditar.getId() );	

		System.out.println("instance " +mpaValoresEditar.getVarNombre()  );
		if(instance != null ){ 
			updateValid = true;
			if(!mpaValoresActionDAO.isExisteValor(  mpaValoresEditar)){ 
				mpaValoresEditar = mpaValoresActionDAO.merge( mpaValoresEditar);
				facesMessages.add("Con éxito actualizado el valor: \""+mpaValoresEditar.getVarNombre()+"\"");
				events.raiseTransactionSuccessEvent("updateConfirmUsuario");
			}else{
				facesMessages.add("Existe un valor con este nombre \""+mpaValoresEditar.getVarNombre() + "\". Por favor intente con un nombre diferente. ");

			}
		}


	}



	public void seleccionMpaValores(MpaValores mpaValoresEditar) {
		System.out.println("select ");
		this.mpaValoresEditar = mpaValoresEditar;
	}



	/**
	 * @return
	 * @uml.property  name="mpaValoresEditar"
	 */
	public MpaValores getMpaValoresEditar() {
		return mpaValoresEditar;
	}

	/**
	 * @param  mpaValoresEditar
	 * @uml.property  name="mpaValoresEditar"
	 */
	public void setMpaValoresEditar(MpaValores mpaValoresEditar) {
		this.mpaValoresEditar = mpaValoresEditar;
	}

	@Remove
	public void destroy() {}
}
