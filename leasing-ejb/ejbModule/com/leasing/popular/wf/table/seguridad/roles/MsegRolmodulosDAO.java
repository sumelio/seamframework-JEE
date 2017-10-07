package com.leasing.popular.wf.table.seguridad.roles;

// Generated 22/11/2009 11:45:06 AM by Hibernate Tools 3.2.5.Beta

import java.util.List;

import javax.ejb.Local;

import com.leasing.popular.wf.table.seguridad.modulo.MsegRolmodulosId;


@Local
public interface  MsegRolmodulosDAO {

	public void persist( MsegRolmodulos transientInstance);
	public void remove( MsegRolmodulos persistentInstance);
	public void removeLista( List<MsegRolmodulos> listaModuloRol ) ;
	public MsegRolmodulos merge( MsegRolmodulos detachedInstance);
	public MsegRolmodulos findById( MsegRolmodulosId id);
	public List<MsegRolmodulos> findRol( MsegRoles id);
	public void persistList( List<MsegRolmodulos> listaModuloRolM);

}
