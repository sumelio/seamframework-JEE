package com.leasing.popular.wf.table.seguridad.usuarios;
// Generated 25/11/2009 08:30:37 AM by Hibernate Tools 3.2.5.Beta

import java.util.List;

import javax.ejb.Local;

import com.leasing.popular.wf.modulos.seguridad.usuario.bean.BooleanCheckboxUsuRol;
import com.leasing.popular.wf.table.seguridad.roles.MsegRoles;

/**
 * Home object for domain model class MsegRolusuarios.
 * @see tables.MsegRolusuarios
 * @author Hibernate Tools
 */
@Local
public interface  MsegRolusuariosDAO {


	public void persist(  MsegRolusuarios transientInstance);
	public void remove(  MsegRolusuarios persistentInstance);
	public void removeListRol(  MsegUsuarios msegUsuarios);
	public MsegRolusuarios merge(  MsegRolusuarios detachedInstance);
	public MsegRolusuarios findById( MsegRolusuariosId id);
	public List<BooleanCheckboxUsuRol> findByUsu( MsegUsuarios msegUsuarios);
	public List<MsegRolusuarios> findByUsuRol( MsegUsuarios msegUsuarios);
	public List<MsegRolusuarios> findByRolUsu( MsegRoles msegRoles);
}
