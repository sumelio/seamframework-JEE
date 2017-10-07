package com.leasing.popular.wf.table.parametros.valores;

// Generated 12/12/2009 10:26:43 AM by Hibernate Tools 3.2.4.GA

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Transactional;

import com.leasing.popular.wf.table.parametros.parametro.MpaParametro;
import com.leasing.popular.wf.table.seguridad.roles.MsegRoles;

/**
 * Home object for domain model class MpaValores.
 * @see tables.MpaValores
 * @author Hibernate Tools
 */
@Name("mpaValoresActionDAO")
@AutoCreate
@Transactional
public class MpaValoresActionDAO implements MpaValoresDAO {

	private static final Log log = LogFactory.getLog(MpaValoresActionDAO.class);
	@In
	private EntityManager entityManager;
	private static Long NUM_MODULO = new Long(4);

	public void persist(  MpaValores transientInstance) {
		log.debug("persisting MpaValores instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove( MpaValores persistentInstance) throws Exception{
		log.debug("removing MpaValores instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		}catch (RuntimeException re) {
			log.error("remove failed", re);
			throw   re;
		}
	}

	public MpaValores merge( MpaValores detachedInstance) {
		log.debug("merging MpaValores instance");
		try {
			MpaValores result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public MpaValores findById( MpaValoresId id) {
		log.debug("getting MpaValores instance with id: " + id);
		try {
			MpaValores instance = entityManager.find(MpaValores.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	public boolean isEliminar( MpaValoresId id) {
		log.debug("getting MpaValores instance with id: " + id);
		try {
			MpaValores instance = entityManager.find(MpaValores.class, id);
			log.debug("get successful");
			 
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
		return true;
	}
	


	public Long genereSecuencia( MpaParametro mpa) {
		log.debug("getting MpaValores secuencia ");
		//SystentityManager.out.println("getting MpaValores secuencia " +  mpa.getParCodigo());
		Long codigo; 
		try {
			Query query = entityManager.createQuery("select COALESCE(max(u.id.varCodigo),0) as max from MpaValores u where u.id.parCodigo  = :mp")
			.setParameter("mp", mpa.getParCodigo());

			if(query.getResultList() != null && query.getResultList().size() > 0){
				codigo = (Long)query.getResultList().get(0);
			}else{
				codigo = new Long(0);
			}


			log.debug("get successful");

			if(query != null && (Long)query.getResultList().get(0)> 0){
				return (Long)query.getResultList().get(0) + new Long(1);
			}
			else{
				return new Long(1);
			}


		} catch (RuntimeException re) {
			log.error("get failed genereSecuencia" , re);
			throw re;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//SystentityManager.out.println("E" + e.getMessage());
		}
		return null;
	}
	
	
	
	
	
	

	public boolean isExisteValor( MpaValores mpaValores ) {
		log.debug("getting MpaValores secuencia ");
		   
		try {
			Query query = entityManager.createQuery("  select  COALESCE(sum(1),0)                             " +
					"                                  from   MpaValores u   " +
					"                                  where  u.varNombre = :nom " +
					"                                  and    (u.id.varCodigo <> :ids1   and  u.id.parCodigo = :ids2  )")
			        .setParameter("nom", mpaValores.getVarNombre())               
					.setParameter("ids1", mpaValores.getId().getVarCodigo())
					.setParameter("ids2", mpaValores.getId().getParCodigo());
			                       
	 
			if(query != null && (Long)query.getResultList().get(0)> 0){
				return true;
			}
			


		} catch (RuntimeException re) {
			log.error("get failed genereSecuencia" , re);
			throw re;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//SystentityManager.out.println("E" + e.getMessage());
		}
		return false;
	}
	
	
	
	

	public List<MpaValores>  findParam( Long codParametro) {
		log.debug("getting findParam   codParametro " + codParametro);
		List<MpaValores> lista = new ArrayList<MpaValores>(); 
		try {
//			if (entityManager == null) {
//				SystentityManager.out.println("entityManager es null");
//				
//			}else{
//SystentityManager.out.println("no es entityManager null");
//			}
			//SystentityManager.out.println("codParametro " + codParametro);
			lista = entityManager.createQuery("select v from MpaValores v where v.mpaParametro.parCodigo = :codParametro order by v.varNombre")
			.setParameter("codParametro", codParametro)      
			.getResultList();
			 
 

		} catch (RuntimeException re) {
			log.error("get failed findParam +" + codParametro , re);
			throw re;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//SystentityManager.out.println("E" + e.getMessage());
		}
		return lista;
	}
	
	
	
	public List<MpaValores>  findParamPadre( Long codParametro, Long codPadre) {
		log.debug("getting findParam   findParamPadre " + codParametro);
		List<MpaValores> lista = new ArrayList<MpaValores>(); 
		try {
//			if (entityManager == null) {
//				SystentityManager.out.println("entityManager es null");
//				
//			}else{
//SystentityManager.out.println("no es entityManager null");
//			}
			//SystentityManager.out.println("codParametro " + codParametro);
			lista = entityManager.createQuery("select v from MpaValores v where v.mpaParametro.parCodigo = :codParametro" +
					"                            and  v.varPadre = :codPadre order by v.varNombre")
			.setParameter("codParametro", codParametro)
			.setParameter("codPadre", codPadre)
			.getResultList();
			 
 

		} catch (RuntimeException re) {
			log.error("get failed findParamPadre - " + codParametro+" - " + codPadre , re);
			throw re;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//SystentityManager.out.println("E" + e.getMessage());
		}
		return lista;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	// Permisos
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
	
	
	/**
	 * @param em
	 * @param msegRoles
	 * @return
	 */
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
