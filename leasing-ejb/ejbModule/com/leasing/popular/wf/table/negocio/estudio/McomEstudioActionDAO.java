package com.leasing.popular.wf.table.negocio.estudio;

// Generated 1/02/2010 10:59:06 PM by Hibernate Tools 3.2.4.GA

 
import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Remove;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Transactional;

import com.leasing.popular.wf.table.seguridad.roles.MsegRoles;

/**
 * Home object for domain model class McomEstudio.
 * @see tables.McomEstudio
 * @author Hibernate Tools
 */
 
@Name("mcomEstudioActionDAO")
@AutoCreate
@Transactional
public class McomEstudioActionDAO implements McomEstudioDAO{

	
	@In
	private EntityManager entityManager;
	
	private static final Log log = LogFactory.getLog(McomEstudioActionDAO.class);
	private static Long NUM_MODULO = new Long(6);
 
	 
	public void persist(  McomEstudio transientInstance) {
		log.debug("persisting McomEstudio instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove( McomEstudio persistentInstance) {
		log.debug("removing McomEstudio instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public McomEstudio merge( McomEstudio detachedInstance) {
		log.debug("merging McomEstudio instance");
		try {
			McomEstudio result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public McomEstudio findById(  BigDecimal id) {
		log.debug("getting McomEstudio instance with id: " + id);
		try {
			McomEstudio instance = entityManager.find(McomEstudio.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	

	public Long genereSecuencia() {
		log.debug("getting genereSecuencia");
		Long codigo = null; 
		try {
			Query query  = entityManager.createQuery("select  COALESCE(max(p.estId),0)  from McomEstudio p");

			if(query.getResultList() != null && query.getResultList().size() > 0){
				codigo = (Long)query.getResultList().get(0);
			}else{
				codigo = new Long(0);
			}

		
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
		return   codigo + 1;
	}
	

	//PERMISOS
		
		public boolean isConsulta( MsegRoles msegRoles ) {
			log.debug("getting MsegUsuarios instance with isConsulta");
			try {

				List<Long> instance = entityManager.createQuery("select u.rolConsulta from MsegRolmodulos u where u.msegRoles.rolCodigo = :arg1 and u.msegModulos.modCodigo = :arg2")
				.setParameter("arg1", msegRoles.getRolCodigo())
				.setParameter("arg2", NUM_MODULO)
				.getResultList();
				//List  instance = entityManager.find(MsegUsuarios.class, id);
				log.debug("get successful");
				if(instance != null && instance.size()>0 && instance.get(0)>0 )
				return true;
			} catch (RuntimeException re) {
				log.error("get failed", re);
				throw re;
			}
			return false;
		}
		
		
		public boolean isActualizar( MsegRoles msegRoles ) {
			log.debug("getting MsegUsuarios instance with isConsulta");
			try {

				List<Long> instance = entityManager.createQuery("select u.rolActualizar from MsegRolmodulos u where u.msegRoles.rolCodigo = :arg1 and u.msegModulos.modCodigo = :arg2")
				.setParameter("arg1", msegRoles.getRolCodigo())
				.setParameter("arg2", NUM_MODULO)
				.getResultList();
				//List  instance = entityManager.find(MsegUsuarios.class, id);
				log.debug("get successful");
				if(instance != null && instance.size()>0 && instance.get(0)>0 )
				return true;
			} catch (RuntimeException re) {
				log.error("get failed", re);
				throw re;
			}
			return false;
		}
		
		public boolean isElimnar( MsegRoles msegRoles ) {
			log.debug("getting MsegUsuarios instance with isConsulta");
			try {

				List<Long> instance = entityManager.createQuery("select u.rolEliminar from MsegRolmodulos u where u.msegRoles.rolCodigo = :arg1 and u.msegModulos.modCodigo = :arg2")
				.setParameter("arg1", msegRoles.getRolCodigo())
				.setParameter("arg2", NUM_MODULO)
				.getResultList();
				//List  instance = entityManager.find(MsegUsuarios.class, id);
				log.debug("get successful");
				if(instance != null && instance.size()>0 && instance.get(0)==1 )
				return true;
			} catch (RuntimeException re) {
				log.error("get failed", re);
				throw re;
			}
			return false;
		}
		
		
		public boolean isInsertar( MsegRoles msegRoles ) {
			log.debug("getting MsegUsuarios instance with isConsulta");
			try {

				List<Long> instance = entityManager.createQuery("select u.rolInsertar from MsegRolmodulos u where u.msegRoles.rolCodigo = :arg1 and u.msegModulos.modCodigo = :arg2")
				.setParameter("arg1", msegRoles.getRolCodigo())
				.setParameter("arg2", NUM_MODULO)
				.getResultList();
				//List  instance = entityManager.find(MsegUsuarios.class, id);
				log.debug("get successful");
				if(instance != null && instance.size()>0 && instance.get(0)>0)
				return  true;
			} catch (RuntimeException re) {
				log.error("get failed", re);
				throw re;
			}
			return false;
		}

		@Remove
		public void destroy() {}
}
