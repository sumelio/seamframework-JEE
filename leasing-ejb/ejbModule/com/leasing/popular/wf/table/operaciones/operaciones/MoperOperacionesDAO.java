package com.leasing.popular.wf.table.operaciones.operaciones;
// default package
// Generated 19/03/2010 10:09:21 AM by Hibernate Tools 3.2.4.GA

 
import javax.ejb.Local;

/**
 * Home object for domain model class MoperOperaciones.
 * @see .MoperOperaciones
 * @author Hibernate Tools
 */


@Local
public interface MoperOperacionesDAO {
 

	public void persist(MoperOperaciones transientInstance);

	public void remove(MoperOperaciones persistentInstance);

	public MoperOperaciones merge(MoperOperaciones detachedInstance);

	public MoperOperaciones findById(Long id);
	public void destroy();
}
