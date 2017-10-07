package com.leasing.popular.wf.table.seguridad.roles;

// Generated 21/11/2009 03:33:18 PM by Hibernate Tools 3.2.5.Beta

import javax.ejb.Local;
import javax.persistence.EntityManager;

/**
 * Home object for domain model class MsegRoles.
 * @see tables.MsegRoles
 * @author Hibernate Tools
 */

@Local
public interface MsegRolesDAO {
	public void persist(  MsegRoles transientInstance);
	public void remove(  MsegRoles persistentInstance);
	public MsegRoles merge(  MsegRoles detachedInstance);
	public MsegRoles findById(  Long id);
	public Long genereSecuencia(EntityManager em);
	public boolean isExisteNombre(  MsegRoles msegRoles);
	public boolean isConsulta(  MsegRoles msegRoles );
	public boolean isActualizar(  MsegRoles msegRoles );
	public boolean isElimnar(  MsegRoles msegRoles );
	public boolean isInsertar(  MsegRoles msegRoles );

}
