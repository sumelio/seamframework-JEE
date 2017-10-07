//$Id: UsuarioBuscarAction.java 5509 2007-06-25 16:19:40Z gavin $
package com.leasing.popular.wf.modulos.negocio.persona;

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
import com.leasing.popular.wf.table.negocio.persona.McomPersonas;
import com.leasing.popular.wf.table.negocio.persona.McomPersonasActionDAO;
import com.leasing.popular.wf.table.negocio.persona.McomPersonasDAO;
import com.leasing.popular.wf.table.negocio.persona.McomPersonasId;
import com.leasing.popular.wf.table.seguridad.roles.MsegRoles;



/**
 * @author       Usuario
 */
@Stateful
@Name("perBuscarAction")
@Scope(ScopeType.SESSION)
@Restrict("#{identity.loggedIn}")
public class PerBuscarAction implements  PerBuscar
{

	@PersistenceContext
	private EntityManager em;


	@In
	private   FacesMessages facesMessages;

	@DataModel
	private List<McomPersonas> listaPersona;
	
	McomPersonas mcomPersonaEditar = new McomPersonas();

	@In(required=true, scope=ScopeType.SESSION)
	public MsegRoles msegRoles; 



	private String searchString;
	
	private int pageSize = 10;
	
	private int page = 0;
	
	@In
	private McomPersonasActionDAO mcomPersonasActionDAO;
	
	@In
	private McomNegocioActionDAO mcomNegocioActionDAO;
	
	private String deletePersona;

	private boolean permisoConsulta;
	
	private boolean permisoInsertar;
	
    private boolean permisoEliminar ;
	
	private boolean permisoActualizar;
	
	private int queryCantidadTotal = 0;

	public void find(){
		page = 0;
		queryPersonas();
	}

	public void nextPage(){
		page++;
		queryPersonas();
	}
	
	public int getPage() {
		return page;
	}


	public void firstPage(){
		page = 0;
		queryPersonas();
	}
	public void lastPage(){
		page = queryCantidadTotal-1;
		queryPersonas();
	}

	public void backPage(){
		if(page > 0)
			page--;
		queryPersonas();
	}


	private void queryPersonas(){

		List lista2= em.createQuery("select COALESCE(sum(1),0) from McomPersonas h where lower(h.perNombres) like #{patternPersonas} order by h.perNombres")
		.getResultList();

		int cantidad = ((Long)lista2.get(0)).intValue();
		queryCantidadTotal = cantidad/pageSize;
		queryCantidadTotal +=   cantidad>pageSize && cantidad%pageSize>0?1:0;


		listaPersona = em.createQuery("select h from McomPersonas h where lower(h.perNombres) like #{patternPersonas} order by h.perNombres")
		.setMaxResults(pageSize)
		.setFirstResult( page * pageSize )
		.getResultList();
	}


	public int getQueryCantidadTotal(){
		return queryCantidadTotal;
	}

	@Factory(value="patternPersonas", scope=ScopeType.EVENT)
	public String getSearchPattern(){
		return searchString==null ?
				"%" : '%' + searchString.toLowerCase().replace('*', '%') + '%';
	}

	public boolean isNextPageAvailable()
	{
		return listaPersona!=null && listaPersona.size()==pageSize;
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



	public void eliminarPersona(){
		McomPersonas m = null; 
		if (deletePersona !=null){ 
			System.out.println("deletePersona " + deletePersona);
			McomPersonasId mcomPersonasId = new McomPersonasId();

			mcomPersonasId.setPerIdentificacion(deletePersona.substring(0, deletePersona.lastIndexOf(":")));
			mcomPersonasId.setPerTipoIdentificacion(Long.valueOf(deletePersona.substring(deletePersona.lastIndexOf(":")+1, deletePersona.lastIndexOf("|"))));
			mcomPersonasId.setPerParaTipoId(Long.valueOf(deletePersona.substring(deletePersona.lastIndexOf("|")+1,deletePersona.length())));

			m = mcomPersonasActionDAO.findById( mcomPersonasId );

			if(m!= null){ 
				if(!mcomNegocioActionDAO.findByPersona(m.getId())){
				mcomPersonasActionDAO.remove( m);
				facesMessages.add("Con éxito eliminada la persona con identificación \""+ mcomPersonasId.getPerTipoIdentificacion() + "\"");
				}else{
					facesMessages.add("No es posible eliminar esta informaci{on, existe un negocio relacionado con esta persona.");
				}
				queryPersonas();
			}else{
				System.out.println("es nulll");
			}
		}
		find();

	}









	public String getDeletePersona() {
		return deletePersona;
	}

	public void setDeletePersona(String deletePersona_) {
		this.deletePersona = deletePersona_;
	}













	public boolean isPermisoConsulta() {
		return mcomPersonasActionDAO.isConsulta( msegRoles);
	}

	public boolean isPermisoInsertar() {
		return mcomPersonasActionDAO.isInsertar( msegRoles);
	}
	public boolean isPermisoEliminar() {
		return mcomPersonasActionDAO.isElimnar( msegRoles);
	}
	public boolean isPermisoActualizar() {
		return mcomPersonasActionDAO.isActualizar( msegRoles);
	}



	@Remove
	public void destroy() {}
}
