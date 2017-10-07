package com.leasing.popular.wf.modulos.seguridad.usuario;

import java.util.List;

import javax.ejb.Local;
import javax.faces.convert.Converter;

import com.leasing.popular.wf.table.seguridad.estado.MsegEstados;
import com.leasing.popular.wf.table.seguridad.usuarios.MsegUsuarios;




/**
 * @author          Usuario
 */
@Local
public interface UsuarioRegistro
{
	public void cancelar();
	public void register(); 
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
	public boolean isRegistered();
    public void destroy();
    public Converter getConverter();
    /**
	 * @param  msegUsuariosReg
	 * @uml.property  name="msegUsuariosReg"
	 */
    public void setMsegUsuariosReg(MsegUsuarios msegUsuariosReg);
    /**
	 * @uml.property  name="msegUsuariosReg"
	 * @uml.associationEnd  
	 */
    public MsegUsuarios getMsegUsuariosReg(); 
    /**
	 * @return
	 * @uml.property  name="listaEstadosReg"
	 */
    public List<MsegEstados>  getListaEstadosReg();
    public void nuevoUsuario();
    /**
	 * @param  listaEstadosReg
	 * @uml.property  name="listaEstadosReg"
	 */
    public void setListaEstadosReg(List<MsegEstados> listaEstadosReg);
 
}