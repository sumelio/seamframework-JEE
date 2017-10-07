package com.leasing.popular.wf.table.negocio.negocio;
// Generated 31/01/2010 09:41:03 AM by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;

import javax.ejb.Local;

import com.leasing.popular.wf.table.seguridad.roles.MsegRoles;

/**
 * Home object for domain model class McomNegocio.
 * @see tables.McomNegocio
 * @author Hibernate Tools
 */
@Local
public interface McomNegocioDAO {


	public void persist( McomNegocio transientInstance);
	public void remove( McomNegocio persistentInstance);
	public McomNegocio merge( McomNegocio detachedInstance);
	public McomNegocio findById( BigDecimal id);
	public BigDecimal genereSecuencia();
	public boolean isConsulta( MsegRoles msegRoles );
	public boolean isActualizar( MsegRoles msegRoles );
	public boolean isElimnar( MsegRoles msegRoles );
	public boolean isInsertar( MsegRoles msegRoles );
	public void destroy();

}
