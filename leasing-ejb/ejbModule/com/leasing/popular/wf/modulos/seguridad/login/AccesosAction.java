package com.leasing.popular.wf.modulos.seguridad.login;



import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateless;
import javax.faces.convert.Converter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Identity;

import com.leasing.popular.wf.generico.view.converter.ConverterRolUsuarios;
import com.leasing.popular.wf.table.seguridad.roles.MsegRoles;
import com.leasing.popular.wf.table.seguridad.usuarios.MsegRolusuarios;
import com.leasing.popular.wf.table.seguridad.usuarios.MsegRolusuariosActionDAO;
import com.leasing.popular.wf.table.seguridad.usuarios.MsegUsuarios;
import com.leasing.popular.wf.table.seguridad.usuarios.MsegUsuariosActionDAO;

/**
 * @author       Usuario
 */
@Stateless 
@Scope(ScopeType.SESSION)
@Name("accesosAction")
@Restrict("#{identity.loggedIn}")
public class AccesosAction implements Accesos
{

	@In
	Identity identity;

	@Logger 
	private Log log;

	@PersistenceContext 
	private EntityManager em;


	@In(required=false, scope=ScopeType.SESSION) 
	private MsegUsuarios msegUsuarios;

	@Out(required=true, scope=ScopeType.SESSION) 
	private MsegUsuarios msegUsuariosSession = new MsegUsuarios(); 

	@Out(required=true, scope=ScopeType.SESSION)
	public MsegRoles msegRoles = new MsegRoles();


	private MsegRolusuarios msegRolusuariosAcceso = new MsegRolusuarios();

	@In(required=false,scope=ScopeType.SESSION)
	@Out(required=true,scope=ScopeType.SESSION)
	public List<MsegRolusuarios> listaRolUsuarioAcceso;


	@In
	private MsegRolusuariosActionDAO msegRolusuariosActionDAO; 
	@In
	private MsegUsuariosActionDAO msegUsuariosActionDAO; 



	public String acceso(){
		try{
			System.out.println("ACCESO");



			System.out.println("Entro acceso " + msegUsuariosSession.getUsuNombres());
			listaRolUsuarioAcceso = msegRolusuariosActionDAO.findByUsuRol(  msegUsuarios);
			//em.refresh(listaRolUsuarioAcceso);

			System.out.println("listaRolUsuario.size()++ " + listaRolUsuarioAcceso.size());
			if(listaRolUsuarioAcceso.size() == 0){
				return "uno";
			}else if (listaRolUsuarioAcceso.size() == 1) {

				msegRoles = ((MsegRolusuarios)listaRolUsuarioAcceso.get(0)).getMsegRoles();
				msegUsuariosSession = msegUsuariosActionDAO.findById(  msegUsuarios.getUsuCedula());
				System.out.println("Entra con un solo rol ---" + msegRoles.getRolNombreRol()+":"+msegUsuarios.getUsuNombres());
				System.out.println("msegUsuarios.getUsuCedula() " + msegUsuarios.getUsuCedula());
				return "tres";

			}else{
				System.out.println("Entra con un mas de un acceso " + msegUsuarios.getUsuNombres());
				return "dos";
			}
		}catch (RuntimeException e) {
			e.printStackTrace();
			// TODO: handle exception
			System.out.println(e.getMessage());
			// TODO: handle exception
		}
		return "dos"; 

	}

	public void accesoRol(){ 
		msegRoles = msegRolusuariosAcceso.getMsegRoles();
		msegUsuariosSession = msegUsuariosActionDAO.findById(  msegUsuarios.getUsuCedula());
		System.out.println("Ingreso con el acceso " + msegRoles.getRolNombreRol());

	}
	//1 2  8 9 3 7

	/**
	 * @return
	 * @uml.property  name="listaRolUsuarioAcceso"
	 */
	public List<MsegRolusuarios> getListaRolUsuarioAcceso() {
		return listaRolUsuarioAcceso;
	}


	/**
	 * @param  listaRolUsuario
	 * @uml.property  name="listaRolUsuarioAcceso"
	 */
	public void setListaRolUsuarioAcceso(List<MsegRolusuarios> listaRolUsuario) {
		this.listaRolUsuarioAcceso = listaRolUsuario;
	}


	public Converter getConverter() {

		if(msegUsuarios != null){
			return new ConverterRolUsuarios(msegRolusuariosActionDAO.findByUsuRol(  msegUsuarios));
		}else{
			return null;
		}
	}


	/**
	 * @return
	 * @uml.property  name="msegRolusuariosAcceso"
	 */
	public MsegRolusuarios getMsegRolusuariosAcceso() {
		return msegRolusuariosAcceso;
	}


	/**
	 * @param  msegRolusuariosAcceso
	 * @uml.property  name="msegRolusuariosAcceso"
	 */
	public void setMsegRolusuariosAcceso(MsegRolusuarios msegRolusuariosAcceso) {
		this.msegRolusuariosAcceso = msegRolusuariosAcceso;
		msegRoles  = msegRolusuariosAcceso.getMsegRoles();
		System.out.println("Varios acceso entro con " + msegRoles.getRolNombreRol());

	}

	@Remove
	public void destroy() {}



}
