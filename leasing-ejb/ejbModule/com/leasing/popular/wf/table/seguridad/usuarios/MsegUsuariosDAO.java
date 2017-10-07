package com.leasing.popular.wf.table.seguridad.usuarios;

// Generated 24/09/2009 08:39:53 PM by Hibernate Tools 3.2.4.GA

import java.util.List;

import javax.ejb.Local;

import com.leasing.popular.wf.table.seguridad.roles.MsegRoles;

/**
 * Home object for domain model class MsegUsuarios.
 * @see com.leasing.popular.wf.table.seguridad.usuarios.MsegUsuarios
 * @author Hibernate Tools
 */

@Local
public interface    MsegUsuariosDAO {
	public void persist(  MsegUsuarios transientInstance);
	public void remove(  MsegUsuarios persistentInstance);
	public MsegUsuarios merge(  MsegUsuarios detachedInstance);
	public MsegUsuarios findById(  long id);
	public MsegUsuarios findByCedula( long cedula);
	public List<MsegUsuarios> findByLogin(  String username, String password );
	public boolean isExisteUsuarioNombre(  MsegUsuarios msegUsuarios );
	public boolean isConsulta(  MsegRoles msegRoles ) ;
	public boolean isActualizar(  MsegRoles msegRoles );
	public boolean isElimnar(  MsegRoles msegRoles );
	public boolean isInsertar(  MsegRoles msegRoles );
}
