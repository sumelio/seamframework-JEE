//$Id: UsuarioBuscarAction.java 5509 2007-06-25 16:19:40Z gavin $
package com.leasing.popular.wf.modulos.seguridad.rol;

import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.faces.FacesMessages;

import com.leasing.popular.wf.table.seguridad.roles.MsegRoles;
import com.leasing.popular.wf.table.seguridad.roles.MsegRolesActionDAO;
import com.leasing.popular.wf.table.seguridad.roles.MsegRolmodulosActionDAO;
import com.leasing.popular.wf.table.seguridad.usuarios.MsegRolusuariosActionDAO;
import com.leasing.popular.wf.table.seguridad.usuarios.MsegUsuarios;



/**
 * @author       Usuario
 */
@Stateful
@Name("rolBuscarAction")
@Scope(ScopeType.SESSION)
@Restrict("#{identity.loggedIn}")
public class RolBuscarAction implements  RolBuscar
{

	@PersistenceContext
	private EntityManager em;

	@In
	private   FacesMessages facesMessages;

	@DataModel
	private List<MsegUsuarios> listaRol;
    /**
	 * @uml.property  name="msegUsuariosEditar"
	 * @uml.associationEnd  
	 */
    MsegUsuarios msegUsuariosEditar = new MsegUsuarios();
    
    /**
	 * @uml.property  name="msegRoles"
	 * @uml.associationEnd  
	 */
    @In(required=true, scope=ScopeType.SESSION)
	public MsegRoles msegRoles; 
	
    
    
	 private String searchString;
	 private int pageSize = 10;
	/**
	 * @uml.property  name="page"
	 */
	private int page;
 @In
	private MsegRolesActionDAO msegRolesActionDAO;
	 @In
	private MsegRolmodulosActionDAO msegRolmodulosActionDAO;
 @In
	private MsegRolusuariosActionDAO msegRolusuariosActionDAO;
	/**
	 * @uml.property  name="deleteRol"
	 */
	private String deleteRol;
	/**
	 * @uml.property  name="queryCantidadTotal"
	 */
	private int queryCantidadTotal = 0;
	

	
	/**
	 * @uml.property  name="permisoConsulta"
	 */
	private boolean permisoConsulta;
	/**
	 * @uml.property  name="permisoInsertar"
	 */
	private boolean permisoInsertar;
	/**
	 * @uml.property  name="permisoEliminar"
	 */
	private boolean permisoEliminar ;
	/**
	 * @uml.property  name="permisoActualizar"
	 */
	private boolean permisoActualizar;

	public void find(){
		page = 0;
		queryRol();
	}


	public void nextPage(){
		page++;
		queryRol();
	}
	public void firstPage(){
		page = 0;
		queryRol();
	}
	public void lastPage(){
		page = queryCantidadTotal-1;
		queryRol();
	}

	public void backPage(){
		if(page > 0)
		page--;
		queryRol();
	}

	/**
	 * @return
	 * @uml.property  name="page"
	 */
	public int getPage() {
		return page;
	}


	private void queryRol(){
		List lista2= em.createQuery("select h from MsegRoles h where lower(h.rolNombreRol) like #{patternRol} order by rolCodigo")
		.getResultList();
		//System.out.println("lista2.size() " + lista2.size());
		queryCantidadTotal = 0;
		queryCantidadTotal = lista2==null?0:(lista2.size())/pageSize;
		queryCantidadTotal += lista2!=null && (lista2.size()) > pageSize && (lista2.size())%pageSize >0 ? 1:0;
		
		
		listaRol = em.createQuery("select h from MsegRoles h where lower(h.rolNombreRol) like #{patternRol} order by rolCodigo")
		.setMaxResults(pageSize)
		.setFirstResult( page * pageSize )
		.getResultList();
	}

	/**
	 * @return
	 * @uml.property  name="queryCantidadTotal"
	 */
	public int getQueryCantidadTotal(){
		return queryCantidadTotal;
	}

	@Factory(value="patternRol", scope=ScopeType.EVENT)
	public String getSearchPattern(){
		return searchString==null ?
				"%" : '%' + searchString.toLowerCase().replace('*', '%') + '%';
	}

	public boolean isNextPageAvailable()
	{
		return listaRol!=null && listaRol.size()==pageSize;
	}

	/**
	 * @return
	 * @uml.property  name="pageSize"
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param  pageSize
	 * @uml.property  name="pageSize"
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return
	 * @uml.property  name="searchString"
	 */
	public String getSearchString()
	{
		return searchString;
	}

 	public void setSearchString(String searchString)
	{
		this.searchString = searchString;
	}



	public void eliminarRol(){
		MsegRoles m = null;
		if (deleteRol !=null){ 
			m = msegRolesActionDAO.findById( Long.valueOf(deleteRol));
			if(m!= null){ 
				if(msegRolusuariosActionDAO.findByRolUsu(m).size() == 0){
				msegRolmodulosActionDAO.removeLista( msegRolmodulosActionDAO.findRol(m));
				msegRolesActionDAO.remove( m);
				}else{
					facesMessages.add("El rol \""+m.getRolNombreRol() + "\" tiene datos relacionados ");
				 }
				find();
			}else{
				System.out.println("es nulll");
			}
		}

	}







 

	/**
	 * @return
	 * @uml.property  name="deleteRol"
	 */
	public String getDeleteRol() {
		return deleteRol;
	}

	/**
	 * @param  deleteRol
	 * @uml.property  name="deleteRol"
	 */
	public void setDeleteRol(String deleteRol) {
		this.deleteRol = deleteRol;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * @return
	 * @uml.property  name="permisoConsulta"
	 */
	public boolean isPermisoConsulta() {
		return msegRolesActionDAO.isConsulta( msegRoles);
	}

	/**
	 * @return
	 * @uml.property  name="permisoInsertar"
	 */
	public boolean isPermisoInsertar() {
		return msegRolesActionDAO.isInsertar( msegRoles);
	}
	/**
	 * @return
	 * @uml.property  name="permisoEliminar"
	 */
	public boolean isPermisoEliminar() {
		return msegRolesActionDAO.isElimnar( msegRoles);
	}
	/**
	 * @return
	 * @uml.property  name="permisoActualizar"
	 */
	public boolean isPermisoActualizar() {
		return msegRolesActionDAO.isActualizar( msegRoles);
	}



	@Remove
	public void destroy() {}
}
