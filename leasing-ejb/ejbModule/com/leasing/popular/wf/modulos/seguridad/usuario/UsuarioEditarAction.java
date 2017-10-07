package com.leasing.popular.wf.modulos.seguridad.usuario;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.faces.convert.Converter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Destroy;
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

import com.leasing.popular.wf.generico.view.converter.ConverterEstados;
import com.leasing.popular.wf.modulos.seguridad.usuario.bean.BooleanCheckboxUsuRol;
import com.leasing.popular.wf.table.seguridad.estado.MsegEstados;
import com.leasing.popular.wf.table.seguridad.estado.MsegEstadosActionDAO;
import com.leasing.popular.wf.table.seguridad.usuarios.MsegRolusuarios;
import com.leasing.popular.wf.table.seguridad.usuarios.MsegRolusuariosActionDAO;
import com.leasing.popular.wf.table.seguridad.usuarios.MsegUsuarios;
import com.leasing.popular.wf.table.seguridad.usuarios.MsegUsuariosActionDAO;



/**
 * @author       Usuario
 */
@Stateful 
@Name("usuarioEditarAction")
@Scope(ScopeType.SESSION)
@Restrict("#{identity.loggedIn}")
public class UsuarioEditarAction implements UsuarioEditar
{
	@PersistenceContext
	private EntityManager em;

	@In
	private   FacesMessages facesMessages;

	@Logger 
	private Log log;

	/**
	 * @uml.property  name="verify"
	 */
	@Out(required=false)
	private String verify;
	
	
	/**
	 * @uml.property  name="msegUsuariosSession"
	 * @uml.associationEnd  
	 */
	@In(required=false)
	private MsegUsuarios msegUsuariosSession;


	/**
	 * @uml.property  name="msegUsuariosEdit"
	 * @uml.associationEnd  
	 */
	private MsegUsuarios   msegUsuariosEdit = new MsegUsuarios();

	@In
	private Events events;

	/**
	 * @uml.property  name="listaRolUsuario"
	 */
	@DataModel
	private List<BooleanCheckboxUsuRol> listaRolUsuario = new ArrayList<BooleanCheckboxUsuRol>();


	//Contrasena

	/**
	 * @uml.property  name="usuContrasena_"
	 */
	private String usuContrasena_ = "";
	/**
	 * @uml.property  name="usuNuevaContrasena"
	 */
	private String usuNuevaContrasena = "";
	/**
	 * @uml.property  name="usuConfirmaContrasena"
	 */
	private String usuConfirmaContrasena = "";



	/**
	 * @uml.property  name="msegEstadosHome"
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
	 * @uml.property  name="msegRolusuariosActionDAO"
	 * @uml.associationEnd  
	 */
	@In
	private MsegRolusuariosActionDAO msegRolusuariosActionDAO;

	/**
	 * @uml.property  name="listaEstadosEdit"
	 */
	private List<MsegEstados>   listaEstadosEdit = new ArrayList<MsegEstados>(); 

	/**
	 * @uml.property  name="updateValid"
	 */
	private boolean updateValid;


	/**
	 * @return
	 * @uml.property  name="listaEstadosEdit"
	 */
	public List<MsegEstados>  getListaEstadosEdit() {
		listaEstadosEdit = em.createQuery("select u from MsegEstados u").getResultList();//msegEstadosActionDAO.findAll(em); 
		return listaEstadosEdit;
	}

	/**
	 * @param  listaEstadosItem
	 * @uml.property  name="listaEstadosEdit"
	 */
	public void setListaEstadosEdit(List<MsegEstados> listaEstadosItem) {
		this.listaEstadosEdit = listaEstadosItem;
	}


	public Converter getConverter() { 
		return new ConverterEstados(msegEstadosActionDAO.findAll( ));
	}



	public void invalid(){
		facesMessages.add("Por favor intente otra vez");
	}

	/**
	 * @return
	 * @uml.property  name="updateValid"
	 */
	public boolean isUpdateValid(){
		return updateValid;
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


	public void seleccionUsuario(MsegUsuarios selected)
	{ 	msegUsuariosEdit = msegUsuariosActionDAO.findById( selected.getUsuCedula());
	   listaRolUsuario  = msegRolusuariosActionDAO.findByUsu( msegUsuariosEdit);

	}



	public void updateUsuario(){
		MsegUsuarios instance  = msegUsuariosActionDAO.findById(  msegUsuariosEdit.getUsuCedula());	
		if(instance != null ){ 
			if(!msegUsuariosActionDAO.isExisteUsuarioNombre(  msegUsuariosEdit)){
				updateValid = true;
				for (BooleanCheckboxUsuRol booleanCheckboxUsuRol: listaRolUsuario) {
					MsegRolusuarios msegRolusuarios  = msegRolusuariosActionDAO.findById(  booleanCheckboxUsuRol.getMsegRolusuarios().getId());
					if (msegRolusuarios != null && !booleanCheckboxUsuRol.isAsignar()) {
						msegRolusuariosActionDAO.remove(  msegRolusuarios);
					}else if(msegRolusuarios == null && booleanCheckboxUsuRol.isAsignar()) {
						msegRolusuariosActionDAO.persist(  booleanCheckboxUsuRol.getMsegRolusuarios());
					}
				}
				msegUsuariosEdit = msegUsuariosActionDAO.merge( msegUsuariosEdit);	
				em.flush();
				facesMessages.add("Con éxito actualizado el usuario \"#{usuarioEditarAction.msegUsuariosEdit.usuNombres} #{usuarioEditarAction.msegUsuariosEdit.usuApellidos}\"");
				events.raiseTransactionSuccessEvent("updateConfirmUsuario");
			}else{
				facesMessages.add("Existe un usuario con el nombre " + msegUsuariosEdit.getUsuUsuario() + ". Por favor digite un nombre de usuario diferente.");
			}
		}
		else{
			facesMessages.instance().addToControl("usuCedula", "No existen ningun  usuario con cedula #{msegUsuariosEdit.usuCedula}");
		}
	}





	public void cancelar(){
		msegUsuariosEdit = new MsegUsuarios();
	}





	public MsegUsuarios getmsegUsuariosEdit() {
		return msegUsuariosEdit;
	}

	/**
	 * @param  msegUsuariosEdit_
	 * @uml.property  name="msegUsuariosEdit"
	 */
	public void setMsegUsuariosEdit(MsegUsuarios msegUsuariosEdit_){
		this.msegUsuariosEdit = msegUsuariosEdit_;
	}






	/**
	 * @return
	 * @uml.property  name="listaRolUsuario"
	 */
	public List<BooleanCheckboxUsuRol> getListaRolUsuario() {
		return listaRolUsuario;
	}

	/**
	 * @param  listaRolUsuario
	 * @uml.property  name="listaRolUsuario"
	 */
	public void setListaRolUsuario(List<BooleanCheckboxUsuRol> listaRolUsuario) {
		this.listaRolUsuario = listaRolUsuario;
	}

	//Contrasena
	
	
	public void updateContrasena(){

System.out.println("msegUsuariosSession " + msegUsuariosSession.getUsuCedula());
		MsegUsuarios instance  = msegUsuariosActionDAO.findById(  msegUsuariosSession.getUsuCedula());	
	    if(instance != null ){ 

			if(instance.getUsuContrasena().equals(usuContrasena_) ){
				if(usuNuevaContrasena.equals(usuConfirmaContrasena)){
					updateValid = true;
					System.out.println("usuNuevaContrasena " + usuNuevaContrasena);
					msegUsuariosSession.setUsuContrasena(usuNuevaContrasena);
					msegUsuariosSession = msegUsuariosActionDAO.merge( msegUsuariosSession);	
					facesMessages.add("Con éxito actualizado el usuario \"" + msegUsuariosSession.getUsuNombres() + "\"");
					events.raiseTransactionSuccessEvent("updateConfirmUsuario");

				}else{
					facesMessages.instance().addToControl("usuNuevaContrasena_input", "Las Contraseñas no son iguales");
				 
				}
			}else{
				facesMessages.instance().addToControl("usuContrasena_", "Contraseña incorrecta");
			}

		}else{
			facesMessages.instance().addToControl("usuContrasena_", "No existen ningun  usuario con cedula #{msegUsuariosEdit.usuCedula}");
		}

	}
	
	public void resetContrasena(){


		MsegUsuarios instance  = msegUsuariosActionDAO.findById(  msegUsuariosEdit.getUsuCedula());	
	    if(instance != null ){ 

			 
				if(usuNuevaContrasena.equals(usuConfirmaContrasena)){
					updateValid = true;
					System.out.println("usuNuevaContrasena " + usuNuevaContrasena);
					msegUsuariosEdit.setUsuContrasena(usuNuevaContrasena);
					msegUsuariosEdit = msegUsuariosActionDAO.merge( msegUsuariosEdit);	
					facesMessages.add("Con éxito actualizado el usuario \""+instance.getUsuNombres() +"\"");
					events.raiseTransactionSuccessEvent("updateConfirmUsuario");

				}else{
					facesMessages.instance().addToControl("usuNuevaContrasena_input", "Las Contraseñas no son iguales");
				}
		 
         }else{
			facesMessages.instance().addToControl("usuCedula", "No existen ningun  usuario con cedula #{msegUsuariosEdit.usuCedula}");
		}

	}
	
	

	/**
	 * @return
	 * @uml.property  name="usuContrasena_"
	 */
	public String getUsuContrasena_() {
		return usuContrasena_;
	}

	/**
	 * @param  usuContrasena_
	 * @uml.property  name="usuContrasena_"
	 */
	public void setUsuContrasena_(String usuContrasena_) {
		this.usuContrasena_ = usuContrasena_;
	}

	/**
	 * @return
	 * @uml.property  name="usuNuevaContrasena"
	 */
	public String getUsuNuevaContrasena() {
		return usuNuevaContrasena;
	}

	/**
	 * @param  usuNuevaContrasena
	 * @uml.property  name="usuNuevaContrasena"
	 */
	public void setUsuNuevaContrasena(String usuNuevaContrasena) {
		this.usuNuevaContrasena = usuNuevaContrasena;
	}

	/**
	 * @return
	 * @uml.property  name="usuConfirmaContrasena"
	 */
	public String getUsuConfirmaContrasena() {
		return usuConfirmaContrasena;
	}

	/**
	 * @param  usuConfirmaContrasena
	 * @uml.property  name="usuConfirmaContrasena"
	 */
	public void setUsuConfirmaContrasena(String usuConfirmaContrasena) {
		this.usuConfirmaContrasena = usuConfirmaContrasena;
	}

	@Remove
	@Destroy
	public void destroy() {
		if(listaRolUsuario != null){
			listaRolUsuario.clear();
			listaRolUsuario=null;
		}
	}
}
