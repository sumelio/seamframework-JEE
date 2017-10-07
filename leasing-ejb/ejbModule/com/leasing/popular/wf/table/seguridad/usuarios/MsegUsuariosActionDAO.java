package com.leasing.popular.wf.table.seguridad.usuarios;

// Generated 24/09/2009 08:39:53 PM by Hibernate Tools 3.2.4.GA

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
/**
 * Home object for domain model class MsegUsuarios.
 * @see com.leasing.popular.wf.table.seguridad.usuarios.MsegUsuarios
 * @author Hibernate Tools
 */
@Name("msegUsuariosActionDAO")
@AutoCreate
@Transactional
public class MsegUsuariosActionDAO implements MsegUsuariosDAO{

	private static final Log log = LogFactory.getLog(MsegUsuariosActionDAO.class);

	private static Long NUM_MODULO = new Long(2);
	
	@In
	private EntityManager entityManager;

	public void persist(  MsegUsuarios transientInstance) {
		log.debug("persisting MsegUsuarios instance");
		 
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(  MsegUsuarios persistentInstance) {
		log.debug("removing MsegUsuarios instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public MsegUsuarios merge(  MsegUsuarios detachedInstance) {
		log.debug("merging MsegUsuarios instance");
		try {
			MsegUsuarios result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public MsegUsuarios findById( long id) {
		log.debug("getting MsegUsuarios instance with id: " + id);
		try {
			MsegUsuarios instance = entityManager.find(MsegUsuarios.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	public MsegUsuarios findByCedula( long cedula) {
		log.debug("getting MsegUsuarios instance with findByCedula");
		try {
			MsegUsuarios  msegUsuarios = null;
			List instance =  entityManager.createQuery("select u from MsegUsuarios u where u.usuCedula = :cedula").setParameter("cedula", cedula).getResultList();
			if (instance != null && instance.size() > 0) {
				try{
					msegUsuarios = (MsegUsuarios)instance.get(0);
				}catch (ClassCastException e) {
					
					log.error("get failed", e);
					throw e;
					// TODO: handle exception
				}
			}
			log.debug("get successful findByCedula");
			return msegUsuarios;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<MsegUsuarios> findByLogin(  String username, String password ) {
		log.debug("getting MsegUsuarios instance with id: " + username + " password " + password);
		try {

			List instance = entityManager.createQuery("select u from MsegUsuarios u where u.usuUsuario= :username and u.usuContrasena= :password")
			.setParameter("username", username)
			.setParameter("password", password)
			.getResultList();
			//List  instance = entityManager.find(MsegUsuarios.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	
	public boolean isExisteUsuarioNombre(  MsegUsuarios msegUsuarios ) {
		log.debug("getting MsegUsuarios isExisteUsuarioNombre ");
		try { 
			Query query  = entityManager.createQuery("select count(p) from MsegUsuarios p where lower(p.usuUsuario) = lower( :nom ) and p.usuCedula <> :cod")
			                .setParameter("nom", msegUsuarios.getUsuUsuario().trim())
			                .setParameter("cod", msegUsuarios.getUsuCedula());

			if(query.getResultList() != null && query.getResultList().size() > 0 && (Long)query.getResultList().get(0) > 0){
				return true;
			} 

		
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
		return  false;
	}
	
	
	public boolean isConsulta(  MsegRoles msegRoles ) {
		log.debug("getting MsegUsuarios instance with isConsulta");
		try {

			List<Long> instance = entityManager.createQuery("select u.rolConsulta from MsegRolmodulos u where u.msegRoles.rolCodigo = :arg1 and u.msegModulos.modCodigo = :arg2")
			.setParameter("arg1", msegRoles.getRolCodigo())
			.setParameter("arg2", NUM_MODULO )
			.getResultList();
			//List  instance = entityManager.find(MsegUsuarios.class, id);
			log.debug("get successful");
			if(instance != null && instance.size()>0 && instance.get(0) == 1 )
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
			.setParameter("arg2", NUM_MODULO )
			.getResultList();
			//List  instance = entityManager.find(MsegUsuarios.class, id);
			log.debug("get successful");
			if(instance != null && instance.size()>0 )
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
			.setParameter("arg2", NUM_MODULO )
			.getResultList();
			//List  instance = entityManager.find(MsegUsuarios.class, id);
			log.debug("get successful");
			if(instance != null && instance.size()>0 && instance.get(0) >0 )
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
			.setParameter("arg2", NUM_MODULO )
			.getResultList();
			//List  instance = entityManager.find(MsegUsuarios.class, id);
			log.debug("get successful");
			if(instance != null && instance.size()>0 && instance.get(0) > 0 )
			return true;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
		return false;
	}
}
