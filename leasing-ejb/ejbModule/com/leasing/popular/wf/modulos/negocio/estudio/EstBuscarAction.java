//$Id: UsuarioBuscarAction.java 5509 2007-06-25 16:19:40Z gavin $
package com.leasing.popular.wf.modulos.negocio.estudio;

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

import com.leasing.popular.wf.table.negocio.estudio.McomEstudio;
import com.leasing.popular.wf.table.negocio.estudio.McomEstudioActionDAO;
import com.leasing.popular.wf.table.seguridad.roles.MsegRoles;



/**
 * @author       Usuario
 */
@Stateful
@Name("estBuscarAction")
@Scope(ScopeType.SESSION)
@Restrict("#{identity.loggedIn}")
public class EstBuscarAction implements  EstBuscar
{

	@PersistenceContext
	private EntityManager em;
	@In
	private   FacesMessages facesMessages;

	@DataModel
	private List<McomEstudio> listaEstudio;


	@In(required=true, scope=ScopeType.SESSION)
	public MsegRoles msegRoles; 


	private String searchStringEstudio;

	private int pageSize = 10;

	private int page;

	@In
	private McomEstudioActionDAO mcomEstudioActionDAO;  
	private String deleteEstudio;

	private int queryCantidadTotal = 0;

	private boolean permisoConsulta;

	private boolean permisoInsertar;

	private boolean permisoEliminar ;

	private boolean permisoActualizar;

	public void find(){
		page = 0;
		queryEstudio();
	}


	public int getPage() {
		return page;
	}
	public void nextPage(){
		page++;
		queryEstudio();
	}

	public void firstPage(){
		page = 0;
		queryEstudio();
	}
	public void lastPage(){
		page = queryCantidadTotal-1;
		queryEstudio();
	}

	public void backPage(){
		if(page > 0)
			page--;
		queryEstudio();
	}

	private void queryEstudio(){
		List lista2= em.createQuery("select coalesce(sum(1),0) from  McomEstudio estd where  lower(estd.mcomPersonas.perNombres||' '||estd.mcomPersonas.perApellidos) like #{patternEstudio}   order by estd.mcomPersonas.perNombres")
		.getResultList();
		int cantidad = ((Long)lista2.get(0)).intValue();
		queryCantidadTotal = cantidad/pageSize;
		queryCantidadTotal +=   cantidad>pageSize && cantidad%pageSize>0?1:0;



		listaEstudio = em.createQuery("select estd from  McomEstudio estd where  lower(estd.mcomPersonas.perNombres||' '||estd.mcomPersonas.perApellidos) like #{patternEstudio}   order by estd.estId")
		.setMaxResults(pageSize)
		.setFirstResult( page * pageSize )
		.getResultList();
	}



	public int getQueryCantidadTotal(){
		return queryCantidadTotal;
	}
	@Factory(value="patternEstudio", scope=ScopeType.EVENT)
	public String getSearchPatternEstudio(){
		return searchStringEstudio==null ?
				"%" : '%' + searchStringEstudio.toLowerCase().replace('*', '%') + '%';
	}

	public boolean isNextPageAvailable()
	{
		return listaEstudio!=null && listaEstudio.size()==pageSize;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public String getSearchStringEstudio()
	{
		return searchStringEstudio;
	}


	public void setSearchStringEstudio(String searchString)
	{
		this.searchStringEstudio = searchString;
	}



	public void eliminarEstudio(){
		McomEstudio m = null; 
		System.out.println("deleteEstudio " + deleteEstudio);
		if (deleteEstudio !=null){ 
			m = mcomEstudioActionDAO.findById(BigDecimal.valueOf( Long.valueOf(deleteEstudio))  );

			if(m!= null){ 
				mcomEstudioActionDAO.remove( m);
				find();
			}else{
				System.out.println("es nulll");
			}
		}

	}










	public String getDeleteEstudio() {
		return deleteEstudio;
	}


	public void setDeleteEstudio(String deleteRol) {
		this.deleteEstudio = deleteEstudio;
	}














	public boolean isPermisoConsulta() {
		return mcomEstudioActionDAO.isConsulta( msegRoles);
	}


	public boolean isPermisoInsertar() {
		return mcomEstudioActionDAO.isInsertar( msegRoles);
	}

	public boolean isPermisoEliminar() {
		return mcomEstudioActionDAO.isElimnar( msegRoles);
	}

	public boolean isPermisoActualizar() {
		return mcomEstudioActionDAO.isActualizar( msegRoles);
	}



	@Remove
	public void destroy() {}
}
