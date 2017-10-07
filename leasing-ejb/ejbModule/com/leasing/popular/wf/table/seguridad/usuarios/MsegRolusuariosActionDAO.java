package com.leasing.popular.wf.table.seguridad.usuarios;
// Generated 25/11/2009 08:30:37 AM by Hibernate Tools 3.2.5.Beta

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Transactional;

import com.leasing.popular.wf.modulos.seguridad.usuario.bean.BooleanCheckboxUsuRol;
import com.leasing.popular.wf.table.seguridad.roles.MsegRoles;

/**
 * Home object for domain model class MsegRolusuarios.
 * @see tables.MsegRolusuarios
 * @author Hibernate Tools
 */
@Name("msegRolusuariosActionDAO")
@AutoCreate
@Transactional
public class MsegRolusuariosActionDAO implements MsegRolusuariosDAO  {

	private static final Log log = LogFactory.getLog(MsegRolusuariosActionDAO.class);
	@In
	private EntityManager entityManager;
 

	public void persist( MsegRolusuarios transientInstance) {
		log.debug("persisting MsegRolusuarios instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove( MsegRolusuarios persistentInstance) {
		log.debug("removing MsegRolusuarios instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}
	
	public void removeListRol( MsegUsuarios msegUsuarios) {
		log.debug("removing MsegRolusuarios instance");
		try {
			List<MsegRoles> listaMsegRoles = entityManager.createQuery("select u from MsegRoles u order by u.rolCodigo").getResultList();
			 for (MsegRoles msegRoles : listaMsegRoles) {
				 MsegRolusuariosId msegRolusuariosId = new MsegRolusuariosId(msegRoles.getRolCodigo(), msegUsuarios.getUsuCedula()); 
				 MsegRolusuarios instance = entityManager.find(MsegRolusuarios.class, msegRolusuariosId);
				 if(instance != null){
					 entityManager.remove(instance);
				}
			}
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}
	public MsegRolusuarios merge( MsegRolusuarios detachedInstance) {
		log.debug("merging MsegRolusuarios instance");
		try {
			MsegRolusuarios result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public MsegRolusuarios findById(MsegRolusuariosId id) {
		log.debug("getting MsegRolusuarios instance with id: " + id);
		try {
			MsegRolusuarios instance = entityManager.find(
					MsegRolusuarios.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<BooleanCheckboxUsuRol> findByUsu(MsegUsuarios msegUsuarios) {
		log.debug("getting MsegRolusuarios instance findByUsu ");
		try {
			List<BooleanCheckboxUsuRol> listaMsegRolusuarios = new ArrayList<BooleanCheckboxUsuRol>();
			
			List<MsegRoles> listaMsegRoles = entityManager.createQuery("select u from MsegRoles u order by u.rolCodigo").getResultList();
			 for (MsegRoles msegRoles : listaMsegRoles) {
				 MsegRolusuariosId msegRolusuariosId = new MsegRolusuariosId(msegRoles.getRolCodigo(), msegUsuarios.getUsuCedula()); 
				 MsegRolusuarios instance = entityManager.find(MsegRolusuarios.class, msegRolusuariosId);
				 if(instance != null){
					 listaMsegRolusuarios.add(new BooleanCheckboxUsuRol(instance,true));
					
				}else{
					 listaMsegRolusuarios.add(new BooleanCheckboxUsuRol(new MsegRolusuarios(msegRolusuariosId,msegRoles,msegUsuarios),false ));
				}
			}
			
			
			log.debug("get lista Rol Usuario successful");
			return listaMsegRolusuarios ;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<MsegRolusuarios> findByUsuRol(MsegUsuarios msegUsuarios) {
		log.debug("getting MsegRolusuarios instance findByUsu ");
		try {
			List<MsegRolusuarios> listaMsegRolusuarios = new ArrayList<MsegRolusuarios>();
			
			List<MsegRoles> listaMsegRoles = entityManager.createQuery("select u from MsegRoles u order by u.rolCodigo").getResultList();
			 for (MsegRoles msegRoles : listaMsegRoles) {
				 MsegRolusuariosId msegRolusuariosId = new MsegRolusuariosId(msegRoles.getRolCodigo(), msegUsuarios.getUsuCedula()); 
				 MsegRolusuarios instance = entityManager.find(MsegRolusuarios.class, msegRolusuariosId);
				 if(instance != null){
					 listaMsegRolusuarios.add(instance);
					
				}
			}
			
			
			log.debug("get lista Rol Usuario successful");
			return listaMsegRolusuarios ;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<MsegRolusuarios> findByRolUsu(MsegRoles msegRoles) {
		log.debug("getting MsegRolusuarios instance findByRolUsu ");
		try {
			List<MsegRolusuarios> listaMsegRolusuarios = new ArrayList<MsegRolusuarios>();
			
			listaMsegRolusuarios = entityManager.createQuery("select u from MsegRolusuarios u where u.msegRoles = :m ")
			.setParameter("m", msegRoles)
			.getResultList();
			 
			
			
			log.debug("get lista Rol Usuario findByRolUsu");
			return listaMsegRolusuarios ;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
