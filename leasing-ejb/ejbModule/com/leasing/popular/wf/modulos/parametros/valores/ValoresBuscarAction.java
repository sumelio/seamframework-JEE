//$Id: UsuarioBuscarAction.java 5509 2007-06-25 16:19:40Z gavin $
package com.leasing.popular.wf.modulos.parametros.valores;

import java.sql.BatchUpdateException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.faces.convert.Converter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.exception.ConstraintViolationException;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.faces.FacesMessages;

import com.leasing.popular.wf.generico.view.converter.ConverterParametro;
import com.leasing.popular.wf.table.parametros.parametro.MpaParametro;
import com.leasing.popular.wf.table.parametros.parametro.MpaParametroActionDAO;
import com.leasing.popular.wf.table.parametros.parametro.MpaParametroDAO;
import com.leasing.popular.wf.table.parametros.valores.MpaValores;
import com.leasing.popular.wf.table.parametros.valores.MpaValoresActionDAO;
import com.leasing.popular.wf.table.parametros.valores.MpaValoresDAO;
import com.leasing.popular.wf.table.parametros.valores.MpaValoresId;
import com.leasing.popular.wf.table.seguridad.roles.MsegRoles;



/**
 * @author       Usuario
 */
@Stateful
@Name("valoresBuscarAction")
@Scope(ScopeType.SESSION)
@Restrict("#{identity.loggedIn}")
public class ValoresBuscarAction implements  ValoresBuscar
{

	@PersistenceContext
	private EntityManager em;

	/**
	 * @uml.property  name="msegRoles"
	 * @uml.associationEnd  
	 */
	@In(required=true, scope=ScopeType.SESSION)
	public MsegRoles msegRoles; 

	@DataModel
	private List<MpaValores> listaValores;

	@In
	private   FacesMessages facesMessages;

	/**
	 * @uml.property  name="searchString"
	 */
	private String searchString;
	/**
	 * @uml.property  name="searchStringP"
	 * @uml.associationEnd  
	 */
	private MpaParametro searchStringP;
	/**
	 * @uml.property  name="pageSize"
	 */
	private int pageSize = 10;
	/**
	 * @uml.property  name="page"
	 */
	private int page;
	/**
	 * @uml.property  name="queryCantidadTotal"
	 */
	private int queryCantidadTotal = 0;
	/**
	 * @uml.property  name="mpaParametroBuscar"
	 * @uml.associationEnd  
	 */
	private MpaParametro mpaParametroBuscar = new MpaParametro();
	/**
	 * @uml.property  name="mpaParametroActionDAO"
	 * @uml.associationEnd  
	 */
	@In
	private MpaParametroActionDAO mpaParametroActionDAO;
	/**
	 * @uml.property  name="mpaValoresDAO"
	 * @uml.associationEnd  
	 */
	private MpaValoresActionDAO mpaValoresActionDAO ;
	/**
	 * @uml.property  name="deleteValores"
	 */
	private String deleteValores;
	/**
	 * @uml.property  name="listaParametroValores"
	 */
	private List<MpaParametro> listaParametroValores = new ArrayList<MpaParametro>();
	/**
	 * @uml.property  name="mpaValores"
	 * @uml.associationEnd  
	 */
	private MpaValores mpaValores = new MpaValores();

	public void find(){ 
		page = 0;
		queryValores();
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
		queryValores();
	}

	public void firstPage(){
		page = 0;
		queryValores();
	}
	public void lastPage(){
		page = queryCantidadTotal-1;
		queryValores();
	}

	public void backPage(){
		if(page > 0)
			page--;
		queryValores();
	}


	private void queryValores(){
		searchStringP = searchStringP==null ?new MpaParametro(new Long(0)) : searchStringP;


		List lista2= em.createQuery("select   COALESCE(sum(1),0) from MpaValores h where lower(h.varNombre) like #{patternValores} and (:mp1 = :mp OR h.mpaParametro.parCodigo = :mp2)  order by h.mpaParametro.parCodigo, h.varNombre")
		.setParameter("mp1", new Long(0))
		.setParameter("mp", searchStringP.getParCodigo())
		.setParameter("mp2", searchStringP.getParCodigo())
		.getResultList();
		int cantidad = ((Long)lista2.get(0)).intValue();
		queryCantidadTotal = cantidad/pageSize;
		queryCantidadTotal +=   cantidad>pageSize && cantidad%pageSize>0?1:0;



		listaValores = em.createQuery("select h from MpaValores h where lower(h.varNombre) like #{patternValores} and (:mp1 = :mp OR h.mpaParametro.parCodigo = :mp2)  order by h.mpaParametro.parNombre, h.varNombre")
		.setParameter("mp1", new Long(0))
		.setParameter("mp", searchStringP.getParCodigo())
		.setParameter("mp2", searchStringP.getParCodigo())
		.setMaxResults(pageSize)
		.setFirstResult( page * pageSize )
		.getResultList();
		System.out.println("listaValores " + listaValores.size());
	}


	/**
	 * @return
	 * @uml.property  name="queryCantidadTotal"
	 */
	public int getQueryCantidadTotal(){
		return queryCantidadTotal;
	}


	@Factory(value="patternValores", scope=ScopeType.EVENT)
	public String getSearchPattern(){
		return searchString==null ?
				"%" : '%' + searchString.toLowerCase().replace('*', '%') + '%';
	}



	@Factory(value="patternValoresP", scope=ScopeType.EVENT)
	public MpaParametro getSearchPatternP(){
		return searchStringP==null ?new MpaParametro(new Long(0)) : searchStringP;
	}

	public boolean isNextPageAvailable()
	{
		return listaParametroValores!=null && listaParametroValores.size()==pageSize;
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



	/**
	 * @return
	 * @uml.property  name="searchStringP"
	 */
	public MpaParametro getSearchStringP() {
		//find();
		return searchStringP;
	}

	/**
	 * @param  searchStringP
	 * @uml.property  name="searchStringP"
	 */
	public void setSearchStringP(MpaParametro searchStringP) {
		this.searchStringP = searchStringP ==null ?new MpaParametro(new Long(0)) : searchStringP;;
	}

	/* (non-Javadoc)
	 * @see com.leasing.popular.wf.modulos.parametros.valores.ValoresBuscar#eliminarValor()
	 */
	public void eliminarValor(){
		MpaValores m = null;
		if(deleteValores !=null && deleteValores.lastIndexOf("|") > 0){
			MpaParametro mp = mpaParametroActionDAO.findById(  Long.valueOf(deleteValores.substring(deleteValores.lastIndexOf("|")+1,deleteValores.length())));
			System.out.println("mp " + mp.getParNombre());
			if(mp != null){
				MpaValoresId id = new MpaValoresId(Long.valueOf(deleteValores.substring(0, deleteValores.lastIndexOf("|"))),Long.valueOf(deleteValores.substring(deleteValores.lastIndexOf("|")+1,deleteValores.length())));

				m = mpaValoresActionDAO.findById( id );
				System.out.println("m.getVarNombre() " + m.getVarNombre());
				if(m!= null){ 
					//if(!mpaValoresActionDAO.isEliminar( id)){

					try { 
						em.remove(m) ;
						em.flush();
						queryValores();
						facesMessages.add( "Con exito eliminar el registro: " + m.getVarNombre());
						//facesMessages.add("Con exito eliminar el registro: " + m.getVarNombre());
					} catch (RuntimeException re) { 
						re.printStackTrace();
						System.out.println(re.getCause() );
						System.out.println("------------------------------------------");
						System.out.println(re.getLocalizedMessage() );
						facesMessages.add( "No es posible eliminar el registro: restricción de integridad (HLPDSK.FK_PERSONAS_VALORES_CIUDAD) violada - registro secundario encontrado");
					}

				}else{
					System.out.println("es nulll");
				}
			}
		}
	}/*
	MpaParametro m = null;
	if (deleteParametro !=null){ 
		m = mpaParametroDAO.findById(  Long.valueOf(deleteParametro));
		if(m!= null){
			if(  mpaValoresActionDAO.genereSecuencia( m).longValue() == 1 ){
				mpaParametroDAO.remove(  m);
				queryParametro();
			}else{
				facesMessages.add("El parametro \""+m.getParNombre() + "\" tiene valores realcionados ");
			}

		}else{
			System.out.println("es nulll");
		}
	}
	 */

	/**
	 * @return
	 * @uml.property  name="listaParametroValores"
	 */
	public List<MpaParametro> getListaParametroValores() {
		listaParametroValores = mpaParametroActionDAO.findAll( );
		return listaParametroValores;
	}

	/**
	 * @param  listaParametros
	 * @uml.property  name="listaParametroValores"
	 */
	public void setListaParametroValores(List<MpaParametro> listaParametros) {
		this.listaParametroValores = listaParametros;
	}


	public Converter getConverter() {
		return new ConverterParametro(mpaParametroActionDAO.findAll( ));
	}




	/**
	 * @return
	 * @uml.property  name="mpaParametroBuscar"
	 */
	public MpaParametro getMpaParametroBuscar() {
		return mpaParametroBuscar;
	}

	/**
	 * @param  tipoParametroBuscar
	 * @uml.property  name="mpaParametroBuscar"
	 */
	public void setMpaParametroBuscar(MpaParametro tipoParametroBuscar) {
		this.mpaParametroBuscar = tipoParametroBuscar;
	}

	/**
	 * @return
	 * @uml.property  name="deleteValores"
	 */
	public String getDeleteValores() {
		return deleteValores;
	}

	/**
	 * @param  deleteValores
	 * @uml.property  name="deleteValores"
	 */
	public void setDeleteValores(String deleteValores) {
		this.deleteValores = deleteValores;
	}




	public boolean isPermisoConsulta() {
		return mpaValoresActionDAO.isConsulta(  msegRoles);
	}

	public boolean isPermisoInsertar() {
		return mpaValoresActionDAO.isInsertar(  msegRoles);
	}
	public boolean isPermisoEliminar() {
		return mpaValoresActionDAO.isElimnar(  msegRoles);
	}
	public boolean isPermisoActualizar() {
		return mpaValoresActionDAO.isActualizar(  msegRoles);
	}





	@Remove
	public void destroy() {}
}
