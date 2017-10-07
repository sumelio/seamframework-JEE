package com.leasing.popular.wf.modulos.seguridad.login;

import java.util.List;

import javax.ejb.Local;
import javax.faces.convert.Converter;

import com.leasing.popular.wf.table.seguridad.roles.MsegRoles;
import com.leasing.popular.wf.table.seguridad.usuarios.MsegRolusuarios;

/**
 * @author          Usuario
 */
@Local
public interface Accesos
{ 
    public String acceso();
	public void accesoRol();
	public Converter getConverter();
    /**
	 * @return
	 * @uml.property  name="listaRolUsuarioAcceso"
	 */
    public List<MsegRolusuarios> getListaRolUsuarioAcceso() ;
    /**
	 * @param  listaRolUsuario
	 * @uml.property  name="listaRolUsuarioAcceso"
	 */
    public void setListaRolUsuarioAcceso(List<MsegRolusuarios> listaRolUsuario);
	/**
	 * @param  msegRolusuariosAcceso
	 * @uml.property  name="msegRolusuariosAcceso"
	 */
	public void setMsegRolusuariosAcceso(MsegRolusuarios msegRolusuariosAcceso);
	/**
	 * @uml.property  name="msegRolusuariosAcceso"
	 * @uml.associationEnd  
	 */
	public MsegRolusuarios getMsegRolusuariosAcceso();
	public void destroy();
	
}
