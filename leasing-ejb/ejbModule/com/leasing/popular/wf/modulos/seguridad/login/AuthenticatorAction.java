package com.leasing.popular.wf.modulos.seguridad.login;

import static org.jboss.seam.ScopeType.SESSION;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Identity;

import com.leasing.popular.wf.table.seguridad.usuarios.MsegRolusuarios;
import com.leasing.popular.wf.table.seguridad.usuarios.MsegRolusuariosActionDAO;
import com.leasing.popular.wf.table.seguridad.usuarios.MsegUsuarios;
import com.leasing.popular.wf.table.seguridad.usuarios.MsegUsuariosActionDAO;

/**
 * @author       Usuario
 */
@Stateless
@Name("authenticator")
public class AuthenticatorAction implements Authenticator
{

	@In
	Identity identity;

	@Logger 
	private Log log;

	@PersistenceContext 
	private EntityManager em;
	
 
	
	@Out(required=false,scope=ScopeType.SESSION)
	public List<MsegRolusuarios> listaRolUsuarioAcceso;
	

	@In
	private   FacesMessages facesMessages;
	
	/**
	 * @uml.property  name="valor"
	 */
	private String valor;
	
	
	private List<MsegRolusuarios> listaRolesUsuario;

	/**
	 * @return
	 * @uml.property  name="valor"
	 */
	public String getValor() {
		return valor;
	}


	/**
	 * @param  valor
	 * @uml.property  name="valor"
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}


	/**
	 * @uml.property  name="msegUsuarios"
	 * @uml.associationEnd  
	 */
	@Out(required=false, scope = SESSION)
	private MsegUsuarios msegUsuarios;

	/**
	 * @uml.property  name="msegUsuariosHome"
	 * @uml.associationEnd  
	 */
	@In
	private MsegUsuariosActionDAO msegUsuariosActionDAO;
	/**
	 * @uml.property  name="msegRolusuariosActionDAO"
	 * @uml.associationEnd  
	 */
	@In
	private MsegRolusuariosActionDAO msegRolusuariosActionDAO; 

	public boolean authenticate(){
		List<MsegUsuarios> results = msegUsuariosActionDAO.findByLogin(  identity.getUsername(),identity.getPassword());
		valor = "0";
		log.info("authenticating {0}", identity.getUsername());
		
		
		
		
		if (results.size()==0 ){    
			
			return false;
		}else{
			msegUsuarios = (MsegUsuarios) results.get(0);
			
			if (msegUsuarios.getMsegEstados() == null || msegUsuarios.getMsegEstados().getEstCodigo().longValue() != 1 ) {
				facesMessages.instance().addToControl("username", "El usuario no está ACTIVO.");
				return false;
			}
			
			listaRolesUsuario = msegRolusuariosActionDAO.findByUsuRol(  msegUsuarios);
			System.out.println("listaRolesUsuario.size() " + listaRolesUsuario.size());
			
			
			
			if(listaRolesUsuario.size() == 1){
				valor = "1";
			
			}
			if(listaRolesUsuario.size() > 1){
				valor = "2";
			}
			if(listaRolesUsuario.size() == 0){
				valor = "3";
				facesMessages.instance().addToControl("username", "El usuario no tiene ningún rol asignado.");
				return false;
				
			}
				
			
			
			return true;
		}
	}
	
	
	/*public String acceso(){
		System.out.println();
		
		List<MsegRolusuarios> listaRolUsuario = msegRolusuariosActionDAO.findByUsuRol(  msegUsuarios);
		
		System.out.println("listaRolUsuario.size() " + listaRolUsuario.size());
		if(listaRolUsuario.size() == 0){
			return "uno";
		}else if (listaRolUsuario.size() == 1) {
			return "dos";
			
		}else{
			return "tres";
		}
	}*/

}
