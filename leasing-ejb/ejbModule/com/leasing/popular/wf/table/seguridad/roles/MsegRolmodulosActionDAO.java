package com.leasing.popular.wf.table.seguridad.roles;

// Generated 22/11/2009 11:45:06 AM by Hibernate Tools 3.2.5.Beta

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Transactional;

import com.leasing.popular.wf.table.seguridad.modulo.MsegModulos;
import com.leasing.popular.wf.table.seguridad.modulo.MsegRolmodulosId;

@Name("msegRolmodulosActionDAO")
@AutoCreate
@Transactional
public class MsegRolmodulosActionDAO implements MsegRolmodulosDAO {

	private static final Log log = LogFactory.getLog(MsegRolmodulosActionDAO.class);
 

	@In
	private EntityManager entityManager;
	
	public void persist(  MsegRolmodulos transientInstance) {
		log.debug("persisting MsegRolmodulos instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(  MsegRolmodulos persistentInstance) {
		log.debug("removing MsegRolmodulos instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}
	
	public void removeLista(  List<MsegRolmodulos> listaModuloRol ) {
		log.debug("removing MsegRolmodulosl lista  instance");
		try {
			
			for (MsegRolmodulos  element : listaModuloRol) {
				 if(findById( element.getId()) != null){
					 entityManager.remove(element);
				 } 
			}
			 
			
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public MsegRolmodulos merge(  MsegRolmodulos detachedInstance) {
		log.debug("merging MsegRolmodulos instance");
		try {
			MsegRolmodulos result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public MsegRolmodulos findById(  MsegRolmodulosId id) {
		log.debug("getting MsegRolmodulos instance with id: " + id);
		try {
			MsegRolmodulos instance = entityManager.find(MsegRolmodulos.class,
					id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	
	
	
	public List<MsegRolmodulos> findRol(  MsegRoles id) {
		log.debug("getting MsegRolmodulos instance with id: " + id);
	//	SystentityManager.out.println("id "  + id);
		try {                                                             
			//MsegRolmodulos ms
			boolean flag = false;
			List<MsegRolmodulos> instance = entityManager.createQuery("select u from MsegRolmodulos  u where u.msegRoles.rolCodigo = :ids order by u.msegModulos.msegMenu.menCodigo, u.msegModulos.modNombre ")
			                            .setParameter("ids", id.getRolCodigo())
			                             .getResultList();
			
			List<MsegModulos> instance2 = entityManager.createQuery("select uu from MsegModulos uu  order by uu.msegMenu.menCodigo, uu.modNombre")
                            .getResultList();
			
			for (MsegModulos msegModulos : instance2) {
				 
				flag = false;
				
				for (MsegRolmodulos msegRolmodulos : instance) {
				 
					if ( msegModulos.getModCodigo() == msegRolmodulos.getMsegModulos().getModCodigo()) {
						flag = true;
					}
				}	
				if(flag == false){
					MsegRolmodulos msegRolmodulos = new MsegRolmodulos (new MsegRolmodulosId(msegModulos.getModCodigo(), id.getRolCodigo()),id,msegModulos); 
					msegRolmodulos.setRolActualizar(new Long(0));
					msegRolmodulos.setRolConsulta(new Long(0));
					msegRolmodulos.setRolEliminar(new Long(0));
					msegRolmodulos.setRolInsertar(new Long(0));
					instance.add(msegRolmodulos);
				}
			}
			
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	
	
	
	public void persistList(  List<MsegRolmodulos> listaModuloRolM) {
		log.debug("persisting MsegRolmodulos instance");
	//	SystentityManager.out.println("listaModuloRolM " + listaModuloRolM.size());
		try {
			
			for (MsegRolmodulos  element : listaModuloRolM) {
				//SystentityManager.out.println("element.getId()" + element.getId().getModCodigo());
				if(findById( element.getId()) != null){
					//SystentityManager.out.println("Existe");
					entityManager.merge(element);
				}else{
				    entityManager.persist(element);
					//SystentityManager.out.println("no Existe");
				}
			}
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}
 
}
