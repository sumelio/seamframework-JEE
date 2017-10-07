package com.leasing.popular.wf.modulos.seguridad.rol;

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

import com.leasing.popular.wf.table.seguridad.roles.MsegRoles;
import com.leasing.popular.wf.table.seguridad.roles.MsegRolesActionDAO;
import com.leasing.popular.wf.table.seguridad.roles.MsegRolesDAO;



/**
 * @author       Usuario
 */
@Stateful
@Name("rolRegistroAction")
@Scope(ScopeType.EVENT)
@Restrict("#{identity.loggedIn}")
public class RolRegistroAction implements RolRegistro
{
	@PersistenceContext
	private EntityManager em;

	@In
	private   FacesMessages facesMessages;

   
	/**
	 * @uml.property  name="msegRoles"
	 * @uml.associationEnd  
	 */
	private MsegRoles msegRoles = new MsegRoles();

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
	 * @uml.property  name="msegRolesActionDAO"
	 * @uml.associationEnd  
	 */
	@In
	private MsegRolesActionDAO msegRolesActionDAO;


	public void guardar(){
		registered = true;
		msegRoles.setRolCodigo( msegRolesActionDAO.genereSecuencia(em));
		if(!msegRolesActionDAO.isExisteNombre(  msegRoles)){
			msegRoles.setRolCodigo( msegRolesActionDAO.genereSecuencia(em));
			msegRolesActionDAO.persist( msegRoles);
			facesMessages.add("Con éxito registrado como \""+msegRoles.getRolNombreRol() + "\"");
			msegRoles = new MsegRoles(); 
			registered = true;
		}else{
			facesMessages.add("Existe un rol con el nombre "+ msegRoles.getRolNombreRol()+". Por favor registre un nombre diferente.");
			msegRoles = new MsegRoles(); 
		}
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

 
	
	
 


	/**
	 * @return
	 * @uml.property  name="msegRoles"
	 */
	public MsegRoles getMsegRoles() {
		return msegRoles;
	}


	/**
	 * @param  msegRoles
	 * @uml.property  name="msegRoles"
	 */
	public void setMsegRoles(MsegRoles msegRoles) {
		this.msegRoles = msegRoles;
	}


	@Remove
	public void destroy() {}
}
