package com.leasing.popular.wf.modulos.seguridad.usuario;

import java.util.List;

import javax.ejb.Local;
import javax.faces.convert.Converter;

import com.leasing.popular.wf.modulos.seguridad.usuario.bean.BooleanCheckboxUsuRol;
import com.leasing.popular.wf.table.seguridad.estado.MsegEstados;
import com.leasing.popular.wf.table.seguridad.usuarios.MsegUsuarios;




/**
 * @author          Usuario
 */
@Local
public interface UsuarioEditar
{
	public void cancelar();
	public void updateUsuario();
	public void invalid();
    /**
	 * @return
	 * @uml.property  name="verify"
	 */
    public String getVerify(); 
	/**
	 * @param  verify
	 * @uml.property  name="verify"
	 */
	public void setVerify(String verify);
	public boolean isUpdateValid();
   
    public Converter getConverter();
    public void setMsegUsuariosEdit(MsegUsuarios msegUsuariosEdit_);
    public MsegUsuarios getmsegUsuariosEdit(); 
    /**
	 * @return
	 * @uml.property  name="listaEstadosEdit"
	 */
    public  List<MsegEstados>  getListaEstadosEdit();
    /**
	 * @param  listaEstadosEdit
	 * @uml.property  name="listaEstadosEdit"
	 */
    public void setListaEstadosEdit(List<MsegEstados> listaEstadosEdit);
    public void seleccionUsuario(MsegUsuarios selected);
    /**
	 * @return
	 * @uml.property  name="listaRolUsuario"
	 */
    public List<BooleanCheckboxUsuRol> getListaRolUsuario();
    /**
	 * @param  listaRolUsuario
	 * @uml.property  name="listaRolUsuario"
	 */
    public void setListaRolUsuario(List<BooleanCheckboxUsuRol> listaRolUsuario);
    
    
	/**
	 * @return
	 * @uml.property  name="usuContrasena_"
	 */
	public String getUsuContrasena_();
	/**
	 * @param  usuContrasena_
	 * @uml.property  name="usuContrasena_"
	 */
	public void setUsuContrasena_(String usuContrasena_);
	/**
	 * @return
	 * @uml.property  name="usuNuevaContrasena"
	 */
	public String getUsuNuevaContrasena();
	/**
	 * @param  usuNuevaContrasena
	 * @uml.property  name="usuNuevaContrasena"
	 */
	public void setUsuNuevaContrasena(String usuNuevaContrasena);
	/**
	 * @return
	 * @uml.property  name="usuConfirmaContrasena"
	 */
	public String getUsuConfirmaContrasena();
    /**
	 * @param  usuConfirmaContrasena
	 * @uml.property  name="usuConfirmaContrasena"
	 */
    public void setUsuConfirmaContrasena(String usuConfirmaContrasena);
    public void updateContrasena();
    public void resetContrasena();
   
	public void destroy();
}