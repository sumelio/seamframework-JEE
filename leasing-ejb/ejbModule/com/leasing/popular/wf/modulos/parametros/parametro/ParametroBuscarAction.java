//$Id: UsuarioBuscarAction.java 5509 2007-06-25 16:19:40Z gavin $
package com.leasing.popular.wf.modulos.parametros.parametro;

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

import com.leasing.popular.wf.table.parametros.parametro.MpaParametro;
import com.leasing.popular.wf.table.parametros.parametro.MpaParametroActionDAO;
import com.leasing.popular.wf.table.parametros.valores.MpaValoresActionDAO;
import com.leasing.popular.wf.table.seguridad.roles.MsegRoles;



/**
 * @author       Usuario
 */
@Stateful
@Name("parametroBuscarAction")
@Scope(ScopeType.SESSION)
@Restrict("#{identity.loggedIn}")
public class ParametroBuscarAction implements  ParametroBuscar
{

	@PersistenceContext
	private EntityManager em;

	@In(required=true, scope=ScopeType.SESSION)
	public MsegRoles msegRoles; 

	@In 
	private FacesMessages facesMessages;

	@DataModel
	private List<MpaParametro>listaParametros;
	private String searchString;
	private int pageSize = 10;
	private int queryCantidadTotal = 0;
    private int page;
    @In
	private MpaParametroActionDAO  mpaParametroActionDAO;
	@In
	private MpaValoresActionDAO mpaValoresActionDAO;
	private String deleteParametro;
    private MpaParametro mpaParametro = new MpaParametro();
	private boolean permisoConsulta;
	private boolean permisoInsertar;
	private boolean permisoEliminar;
	private boolean permisoActualizar;


	public void find(){
		page = 0;
		queryParametro();
	}

	public void nextPage(){
		page++;
		queryParametro();
	}
	public void firstPage(){
		page = 0;
		queryParametro();
	}
	public void lastPage(){
		page = queryCantidadTotal-1;
		queryParametro();
	}

	public void backPage(){
		if(page > 0)
		page--;
		queryParametro();
	}
	
	

	private void queryParametro(){
		List lista2= em.createQuery("select hh from MpaParametro hh where lower(hh.parNombre) like #{patternParametros}")
		.getResultList();
		
		queryCantidadTotal = lista2==null?0:lista2.size()/pageSize;
		queryCantidadTotal += lista2!=null && lista2.size()>pageSize && lista2.size()%pageSize>0?1:0;
		
		
		listaParametros = em.createQuery("select h from MpaParametro h where lower(h.parNombre) like #{patternParametros} order by h.parNombre")
		.setMaxResults(pageSize)
		.setFirstResult( page * pageSize )
		.getResultList();
		
		
	}


 
	public int getQueryCantidadTotal(){
		return queryCantidadTotal;
	}

	@Factory(value="patternParametros", scope=ScopeType.EVENT)
	public String getSearchPattern(){
		return searchString==null ?
				"%" : '%' + searchString.toLowerCase().replace('*', '%') + '%';
	}

	public boolean isNextPageAvailable()
	{
		return listaParametros!=null &&listaParametros.size()==pageSize;
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



	public void eliminarParametro(){
		MpaParametro m = null;
		if (deleteParametro !=null){ 
			m = mpaParametroActionDAO.findById( Long.valueOf(deleteParametro));
			if(m!= null){
				if(  mpaValoresActionDAO.genereSecuencia(m).longValue() == 1 ){
					mpaParametroActionDAO.remove( m);
					queryParametro();
				}else{
					facesMessages.add("El parametro \""+m.getParNombre() + "\" tiene valores realcionados ");
				}

			}else{
				System.out.println("es nulll");
			}
		}

	}

 
	public void setDeleteParametro(String deleteParametro) {
		this.deleteParametro = deleteParametro;
	}

 
	public String getDeleteParametro() {
		return deleteParametro;
	}

 
	public boolean isPermisoConsulta() {
		return mpaValoresActionDAO.isConsulta( msegRoles);
	}
 
	public boolean isPermisoInsertar() {
		return  mpaValoresActionDAO.isInsertar( msegRoles);
	}
 
	public boolean isPermisoEliminar() {
		return mpaValoresActionDAO.isElimnar( msegRoles);
	}
 
	public boolean isPermisoActualizar() {
		return mpaValoresActionDAO.isActualizar( msegRoles);
	}

 
	public int getPage() {
		return page;
	}

 
	public void setPage(int page) {
		this.page = page;
	} 
 
	public void setQueryCantidadTotal(int queryCantidadTotal) {
		this.queryCantidadTotal = queryCantidadTotal;
	}

	@Remove
	public void destroy() {}
}
