package com.leasing.popular.wf.table.negocio.negocio;
// Generated 31/01/2010 09:41:03 AM by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Transactional;

import com.leasing.popular.wf.table.negocio.persona.McomPersonasId;
import com.leasing.popular.wf.table.parametros.valores.MpaValores;
import com.leasing.popular.wf.table.seguridad.roles.MsegRoles;

import org.apache.commons.logging.LogFactory;
/**
 * Home object for domain model class McomNegocio.
 * @see tables.McomNegocio
 * @author Hibernate Tools
 */
@Name("mcomNegocioActionDAO")
@AutoCreate
@Transactional
public class McomNegocioActionDAO implements  McomNegocioDAO {

	


	@In
	private EntityManager entityManager;
	private static final Log log = LogFactory.getLog(McomNegocioActionDAO.class);
	 
	private static Long NUM_MODULO = new Long(5);

	public void persist( McomNegocio transientInstance) {
		log.debug("persisting McomNegocio instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove( McomNegocio persistentInstance) {
		log.debug("removing McomNegocio instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public McomNegocio merge( McomNegocio detachedInstance) {
		log.debug("merging McomNegocio instance");
		try {
			McomNegocio result =entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public McomNegocio findById( BigDecimal id) {
		log.debug("getting McomNegocio instance with id: " + id);
		try {

			McomNegocio instance =entityManager.find(McomNegocio.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/**
	 * @param mcomPersonasId
	 * @return
	 */
	public boolean findByPersona(McomPersonasId mcomPersonasId ) {
		log.debug("getting findByPersona");
		Long codigo = null; 
		try {
			Query query  = entityManager.createQuery("select COALESCE(sum(1),0) from McomNegocio neg where neg.mcomPersonasByFkClientePersonas.id :id")
			                .setParameter("id", mcomPersonasId);

			if(query.getResultList() != null && query.getResultList().size() > 0){
				return true;
			}


		} catch (RuntimeException re) {
			log.error("get findByPersona  failed", re);
			throw re;
		}
		return   false;
	}
	
	public BigDecimal genereSecuencia( ) {
		log.debug("getting genereSecuencia");
		BigDecimal codigo = null; 
		try {
			Query query  = entityManager.createQuery("select  COALESCE(max(p.negId),0)  from McomNegocio p");

			if(query.getResultList() != null && query.getResultList().size() > 0){
				codigo = (BigDecimal)query.getResultList().get(0);
			}else{
				codigo = new BigDecimal(0);
			}


		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
		return  new BigDecimal(Long.valueOf(codigo.longValue() + 1));
	}

	//PERMISOS

	public boolean isConsulta(  MsegRoles msegRoles ) {
		log.debug("getting MsegUsuarios instance with isConsulta");
		try {

			List<Long> instance =entityManager.createQuery("select u.rolConsulta from MsegRolmodulos u where u.msegRoles.rolCodigo = :arg1 and u.msegModulos.modCodigo = :arg2")
			.setParameter("arg1", msegRoles.getRolCodigo())
			.setParameter("arg2", NUM_MODULO)
			.getResultList();
			//List  instance =entityManager.find(MsegUsuarios.class, id);
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

			List<Long> instance =entityManager.createQuery("select u.rolActualizar from MsegRolmodulos u where u.msegRoles.rolCodigo = :arg1 and u.msegModulos.modCodigo = :arg2")
			.setParameter("arg1", msegRoles.getRolCodigo())
			.setParameter("arg2", NUM_MODULO)
			.getResultList();
			//List  instance =entityManager.find(MsegUsuarios.class, id);
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

			List<Long> instance =entityManager.createQuery("select u.rolEliminar from MsegRolmodulos u where u.msegRoles.rolCodigo = :arg1 and u.msegModulos.modCodigo = :arg2")
			.setParameter("arg1", msegRoles.getRolCodigo())
			.setParameter("arg2", NUM_MODULO)
			.getResultList();
			//List  instance =entityManager.find(MsegUsuarios.class, id);
			log.debug("get successful");
			if(instance != null && instance.size()>0 && instance.get(0)==1 )
				return true;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
		return false;
	}


	/* (non-Javadoc)
	 * @see com.leasing.popular.wf.table.negocio.McomNegocioDAO#isInsertar(com.leasing.popular.wf.table.seguridad.MsegRoles)
	 */
	public boolean isInsertar(  MsegRoles msegRoles ) {
		log.debug("getting MsegUsuarios instance with isConsulta");
		try {
//if(entityManager == null){
//	System.out.println("entityManager es null");
//}else{
//	System.out.println("entityManager no es null");
//}
			List<Long> instance =entityManager.createQuery("select u.rolInsertar from MsegRolmodulos u where u.msegRoles.rolCodigo = :arg1 and u.msegModulos.modCodigo = :arg2")
			.setParameter("arg1", msegRoles.getRolCodigo())
			.setParameter("arg2", NUM_MODULO)
			.getResultList();
			//List  instance =entityManager.find(MsegUsuarios.class, id);
			log.debug("get successful");
			if(instance != null && instance.size()>0 && instance.get(0)>0)
				return  true;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
		return false;

	}
	
	

	public List<MpaValores>  findParam(Long codParametro) {
		log.debug("getting findParam   codParametro " + codParametro);
		List<MpaValores> lista = new ArrayList<MpaValores>(); 
		try {
//			if (entityManager == null) {
//				//System.out.println("entityManager es null");
//
//			}else{
//				//System.out.println("no es entityManager null");
//			}
		//	System.out.println("codParametro " + codParametro);
			lista = entityManager.createQuery("select v from MpaValores v where v.mpaParametro.parCodigo = :codParametro order by v.varNombre")
			.setParameter("codParametro", codParametro)      
			.getResultList();



		} catch (RuntimeException re) {
			log.error("get failed findParam" , re);
			throw re;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("E" + e.getMessage());
		}
		return lista;
	}

	@Remove
	public void destroy() {}

}
