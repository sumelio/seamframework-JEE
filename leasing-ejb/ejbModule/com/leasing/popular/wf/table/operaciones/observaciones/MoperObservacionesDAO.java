package com.leasing.popular.wf.table.operaciones.observaciones;// default package
// Generated 19/03/2010 10:09:21 AM by Hibernate Tools 3.2.4.GA

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Transactional;

/**
 * Home object for domain model class MoperObservaciones.
 * @see .MoperObservaciones
 * @author Hibernate Tools
 */

@Local
public interface MoperObservacionesDAO {

	public void persist(MoperObservaciones transientInstance);

	public void remove(MoperObservaciones persistentInstance);

	public MoperObservaciones merge(MoperObservaciones detachedInstance);

	public MoperObservaciones findById(MoperObservacionesId id);
	public void destroy();
}
