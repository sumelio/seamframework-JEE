package com.leasing.popular.wf.modulos.seguridad.rol;

import java.util.ArrayList;
import java.util.List;

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
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.core.Events;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import com.leasing.popular.wf.modulos.seguridad.rol.bean.BooleanCheckboxRolMod;
import com.leasing.popular.wf.table.seguridad.modulo.MsegModulos;
import com.leasing.popular.wf.table.seguridad.modulo.MsegModulosActionDAO;
import com.leasing.popular.wf.table.seguridad.modulo.MsegModulosDAO;
import com.leasing.popular.wf.table.seguridad.roles.MsegRoles;
import com.leasing.popular.wf.table.seguridad.roles.MsegRolesActionDAO;
import com.leasing.popular.wf.table.seguridad.roles.MsegRolesDAO;
import com.leasing.popular.wf.table.seguridad.roles.MsegRolmodulos;
import com.leasing.popular.wf.table.seguridad.roles.MsegRolmodulosActionDAO;
import com.leasing.popular.wf.table.seguridad.roles.MsegRolmodulosDAO;



/**
 * @author       Usuario
 */
@Stateful 
@Name("rolEditarAction")
@Scope(ScopeType.SESSION)
@Restrict("#{identity.loggedIn}")
public class RolEditarAction implements RolEditar
{
	@PersistenceContext
	private EntityManager em;

	@In
	private   FacesMessages facesMessages;

	@Logger 
	private Log log;

	/**
	 * @uml.property  name="msegRolesEditar"
	 * @uml.associationEnd  
	 */
	private MsegRoles msegRolesEditar = new MsegRoles(); 
	/**
	 * @uml.property  name="msegRolesDAO"
	 * @uml.associationEnd  
	 */
	@In
	private MsegRolesActionDAO msegRolesActionDAO;
	/**
	 * @uml.property  name="msegModulosHome"
	 * @uml.associationEnd  
	 */
	@In
	private MsegModulosActionDAO msegModulosActionDAO;
	
	@In
	private MsegRolmodulosActionDAO msegRolmodulosActionDAO;

	@Out(required=false)
	private String verify;

	@In
	private Events events;


	@Out
	List<MsegModulos> listaModuloRol ;

 
	@DataModel
	private List<MsegRolmodulos>  listaModuloRolM;


	/**
	 * @uml.property  name="listaChecked"
	 */
	@DataModel
	private List<BooleanCheckboxRolMod>  listaChecked;

	/**
	 * @uml.property  name="updateValid"
	 */
	private boolean updateValid;





	/**
	 * @return
	 * @uml.property  name="listaModuloRolM"
	 */
	public List<MsegRolmodulos> getListaModuloRolM() {
		return listaModuloRolM;
	}





	/**
	 * @param  listaModuloRolM
	 * @uml.property  name="listaModuloRolM"
	 */
	public void setListaModuloRolM(List<MsegRolmodulos> listaModuloRolM) {
		this.listaModuloRolM = listaModuloRolM;
	}





	

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
		MsegRoles instance  = msegRolesActionDAO.findById( msegRolesEditar.getRolCodigo());	
		if(instance != null ){ 
			updateValid = true;
			if(!msegRolesActionDAO.isExisteNombre( msegRolesEditar)){

				msegRolmodulosActionDAO.persistList( listaModuloRolM);
				msegRolesEditar = msegRolesActionDAO.merge(msegRolesEditar);
				facesMessages.add("Con éxito actualizado el rol: \""+msegRolesEditar.getRolNombreRol()+"\"");
				events.raiseTransactionSuccessEvent("updateConfirmUsuario");
			}else{
				facesMessages.add("Existe un rol con el nombre "+ msegRolesEditar.getRolNombreRol()+". Por favor registre un nombre diferente.");
			}
		}
	}

	public void seleccionRol(MsegRoles msegRoles) {
		this.msegRolesEditar = msegRoles;
		listaModuloRol = msegModulosActionDAO.findAll( );
		listaModuloRolM = msegRolmodulosActionDAO.findRol(msegRolesEditar);
		listaChecked = new ArrayList<BooleanCheckboxRolMod>(); 
		for (MsegRolmodulos msegRolmodulos: listaModuloRolM) {
			listaChecked.add(new BooleanCheckboxRolMod(msegRolmodulos));
		} 
	}
	/**
	 * @return
	 * @uml.property  name="msegRolesEditar"
	 */
	public MsegRoles getMsegRolesEditar() {
		return msegRolesEditar;
	}

	/**
	 * @param  msegRolesEditar
	 * @uml.property  name="msegRolesEditar"
	 */
	public void setMsegRolesEditar(MsegRoles msegRolesEditar) {
		this.msegRolesEditar = msegRolesEditar;
	}


	/**
	 * @return
	 * @uml.property  name="listaChecked"
	 */
	public List<BooleanCheckboxRolMod> getListaChecked() {
		return listaChecked;
	}

	/**
	 * @param  listaChecked
	 * @uml.property  name="listaChecked"
	 */
	public void setListaChecked(List<BooleanCheckboxRolMod> listaChecked) {
		this.listaChecked = listaChecked;
	}



	@Remove
	public void destroy() {}
}
