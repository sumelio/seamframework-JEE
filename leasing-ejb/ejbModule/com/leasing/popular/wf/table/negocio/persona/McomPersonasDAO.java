package com.leasing.popular.wf.table.negocio.persona;

// Generated 14/01/2010 07:07:42 AM by Hibernate Tools 3.2.5.Beta

import java.util.List;

import javax.ejb.Local;

import com.leasing.popular.wf.table.seguridad.roles.MsegRoles;

/**
 * Home object for domain model class McomPersonas.
 * @see tables.McomPersonas
 * @author Hibernate Tools
 */
@Local
public interface McomPersonasDAO {

 
	public void persist( McomPersonas transientInstance);
    public void remove( McomPersonas persistentInstance);
    public McomPersonas merge(  McomPersonas detachedInstance);
    public McomPersonas findById( McomPersonasId id);
	public List<McomPersonas> findProveedor( Long id);
	public boolean isExisteIdentificacion(  McomPersonas mcomPersonas);
	public boolean isConsulta( MsegRoles msegRoles );
	public boolean isActualizar( MsegRoles msegRoles );
	public boolean isElimnar( MsegRoles msegRoles );
	public boolean isInsertar( MsegRoles msegRoles ) ;
}
