package com.leasing.popular.wf.modulos.seguridad.usuario;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.faces.convert.Converter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.faces.FacesMessages;

import com.leasing.popular.wf.generico.view.converter.ConverterEstados;
import com.leasing.popular.wf.table.seguridad.estado.MsegEstados;
import com.leasing.popular.wf.table.seguridad.estado.MsegEstadosActionDAO;
import com.leasing.popular.wf.table.seguridad.estado.MsegEstadosDAO;
import com.leasing.popular.wf.table.seguridad.usuarios.MsegUsuarios;
import com.leasing.popular.wf.table.seguridad.usuarios.MsegUsuariosActionDAO;
import com.leasing.popular.wf.table.seguridad.usuarios.MsegUsuariosDAO;



/**
 * @author       Usuario
 */
@Stateful
@Name("usuarioRegistroAction")
@Scope(ScopeType.CONVERSATION)
@Restrict("#{identity.loggedIn}")
public class UsuarioRegistroAction implements UsuarioRegistro
{
	@PersistenceContext
	private EntityManager em;

	@In
	private   FacesMessages facesMessages;

   
	/**
	 * @uml.property  name="msegUsuariosReg"
	 * @uml.associationEnd  
	 */
	private MsegUsuarios msegUsuariosReg = new MsegUsuarios();

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
	 * @uml.property  name="msegEstadosActionDAO"
	 * @uml.associationEnd  
	 */
	@In
	private MsegEstadosActionDAO msegEstadosActionDAO;
	/**
	 * @uml.property  name="msegUsuariosActionDAO"
	 * @uml.associationEnd  
	 */
	@In
	private MsegUsuariosActionDAO msegUsuariosActionDAO;
	
	
	/**
	 * @uml.property  name="listaEstadosReg"
	 */
	private List<MsegEstados>   listaEstadosReg =new ArrayList<MsegEstados>(); 
	

	
	


	
  public void nuevoUsuario(){
	  msegUsuariosReg = new MsegUsuarios();
  }
	
	/**
	 * @return
	 * @uml.property  name="listaEstadosReg"
	 */
	public List<MsegEstados>  getListaEstadosReg() {
		listaEstadosReg = em.createQuery("select u from MsegEstados u").getResultList();//msegEstadosActionDAO.findAll(em); 
		return listaEstadosReg;
	}
	/**
	 * @param  listaEstadosReg
	 * @uml.property  name="listaEstadosReg"
	 */
	public void setListaEstadosReg(List<MsegEstados> listaEstadosReg) {
		this.listaEstadosReg = listaEstadosReg;
	}


	public Converter getConverter() { 
		listaEstadosReg = msegEstadosActionDAO.findAll( ); 
		return new ConverterEstados(listaEstadosReg);
	}

	public void register(){

		if ( msegUsuariosReg.getUsuContrasena().equals(verify) ){
			MsegUsuarios instance  = msegUsuariosActionDAO.findById( msegUsuariosReg.getUsuCedula());	
			if(instance == null ){ 
				if(!msegUsuariosActionDAO.isExisteUsuarioNombre( msegUsuariosReg)){
					registered = true;
					 msegUsuariosActionDAO.persist(msegUsuariosReg);
					facesMessages.add("Con éxito registrado como \""+msegUsuariosReg.getUsuUsuario() + "\"");

				}else {
					facesMessages.add("Existe un usuario con el nombre " + msegUsuariosReg.getUsuUsuario() + ". Por favor digite un nombre de usuario diferente.");
				}	
			}
			else{
				facesMessages.instance().addToControl("usuCedula", "Existe un usuario con cedula #{msegUsuariosReg.usuCedula}");
			}
		}else{
			facesMessages.instance().addToControl("verify", "Digite de nuevo su contraseña");
			verify = "";
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

	public void cancelar(){
		msegUsuariosReg = new MsegUsuarios();
	}

	/**
	 * @return
	 * @uml.property  name="msegUsuariosReg"
	 */
	public MsegUsuarios getMsegUsuariosReg() {
		return msegUsuariosReg;
	}

	/**
	 * @param  msegUsuariosReg
	 * @uml.property  name="msegUsuariosReg"
	 */
	public void setMsegUsuariosReg(MsegUsuarios msegUsuariosReg){
		this.msegUsuariosReg = msegUsuariosReg;
	}


	@Remove
	public void destroy() {}
}
