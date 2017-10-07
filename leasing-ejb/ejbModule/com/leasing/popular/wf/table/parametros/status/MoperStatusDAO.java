package com.leasing.popular.wf.table.parametros.status;
// default package
// Generated 19/03/2010 10:09:21 AM by Hibernate Tools 3.2.4.GA

import javax.ejb.Local;

/**
 * Home object for domain model class MoperStatus.
 * @see .MoperStatus
 * @author Hibernate Tools
 */
@Local
public interface MoperStatusDAO {
 
 

	public void persist(MoperStatus transientInstance);

	public void remove(MoperStatus persistentInstance);
	public MoperStatus merge(MoperStatus detachedInstance);
	public MoperStatus findById(Long id);
	public void destroy();
}
