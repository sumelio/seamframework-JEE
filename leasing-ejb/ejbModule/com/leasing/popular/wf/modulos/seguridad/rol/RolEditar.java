package com.leasing.popular.wf.modulos.seguridad.rol;

import java.util.List;

import javax.ejb.Local;
import javax.faces.convert.Converter;

import com.leasing.popular.wf.modulos.seguridad.rol.bean.BooleanCheckboxRolMod;
import com.leasing.popular.wf.table.seguridad.estado.MsegEstados;
import com.leasing.popular.wf.table.seguridad.roles.MsegRoles;
import com.leasing.popular.wf.table.seguridad.roles.MsegRolmodulos;
import com.leasing.popular.wf.table.seguridad.usuarios.MsegUsuarios;
import com.leasing.popular.wf.table.seguridad.usuarios.MsegUsuariosDAO;




/**
 * @author          Usuario
 */
@Local
public interface RolEditar
{ 
	/**
	 * @return
	 * @uml.property  name="updateValid"
	 */
	public boolean isUpdateValid();
	/**
	 * @param  updateValid
	 * @uml.property  name="updateValid"
	 */
	public void setUpdateValid(boolean updateValid);
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
	public void update();
	public void seleccionRol(MsegRoles msegRoles);
	/**
	 * @uml.property  name="msegRolesEditar"
	 * @uml.associationEnd  
	 */
	public MsegRoles getMsegRolesEditar();
    /**
	 * @param  msegRolesEditar
	 * @uml.property  name="msegRolesEditar"
	 */
    public void setMsegRolesEditar(MsegRoles msegRolesEditar);
	public void destroy(); 
	 /**
	 * @return
	 * @uml.property  name="listaModuloRolM"
	 */
	public List<MsegRolmodulos> getListaModuloRolM();
	 /**
	 * @param  listaModuloRolM
	 * @uml.property  name="listaModuloRolM"
	 */
	public void setListaModuloRolM(List<MsegRolmodulos> listaModuloRolM);
	 /**
	 * @return
	 * @uml.property  name="listaChecked"
	 */
	public List<BooleanCheckboxRolMod> getListaChecked();
	 /**
	 * @param  listaChecked
	 * @uml.property  name="listaChecked"
	 */
	public void setListaChecked(List<BooleanCheckboxRolMod> listaChecked);
 
}