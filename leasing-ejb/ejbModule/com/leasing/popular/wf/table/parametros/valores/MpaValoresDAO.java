package com.leasing.popular.wf.table.parametros.valores;

// Generated 12/12/2009 10:26:43 AM by Hibernate Tools 3.2.4.GA

import java.util.List;

import javax.ejb.Local;

import com.leasing.popular.wf.table.parametros.parametro.MpaParametro;
import com.leasing.popular.wf.table.seguridad.roles.MsegRoles;

/**
 * Home object for domain model class MpaValores.
 * @see tables.MpaValores
 * @author Hibernate Tools
 */
@Local
public interface MpaValoresDAO {
	public void persist(  MpaValores transientInstance);
	public void remove( MpaValores persistentInstance)  throws Exception;
	public MpaValores merge( MpaValores detachedInstance);
	public MpaValores findById( MpaValoresId id);
	public boolean isEliminar( MpaValoresId id);
	public Long genereSecuencia( MpaParametro mpa);
	public boolean isExisteValor( MpaValores mpaValores );
	public List<MpaValores>  findParam( Long codParametro);
	public boolean isConsulta(  MsegRoles msegRoles );
	public boolean isActualizar(  MsegRoles msegRoles );
	public boolean isElimnar(  MsegRoles msegRoles );
	public boolean isInsertar(  MsegRoles msegRoles );
}
