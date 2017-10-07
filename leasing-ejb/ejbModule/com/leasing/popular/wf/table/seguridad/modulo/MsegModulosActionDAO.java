package com.leasing.popular.wf.table.seguridad.modulo;
// Generated 24/10/2009 12:22:18 PM by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Transactional;

/**
 * Home object for domain model class MsegModulos.
 * @see tables.MsegModulos
 * @author Hibernate Tools
 */
 
@Name("msegModulosActionDAO")
@AutoCreate
@Transactional
public class MsegModulosActionDAO implements MsegModulosDAO{

	private static final Log log = LogFactory.getLog(MsegModulosActionDAO.class);
	@In
	private EntityManager entityManager;
	 
	public void persist( MsegModulos transientInstance) {
		log.debug("persisting MsegModulos instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(MsegModulos persistentInstance) {
		log.debug("removing MsegModulos instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public MsegModulos merge(  MsegModulos detachedInstance) {
		log.debug("merging MsegModulos instance");
		try {
			MsegModulos result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public MsegModulos findById( Long id) {
		log.debug("getting MsegModulos instance with id: " + id);
		try {
			MsegModulos instance = entityManager.find(MsegModulos.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<MsegModulos> findAll( ) {
		log.debug("getting MsegModulos all" );
		try {
			List<MsegModulos> instance = entityManager.createQuery("select u from MsegModulos u order by msegMenu, modCodigo").getResultList();;
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
