package com.leasing.popular.wf.modulos.parametros.parametro;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.core.Events;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import com.leasing.popular.wf.table.parametros.parametro.MpaParametro;
import com.leasing.popular.wf.table.parametros.parametro.MpaParametroActionDAO;



/**
 * @author       Usuario
 */
@Stateful 
@Name("parametroEditarAction")
@Scope(ScopeType.SESSION)
@Restrict("#{identity.loggedIn}")
public class ParametroEditarAction implements ParametroEditar
{
	@PersistenceContext
	private EntityManager em;

	@In
	private   FacesMessages facesMessages;

	@Logger 
	private Log log;

	private MpaParametro mpaParametroEditar = new MpaParametro(); 
	@In
	private MpaParametroActionDAO mpaParametroActionDAO;

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
	 
			
			MpaParametro instance  = mpaParametroActionDAO.findById( mpaParametroEditar.getParCodigo());	
		//System.out.println("update ");
		if(instance != null ){ 
			//System.out.println("update ");
			updateValid = true;
			if(!mpaParametroActionDAO.isExistePara(mpaParametroEditar)){
				//System.out.println("update ");
				mpaParametroEditar = mpaParametroActionDAO.merge(mpaParametroEditar);
				//System.out.println("update fin ");
				facesMessages.add("Con éxito actualizado el parametro: \""+mpaParametroEditar.getParNombre()+"\"");
				events.raiseTransactionSuccessEvent("updateConfirmUsuario");
			}else{
				//System.out.println("no update ");
				mpaParametroEditar  = mpaParametroActionDAO.findById( mpaParametroEditar.getParCodigo());
				em.flush();
				facesMessages.add("Existe un parametro con el nombre " + mpaParametroEditar.getParNombre() +". Por favor intente nuevamente con un nombre diferente.");

			}

		}else{
			System.out.println("no update ");
			facesMessages.add("Error actualizando parametro " +mpaParametroEditar.getParNombre() +". Por favor intente nuevamente con un nombre diferente.");
		}

		
	}



	public void seleccionMpaParametro(MpaParametro mpaParametroEditar) {
		this.mpaParametroEditar = mpaParametroEditar;
	}

	/**
	 * @return
	 * @uml.property  name="mpaParametroEditar"
	 */
	public MpaParametro getMpaParametroEditar() {
		return mpaParametroEditar;
	}

	/**
	 * @param  mpaParametroEditar
	 * @uml.property  name="mpaParametroEditar"
	 */
	public void setMpaParametroEditar(MpaParametro mpaParametroEditar) {
		this.mpaParametroEditar = mpaParametroEditar;
	}

	@Remove
	@Destroy
	public void destroy() {}
}
