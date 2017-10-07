package com.leasing.popular.wf.table.operaciones.observaciones;// default package
// Generated 19/03/2010 10:09:21 AM by Hibernate Tools 3.2.4.GA

import java.util.List;

import javax.ejb.Remove;
import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Transactional;

/**
 * Home object for domain model class MoperObservaciones.
 * @see .MoperObservaciones
 * @author Hibernate Tools
 */

@Name("moperObservacionesActionDAO")
@AutoCreate
@Transactional
public class MoperObservacionesActionDAO implements MoperObservacionesDAO {

	private static final Log log = LogFactory
			.getLog(MoperObservacionesActionDAO.class);

	@In
	private EntityManager entityManager;

	public void persist(MoperObservaciones transientInstance) {
		log.debug("persisting MoperObservaciones instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(MoperObservaciones persistentInstance) {
		log.debug("removing MoperObservaciones instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public MoperObservaciones merge(MoperObservaciones detachedInstance) {
		log.debug("merging MoperObservaciones instance");
		try {
			MoperObservaciones result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public MoperObservaciones findById(MoperObservacionesId id) {
		log.debug("getting MoperObservaciones instance with id: " + id);
		try {
			MoperObservaciones instance = entityManager.find(
					MoperObservaciones.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<MoperObservaciones> findAll(MoperObservacionesId id) {
		log.debug("getting MoperObservaciones lista instance with id: " + id.getOperId());
		//System.out.println("--< " + id.getOperId() );
		try {
			List<MoperObservaciones> instance = entityManager.createQuery("select u from MoperObservaciones u where u.id.operId = :idOper order by u.operFecha")
			                              .setParameter("idOper", id.getOperId() )
			                              .getResultList();
			
			for (MoperObservaciones moperObservaciones : instance) {
				moperObservaciones.setObsObservaciones(moperObservaciones.getObsObservaciones().replace("\n", "_ENTER_"));
			}
			
//			for (MoperObservaciones moperObservaciones : instance) {
//				System.out.println("moperObservaciones.getObsObservaciones() " + moperObservaciones.getObsObservaciones());;
//			}
//			
			log.debug("get successful");
			return instance; 
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	@Remove
	public void destroy() {}
}
