package com.leasing.popular.wf.table.seguridad.modulo;
// Generated 24/10/2009 12:22:18 PM by Hibernate Tools 3.2.4.GA

import java.util.List;

import javax.ejb.Local;

/**
 * Home object for domain model class MsegModulos.
 * @see tables.MsegModulos
 * @author Hibernate Tools
 */
@Local
public interface MsegModulosDAO {

	public void persist(MsegModulos transientInstance);
    public void remove(MsegModulos persistentInstance);
    public MsegModulos merge( MsegModulos detachedInstance);
	public MsegModulos findById(Long id);
	public List<MsegModulos> findAll();
}
