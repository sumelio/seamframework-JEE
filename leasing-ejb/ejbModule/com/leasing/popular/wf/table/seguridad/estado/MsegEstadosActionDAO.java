package com.leasing.popular.wf.table.seguridad.estado;

// Generated 24/09/2009 08:39:53 PM by Hibernate Tools 3.2.4.GA

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Transactional;

/**
 * Home object for domain model class MsegEstados.
 * @see com.leasing.popular.wf.table.seguridad.estado.MsegEstados
 * @author Hibernate Tools
 */
@Name("msegEstadosActionDAO")
@AutoCreate
@Transactional
public class MsegEstadosActionDAO implements MsegEstadosDAO {

	private static final Log log = LogFactory.getLog(MsegEstadosActionDAO.class);

	@In
	private EntityManager entityManager;

	public void persist(MsegEstados transientInstance) {
		log.debug("persisting MsegEstados instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(MsegEstados persistentInstance) {
		log.debug("removing MsegEstados instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public MsegEstados merge(MsegEstados detachedInstance) {
		log.debug("merging MsegEstados instance");
		try {
			MsegEstados result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public MsegEstados findById(Long id) {
		log.debug("getting MsegEstados instance with id: " + id);
		try {
			MsegEstados instance = entityManager.find(MsegEstados.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<MsegEstados> findAll() {
		log.debug("getting MsegEstados instance with ALL: " );
		List lista= new ArrayList<MsegEstados>();
		try {
			 
			 
				  lista = entityManager.createQuery("select u from MsegEstados u").getResultList();
		 
			log.debug("get successful");
			return lista;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
