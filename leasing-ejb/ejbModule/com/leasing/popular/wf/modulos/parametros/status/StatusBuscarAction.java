//$Id: UsuarioBuscarAction.java 5509 2007-06-25 16:19:40Z gavin $
package com.leasing.popular.wf.modulos.parametros.status;

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

import com.leasing.popular.wf.table.parametros.status.MoperStatus;
import com.leasing.popular.wf.table.parametros.status.MoperStatusActionDAO;
import com.leasing.popular.wf.table.seguridad.roles.MsegRoles;



/**
 * @author       Usuario
 */
@Stateful
@Name("statusBuscarAction")
@Scope(ScopeType.SESSION)
@Restrict("#{identity.loggedIn}")
public class StatusBuscarAction implements  StatusBuscar
{

	@PersistenceContext
	private EntityManager em;

	@In(required=true, scope=ScopeType.SESSION)
	public MsegRoles msegRoles; 

	@In 
	private FacesMessages facesMessages;

	@DataModel
	private List<MoperStatus>listaStatus;
	private String searchString;
	private int pageSize = 10;
	private int queryCantidadTotal = 0;
    private int page;
    @In
	private MoperStatusActionDAO  moperStatusActionDAO;
	
	private String deleteStatus;
    private MoperStatus mpaStatus = new MoperStatus();
	private boolean permisoConsulta;
	private boolean permisoInsertar;
	private boolean permisoEliminar;
	private boolean permisoActualizar;


	public void find(){
		page = 0;
		queryStatus();
	}

	public void nextPage(){
		page++;
		queryStatus();
	}
	public void firstPage(){
		page = 0;
		queryStatus();
	}
	public void lastPage(){
		page = queryCantidadTotal-1;
		queryStatus();
	}

	public void backPage(){
		if(page > 0)
		page--;
		queryStatus();
	}
	
	

	private void queryStatus(){
		List lista2= em.createQuery("select h from MoperStatus h  where lower(h.staNombre) like #{patternStatuss} order by h.staId")
		.getResultList();
		
		queryCantidadTotal = lista2==null?0:lista2.size()/pageSize;
		queryCantidadTotal += lista2!=null && lista2.size()>pageSize && lista2.size()%pageSize>0?1:0;
		
		
		listaStatus = em.createQuery("select h from MoperStatus h  where lower(h.staNombre) like #{patternStatuss} order by h.staId")
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

	@Factory(value="patternStatuss", scope=ScopeType.EVENT)
	public String getSearchPattern(){
		return searchString==null ?
				"%" : '%' + searchString.toLowerCase().replace('*', '%') + '%';
	}

	public boolean isNextPageAvailable()
	{
		return listaStatus!=null &&listaStatus.size()==pageSize;
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

	/**
	 * @param  searchString
	 * @uml.property  name="searchString"
	 */
	public void setSearchString(String searchString)
	{
		this.searchString = searchString;
	}



	public void eliminarStatus(){
		MoperStatus m = null;
		if (deleteStatus !=null){ 
			m = moperStatusActionDAO.findById(Long.valueOf(deleteStatus));
			if(m!= null){
				moperStatusActionDAO.remove( m);
				facesMessages.add("El Status \""+m.getStaNombre() + "\" fue eliminado exitosamente.");
					queryStatus();
						}

			}else{
				System.out.println("es nulll");
			}
		}

	/**
	 * @param  deleteStatus
	 * @uml.property  name="deleteStatus"
	 */
	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}



	/**
	 * @return
	 * @uml.property  name="deleteStatus"
	 */
	public String getDeleteStatus() {
		return deleteStatus;
	}



	/**
	 * @return
	 * @uml.property  name="permisoConsulta"
	 */
	public boolean isPermisoConsulta() {
		return moperStatusActionDAO.isConsulta( msegRoles);
	}
	/**
	 * @return
	 * @uml.property  name="permisoInsertar"
	 */
	public boolean isPermisoInsertar() {
		return  moperStatusActionDAO.isInsertar( msegRoles);
	}
	/**
	 * @return
	 * @uml.property  name="permisoEliminar"
	 */
	public boolean isPermisoEliminar() {
		return moperStatusActionDAO.isElimnar( msegRoles);
	}
	/**
	 * @return
	 * @uml.property  name="permisoActualizar"
	 */
	public boolean isPermisoActualizar() {
		return moperStatusActionDAO.isActualizar( msegRoles);
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
