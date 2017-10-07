package com.leasing.popular.wf.table.negocio.estudio;

// Generated 1/02/2010 10:59:06 PM by Hibernate Tools 3.2.4.GA

 
import java.math.BigDecimal;

import com.leasing.popular.wf.table.seguridad.roles.MsegRoles;

/**
 * Home object for domain model class McomEstudio.
 * @see tables.McomEstudio
 * @author Hibernate Tools
 */
 
public interface McomEstudioDAO {
	public void persist(McomEstudio transientInstance);
	public void remove(McomEstudio persistentInstance);
	public McomEstudio merge(McomEstudio detachedInstance);
	public McomEstudio findById(BigDecimal id);
	public Long genereSecuencia();
	public boolean isConsulta( MsegRoles msegRoles );
	public boolean isActualizar( MsegRoles msegRoles );
	public boolean isElimnar( MsegRoles msegRoles );
    public boolean isInsertar( MsegRoles msegRoles );

}
