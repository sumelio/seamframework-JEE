package com.leasing.popular.wf.modulos.parametros.status;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.faces.FacesMessages;

import com.leasing.popular.wf.table.parametros.parametro.MpaParametro;
import com.leasing.popular.wf.table.parametros.status.MoperStatus;
import com.leasing.popular.wf.table.parametros.status.MoperStatusActionDAO;
 
/**
 * @author       Usuario
 */
@Stateful
@Name("statusRegistroAction")
@Scope(ScopeType.SESSION)
@Restrict("#{identity.loggedIn}")
public class StatusRegistroAction implements StatusRegistro
{
	@PersistenceContext
	private EntityManager em;

	@In
	private   FacesMessages facesMessages;

   
	/**
	 * @uml.property  name="mpaParametroReg"
	 * @uml.associationEnd  
	 */
	private MoperStatus moperStatusReg = new MoperStatus();

    public MoperStatus getMoperStatusReg() {
    	System.out.println("getMoperStatusReg "+ moperStatusReg.getStaNombre()); 
    	return moperStatusReg;
	}
	public void setMoperStatusReg(MoperStatus moperStatusReg_) {
		this.moperStatusReg = moperStatusReg_;
	}










	/**
	 * @uml.property  name="verify"
	 */
    @Out(required=false)
	private String verify;

	/**
	 * @uml.property  name="registered"
	 */
	private boolean registered;


	/**
	 * @uml.property  name="mpaTipoParametroDAO"
	 * @uml.associationEnd  
	 */
	@In
	private MoperStatusActionDAO moperStatusActionDAO;


	public void guardar(){
		registered = true;
		moperStatusReg.setStaId(moperStatusActionDAO.genereSecuencia( ));
		if(!moperStatusActionDAO.isExisteStatus( moperStatusReg)){
			moperStatusActionDAO.persist( moperStatusReg);	
			facesMessages.add("Con éxito registrado como \""+moperStatusReg.getStaNombre() + "\"");
			moperStatusReg = new MoperStatus(); 
			registered = true;
		}else{
			facesMessages.add("Existe un status con el nombre " +moperStatusReg.getStaNombre() +". Por favor intente nuevamente con un nombre diferente.");
		}
	}
	public void update(){
		registered = true;
		if(!moperStatusActionDAO.isExisteStatus( moperStatusReg)){
			moperStatusReg =moperStatusActionDAO.merge(moperStatusReg);	
			facesMessages.add("Con éxito actualizado como \""+moperStatusReg.getStaNombre() + "\"");
			registered = true;
		}else{
			facesMessages.add("Existe un status con el nombre " +moperStatusReg.getStaNombre() +". Por favor intente nuevamente con un nombre diferente.");
		}
	}
	public void seleccionMoperStatus(MoperStatus moperStatus_) {
		System.out.println("moperStatus_ " + moperStatus_.getStaNombre());
		this.moperStatusReg =  moperStatusActionDAO.findById( moperStatus_.getStaId() );
		System.out.println("moperStatus_ " + moperStatusReg.getStaNombre());
	}

 
	public void invalid(){
		facesMessages.add("Por favor intente otra vez");
	}

	/**
	 * @return
	 * @uml.property  name="registered"
	 */
	public boolean isRegistered(){
		return registered;
	}

	/**
	 * @return
	 * @uml.property  name="verify"
	 */
	public String getVerify(){
		return verify;
	}

	/**
	 * @param  verify
	 * @uml.property  name="verify"
	 */
	public void setVerify(String verify){
		this.verify = verify;
	}

 
	
	
 


 


	@Remove
	public void destroy() {}
}
