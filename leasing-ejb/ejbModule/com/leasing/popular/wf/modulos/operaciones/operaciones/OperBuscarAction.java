//$Id: UsuarioBuscarAction.java 5509 2007-06-25 16:19:40Z gavin $
package com.leasing.popular.wf.modulos.operaciones.operaciones;

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

import com.leasing.popular.wf.table.operaciones.operaciones.MoperOperaciones;
import com.leasing.popular.wf.table.operaciones.operaciones.MoperOperacionesActionDAO;  
import com.leasing.popular.wf.table.seguridad.roles.MsegRoles;



/**
 * @author       Usuario
 */
@Stateful
@Name("operBuscarAction")
@Scope(ScopeType.SESSION)
@Restrict("#{identity.loggedIn}")
public class OperBuscarAction implements  OperBuscar
{

	@PersistenceContext
	private EntityManager em;

	@In(required=true, scope=ScopeType.SESSION)
	public MsegRoles msegRoles; 

	@In 
	private FacesMessages facesMessages;

	@DataModel
	private List<MoperOperaciones>listaOperaciones;
	private String searchString;
	private int pageSize = 10;
	private int queryCantidadTotal = 0;
    private int page;
  
	@In
	private MoperOperacionesActionDAO  moperOperacionesActionDAO;
	private String deleteOperacion;
    private MoperOperaciones mpaOperaciones= new MoperOperaciones();
	private boolean permisoConsulta;
	private boolean permisoInsertar;
	private boolean permisoEliminar;
	private boolean permisoActualizar;


	public void find(){
		page = 0;
		queryOperacion();
	}

	public void nextPage(){
		page++;
		queryOperacion();
	}
	public void firstPage(){
		page = 0;
		queryOperacion();
	}
	public void lastPage(){
		page = queryCantidadTotal-1;
		queryOperacion();
	}

	public void backPage(){
		if(page > 0)
		page--;
		queryOperacion();
	}
	
	
 
	private void queryOperacion(){
		List lista2= em.createQuery("select h from MoperOperaciones h order by operId")
		.getResultList();
		
		queryCantidadTotal = lista2==null?0:lista2.size()/pageSize;
		queryCantidadTotal += lista2!=null && lista2.size()>pageSize && lista2.size()%pageSize>0?1:0;
		
		
		listaOperaciones = em.createQuery("select h from MoperOperaciones h order by operId")
		.setMaxResults(pageSize)
		.setFirstResult( page * pageSize )
		.getResultList();
		
		
	}

 
	public int getQueryCantidadTotal(){
		return queryCantidadTotal;
	}

	@Factory(value="patternOperaciones", scope=ScopeType.EVENT)
	public String getSearchPattern(){
		return searchString==null ?
				"%" : '%' + searchString.toLowerCase().replace('*', '%') + '%';
	}

	public boolean isNextPageAvailable()
	{
		return listaOperaciones!=null && listaOperaciones.size()==pageSize;
	}

 
	public int getPageSize() {
		return pageSize;
	}

 
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

 
	public String getSearchString()
	{
		return searchString;
	}

 
	public void setSearchString(String searchString)
	{
		this.searchString = searchString;
	}



	public void eliminarOperacion(){
		MoperOperaciones m = null;
		if (deleteOperacion !=null){ 
			m = moperOperacionesActionDAO.findById( Long.valueOf(deleteOperacion));
			if(m!= null){
			 moperOperacionesActionDAO.remove( m);
					queryOperacion();
			 

			}else{
				System.out.println("es nulll");
			}
		}

	}

 
	public void setDeleteOperacion(String deleteOperacion) {
		this.deleteOperacion = deleteOperacion;
	}



 
	public String getDeleteOperacion() {
		return deleteOperacion;
	}



 
	public boolean isPermisoConsulta() {
		return moperOperacionesActionDAO.isConsulta( msegRoles);
	}
 
	public boolean isPermisoInsertar() {
		return  moperOperacionesActionDAO.isInsertar( msegRoles);
	}
 
	public boolean isPermisoEliminar() {
		return moperOperacionesActionDAO.isElimnar( msegRoles);
	}
 
	public boolean isPermisoActualizar() {
		return moperOperacionesActionDAO.isActualizar( msegRoles);
	}


	/**
	 * @return
	 * @uml.property  name="page"
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param  page
	 * @uml.property  name="page"
	 */
	public void setPage(int page) {
		this.page = page;
	} 



	/**
	 * @param  queryCantidadTotal
	 * @uml.property  name="queryCantidadTotal"
	 */
	public void setQueryCantidadTotal(int queryCantidadTotal) {
		this.queryCantidadTotal = queryCantidadTotal;
	}

	@Remove
	public void destroy() {}
}
