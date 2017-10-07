//$Id: UsuarioBuscarAction.java 5509 2007-06-25 16:19:40Z gavin $
package com.leasing.popular.wf.modulos.parametros.parametro;

import javax.ejb.Local;

/**
 * @author          Usuario
 */
@Local
public interface ParametroBuscar
{
	public void nextPage();
	public void backPage();
	public void firstPage();
	public void lastPage();
	public String getSearchPattern();
	public boolean isNextPageAvailable();
	/**
	 * @return
	 * @uml.property  name="pageSize"
	 */
	public int getPageSize();
	/**
	 * @param  pageSize
	 * @uml.property  name="pageSize"
	 */
	public void setPageSize(int pageSize);
	/**
	 * @return
	 * @uml.property  name="searchString"
	 */
	public String getSearchString();
	/**
	 * @param  searchString
	 * @uml.property  name="searchString"
	 */
	public void setSearchString(String searchString);
	public void eliminarParametro();
	public void find();
	/**
	 * @return
	 * @uml.property  name="deleteParametro"
	 */
	public String getDeleteParametro();
	/**
	 * @param  deleteParametro
	 * @uml.property  name="deleteParametro"
	 */
	public void setDeleteParametro(String deleteParametro);
	public boolean isPermisoConsulta();
	public boolean isPermisoInsertar();
	public boolean isPermisoEliminar();
	public boolean isPermisoActualizar();
	/**
	 * @return
	 * @uml.property  name="page"
	 */
	public int getPage();
	/**
	 * @param  page
	 * @uml.property  name="page"
	 */
	public void setPage(int page);
	/**
	 * @return
	 * @uml.property  name="queryCantidadTotal"
	 */
	public int getQueryCantidadTotal();
	/**
	 * @param  queryCantidadTotal
	 * @uml.property  name="queryCantidadTotal"
	 */
	public void setQueryCantidadTotal(int queryCantidadTotal);
	public void destroy();

}