package com.leasing.popular.wf.table.parametros.parametro;

// Generated 12/12/2009 10:26:43 AM by Hibernate Tools 3.2.4.GA



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Transactional;

import com.leasing.popular.wf.table.seguridad.roles.MsegRoles;
import com.leasing.popular.wf.table.seguridad.usuarios.MsegUsuarios;

/**
 * Home object for domain model class MpaParametro.
 * @see tables.MpaParametro
 * @author Hibernate Tools

 */
@Name("mpaParametroActionDAO")
@AutoCreate
@Transactional
public class MpaParametroActionDAO  implements MpaParametroDAO {

	private static final Log log = LogFactory.getLog(MpaParametroActionDAO.class);
	private static Long NUM_MODULO = new Long(3);

	@In
	private EntityManager entityManager;
	
	public void persist(  MpaParametro transientInstance) {
		log.debug("persisting MpaParametro instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove( MpaParametro persistentInstance) {
		log.debug("removing MpaParametro instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public MpaParametro merge( MpaParametro detachedInstance) {
		log.debug("merging MpaParametro instance");
		try {
			MpaParametro result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public MpaParametro findById( Long id) {
		log.debug("getting MpaParametro instance with id: " + id);
		try {
			MpaParametro instance = entityManager.find(MpaParametro.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<MpaParametro> findAll( ) {
		log.debug("getting MpaParametro instance with id: ");
		try {
			List<MpaParametro> instance = entityManager.createQuery("select u from MpaParametro u order by u.parNombre").getResultList();
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
			Query query  = entityManager.createQuery("select  COALESCE(max(p.parCodigo),0)  from MpaParametro p");

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
	
	
	
	
	public boolean isExistePara(  MpaParametro mpaParametro) {
		log.debug("getting MsegUsuarios isExistePara ");
	///	SystentityManager.out.println("- 00 " + mpaParametro.getParCodigo());
		try { //SystentityManager.out.println("-  " + mpaParametro.getParNombre().trim());
			Query query  = entityManager.createQuery("select count(p) from MpaParametro p where lower(p.parNombre) LIKE lower( :nom ) and p.parCodigo <> :cod")
			                .setParameter("nom", mpaParametro.getParNombre().trim())
			                .setParameter("cod", mpaParametro.getParCodigo());

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
}
