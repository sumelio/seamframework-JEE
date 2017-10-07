package com.leasing.popular.wf.table.negocio.persona;

// Generated 14/01/2010 07:07:42 AM by Hibernate Tools 3.2.5.Beta

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Transactional;

import com.leasing.popular.wf.table.seguridad.roles.MsegRoles;

/**
 * Home object for domain model class McomPersonas.
 * @see tables.McomPersonas
 * @author Hibernate Tools
 */
@Name("mcomPersonasActionDAO")
@AutoCreate
@Transactional
public class McomPersonasActionDAO implements McomPersonasDAO {

	private static final Log log = LogFactory.getLog(McomPersonasActionDAO.class);
	private static Long NUM_MODULO = new Long(4);
	@In
	EntityManager entityManager;
	public void persist(  McomPersonas transientInstance) {
		log.debug("persisting McomPersonas instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(  McomPersonas persistentInstance) {
		log.debug("removing McomPersonas instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public McomPersonas merge(   McomPersonas detachedInstance) {
		log.debug("merging McomPersonas instance");
		try {
			McomPersonas result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public McomPersonas findById(  McomPersonasId id) {
		log.debug("getting McomPersonas instance with id: " + id);
		try {
			
			/*SystentityManager.out.println("id " + id.getPerIdentificacion());
			SystentityManager.out.println("id " + id.getPerTipoIdentificacion());
			SystentityManager.out.println("id " + id.getPerParaTipoId());
			*/
			
			McomPersonas instance = entityManager.find(McomPersonas.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	
	
	
	public List<McomPersonas> findProveedor(  Long id) {
		log.debug("getting McomPersonas findProveedor");
		try {
			List<McomPersonas> lista = new ArrayList<McomPersonas>();
			lista = entityManager.createQuery("select per from McomPersonas per " +
					"                          where ('%'||per.mpaValoresByFkPersonasValoresPersonas1.varNombre||'%') LIKE '%'||:per1||'%'"  +
					"                          order by per.perNombres, per.perApellidos")
			     .setParameter("per1", "PROVEEDOR") 
			    .getResultList();
			
			log.debug("get successful");
			return lista;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public boolean isExisteIdentificacion(  McomPersonas mcomPersonas) {
		/*log.debug("getting isExisteIdentificacion");
		try { 
			Query query  = entityManager.createQuery("select count(p) from McomPersonas p where lower(p.usuUsuario) = lower( :nom ) and p.usuCedula <> :cod")
			                .setParameter("nom", mcomPersonas.getUsuUsuario().trim())
			                .setParameter("cod", mcomPersonas.getUsuCedula());

			if(query.getResultList() != null && query.getResultList().size() > 0 && (Long)query.getResultList().get(0) > 0){
				return true;
			} 

		
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}*/
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
