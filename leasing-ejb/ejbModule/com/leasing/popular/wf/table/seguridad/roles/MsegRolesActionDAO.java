package com.leasing.popular.wf.table.seguridad.roles;

// Generated 21/11/2009 03:33:18 PM by Hibernate Tools 3.2.5.Beta

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Transactional;

/**
 * Home object for domain model class MsegRoles.
 * @see tables.MsegRoles
 * @author Hibernate Tools
 */
@Name("msegRolesActionDAO")
@AutoCreate
@Transactional
public class MsegRolesActionDAO implements  MsegRolesDAO {

	@In
	private EntityManager entityManager;
	private static final Log log = LogFactory.getLog(MsegRolesActionDAO.class);
	private static Long NUM_MODULO = new Long(1);

	public void persist( MsegRoles transientInstance) {
		log.debug("persisting MsegRoles instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove( MsegRoles persistentInstance) {
		log.debug("removing MsegRoles instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public MsegRoles merge( MsegRoles detachedInstance) {
		log.debug("merging MsegRoles instance");
		try {
			MsegRoles result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public MsegRoles findById( Long id) {
		log.debug("getting MsegRoles instance with id: " + id);
		try {
			MsegRoles instance = entityManager.find(MsegRoles.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}


	public Long genereSecuencia(EntityManager em) {
		log.debug("getting genereSecuencia");
		Long codigo = null; 
		try {
			Query query  = entityManager.createQuery("select  COALESCE(max(p.rolCodigo),0)  from MsegRoles p");

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

	public boolean isExisteNombre( MsegRoles msegRoles) {
		log.debug("getting isExisteNombre"); 
		try { 
			Query query  = entityManager.createQuery("select count(p) from MsegRoles p where lower(p.rolNombreRol) = lower( :nom ) and p.rolCodigo <> :cod")
			.setParameter("nom", msegRoles.getRolNombreRol().trim())
			.setParameter("cod", msegRoles.getRolCodigo());

			if(query.getResultList() != null && query.getResultList().size() > 0 && (Long)query.getResultList().get(0) > 0){
				return true;
			} 


		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
		return  false;
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

}
