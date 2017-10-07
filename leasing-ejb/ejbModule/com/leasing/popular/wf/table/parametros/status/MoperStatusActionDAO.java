package com.leasing.popular.wf.table.parametros.status;
// default package
// Generated 19/03/2010 10:09:21 AM by Hibernate Tools 3.2.4.GA

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
 * Home object for domain model class MoperStatus.
 * @see .MoperStatus
 * @author Hibernate Tools
 */
@Name("moperStatusActionDAO")
@AutoCreate
@Transactional
public class MoperStatusActionDAO implements MoperStatusDAO {

	private static final Log log = LogFactory.getLog(MoperStatusActionDAO.class);
	private static Long NUM_MODULO = new Long(3);
	@In
	private EntityManager entityManager;

	public void persist(MoperStatus transientInstance) {
		log.debug("persisting MoperStatus instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(MoperStatus persistentInstance) {
		log.debug("removing MoperStatus instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public MoperStatus merge(MoperStatus detachedInstance) {
		log.debug("merging MoperStatus instance");
		try {
			MoperStatus result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public MoperStatus findById(Long id) {
		log.debug("getting MoperStatus instance with id: " + id);
		try {
			MoperStatus instance = entityManager.find(MoperStatus.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	public Long genereSecuencia( ) {
		log.debug("getting genereSecuencia");
		Long codigo = null; 
		try {
			Query query  = entityManager.createQuery("select  COALESCE(max(p.staId),0)  from MoperStatus p");

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
	
	public boolean isExisteStatus(  MoperStatus moperStatus) {
		log.debug("getting moperStatus isExistePara ");
	///	SystentityManager.out.println("- 00 " + mpaParametro.getParCodigo());
		try { //SystentityManager.out.println("-  " + mpaParametro.getParNombre().trim());
			Query query  = entityManager.createQuery("select count(p) from MoperStatus p where lower(p.staNombre) LIKE lower( :nom ) and p.staId <> :cod")
			                .setParameter("nom", moperStatus.getStaNombre().trim())
			                .setParameter("cod", moperStatus.getStaId());

			if(query.getResultList() != null && query.getResultList().size() > 0 && (Long)query.getResultList().get(0) > 0){
				return true;
			} 

		
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
		return  false;
	}
	
	public List<MoperStatus> findAll() {
		log.debug("getting MoperStatus list all  instance " );
		try {
			List<MoperStatus> list = null;
			list = entityManager.createQuery("select u from MoperStatus u order by u.staId").getResultList();
			log.debug("get successful");
			 return list;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	//PERMISOS

	public boolean isConsulta(  MsegRoles msegRoles ) {
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


	public boolean isActualizar(  MsegRoles msegRoles ) {
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

	public boolean isElimnar(  MsegRoles msegRoles ) {
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


	public boolean isInsertar(  MsegRoles msegRoles ) {
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
