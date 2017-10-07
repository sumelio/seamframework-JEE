package com.leasing.popular.wf.modulos.negocio.negocio;

import java.math.BigDecimal;
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

import com.leasing.popular.wf.table.negocio.negocio.McomNegocio;
import com.leasing.popular.wf.table.negocio.negocio.McomNegocioActionDAO;
import com.leasing.popular.wf.table.negocio.negocio.McomNegocioDAO;
import com.leasing.popular.wf.table.seguridad.roles.MsegRoles;


@Stateful
@Name("negBuscarAction")
@Scope(ScopeType.SESSION)
@Restrict("#{identity.loggedIn}")
public class NegBuscarAction implements  NegBuscar
{

	@PersistenceContext
	private EntityManager em;


	@In
	private   FacesMessages facesMessages;

	 

	@DataModel
	private List<McomNegocio> listaNegocio;

	@In(required=true, scope=ScopeType.SESSION)
	public MsegRoles msegRoles; 


	private String searchStringNegocio;
	private int pageSize = 10;
	private int page;
	 

	@In
	private McomNegocioActionDAO  mcomNegocioActionDAO;

	private String deleteNegocio;
	private int queryCantidadTotal = 0;
	private boolean permisoConsulta;
	private boolean permisoInsertar;
	private boolean permisoEliminar ;
	private boolean permisoActualizar;

	public void find(){
		page = 0;
		queryNegocio();
	}

	/**
	 * @return
	 * @uml.property  name="page"
	 */
	public int getPage() {
		return page;
	}
	public void nextPage(){
		page++;
		queryNegocio();
	}

	public void firstPage(){
		page = 0;
		queryNegocio();
	}
	public void lastPage(){
		page = queryCantidadTotal-1;
		queryNegocio();
	}

	public void backPage(){
		if(page > 0)
			page--;
		queryNegocio();
	}

	private void queryNegocio(){
		List lista2= em.createQuery("select COALESCE(sum(1),0) from McomNegocio h where lower(h.mcomPersonasByFkClientePersonas.perNombres||' '||h.mcomPersonasByFkClientePersonas.perApellidos)  like   #{patternNegocio} order by h.negFecha")
		.getResultList();
		int cantidad = ((Long)lista2.get(0)).intValue();
		queryCantidadTotal = cantidad/pageSize;
		queryCantidadTotal +=   cantidad>pageSize && cantidad%pageSize>0?1:0;



		listaNegocio = em.createQuery("select h from McomNegocio h where lower(h.mcomPersonasByFkClientePersonas.perNombres||' '||h.mcomPersonasByFkClientePersonas.perApellidos)  like   #{patternNegocio} order by h.negFecha")
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

	@Factory(value="patternNegocio", scope=ScopeType.EVENT)
	public String getSearchPatternNegocio(){
		return searchStringNegocio==null ?
				"%" : '%' + searchStringNegocio.toLowerCase().replace('*', '%') + '%';
	}

	public boolean isNextPageAvailable()
	{
		return listaNegocio!=null && listaNegocio.size()==pageSize;
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
	 * @uml.property  name="searchStringNegocio"
	 */
	public String getSearchStringNegocio()
	{
		return searchStringNegocio;
	}

	/**
	 * @param  searchString
	 * @uml.property  name="searchStringNegocio"
	 */
	public void setSearchStringNegocio(String searchString)
	{
		this.searchStringNegocio = searchString;
	}



	public void eliminarNegocio(){
		McomNegocio m = null; 
		System.out.println("deleteNegocio " + deleteNegocio);
		if (deleteNegocio !=null){ 
			m = mcomNegocioActionDAO.findById( BigDecimal.valueOf(Long.valueOf( deleteNegocio)));

			if(m!= null){ 
				mcomNegocioActionDAO.remove( m);
				queryNegocio();
			}else{
				System.out.println("es nulll");
			}
		}

	}









	/**
	 * @return
	 * @uml.property  name="deleteNegocio"
	 */
	public String getDeleteNegocio() {
		return deleteNegocio;
	}

	/**
	 * @param  deleteNegocio_
	 * @uml.property  name="deleteNegocio"
	 */
	public void setDeleteNegocio(String deleteNegocio_) {
		this.deleteNegocio = deleteNegocio_;
	}













	/**
	 * @return
	 * @uml.property  name="permisoConsulta"
	 */
	public boolean isPermisoConsulta() {
		return mcomNegocioActionDAO.isConsulta(  msegRoles);
	}

	/**
	 * @return
	 * @uml.property  name="permisoInsertar"
	 */
	public boolean isPermisoInsertar() {
		return mcomNegocioActionDAO.isInsertar(  msegRoles);
	}
	/**
	 * @return
	 * @uml.property  name="permisoEliminar"
	 */
	public boolean isPermisoEliminar() {
		return mcomNegocioActionDAO.isElimnar(  msegRoles);
	}
	/**
	 * @return
	 * @uml.property  name="permisoActualizar"
	 */
	public boolean isPermisoActualizar() {
		return mcomNegocioActionDAO.isActualizar(  msegRoles);
	}



	@Remove
	public void destroy() {}
}
