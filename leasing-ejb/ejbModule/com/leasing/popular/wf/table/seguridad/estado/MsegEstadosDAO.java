package com.leasing.popular.wf.table.seguridad.estado;

// Generated 24/09/2009 08:39:53 PM by Hibernate Tools 3.2.4.GA

import java.util.List;

import javax.ejb.Local;

/**
 * Home object for domain model class MsegEstados.
 * @see com.leasing.popular.wf.table.seguridad.estado.MsegEstados
 * @author Hibernate Tools
 */
@Local
public interface  MsegEstadosDAO {

	public void persist(MsegEstados transientInstance) ;
	public void remove(MsegEstados persistentInstance);
	public MsegEstados merge(MsegEstados detachedInstance);
	public MsegEstados findById(Long id);
	public List<MsegEstados> findAll();
}
