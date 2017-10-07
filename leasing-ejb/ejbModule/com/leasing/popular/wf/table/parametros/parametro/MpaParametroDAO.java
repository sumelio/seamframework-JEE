package com.leasing.popular.wf.table.parametros.parametro;

// Generated 12/12/2009 10:26:43 AM by Hibernate Tools 3.2.4.GA



import java.util.List;

import javax.ejb.Local; 
import com.leasing.popular.wf.table.seguridad.roles.MsegRoles;

/**
 * Home object for domain model class MpaParametro.
 * @see tables.MpaParametro
 * @author Hibernate Tools

 */

@Local
public interface MpaParametroDAO {
	public void persist(  MpaParametro transientInstance);
	public void remove( MpaParametro persistentInstance);
	public MpaParametro merge( MpaParametro detachedInstance);
	public MpaParametro findById( Long id);
	public List<MpaParametro> findAll( );
	public Long genereSecuencia( );
	public boolean isExistePara(  MpaParametro mpaParametro);
	public boolean isConsulta(  MsegRoles msegRoles );
	public boolean isActualizar(  MsegRoles msegRoles );
	public boolean isElimnar(  MsegRoles msegRoles );
	public boolean isInsertar(  MsegRoles msegRoles );
}
