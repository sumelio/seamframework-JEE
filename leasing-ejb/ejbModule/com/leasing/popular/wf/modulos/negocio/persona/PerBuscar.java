//$Id: UsuarioBuscarAction.java 5509 2007-06-25 16:19:40Z gavin $
package com.leasing.popular.wf.modulos.negocio.persona
;

import javax.ejb.Local;

/**
 * @author          Usuario
 */
@Local
public interface PerBuscar
{
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
	public String getSearchPattern();
	public void find(); 
	public int getPage();
	public void firstPage();
	public void nextPage();
	public void lastPage();
	public void backPage();
	public int getQueryCantidadTotal();
	public boolean isNextPageAvailable();
	public void eliminarPersona();
	/**
	 * @return
	 * @uml.property  name="deletePersona"
	 */
	public String getDeletePersona();
	/**
	 * @param  deletePersona
	 * @uml.property  name="deletePersona"
	 */
	public void setDeletePersona(String deletePersona);
	public boolean isPermisoConsulta();
	public boolean isPermisoInsertar();
	public boolean isPermisoEliminar();
	public boolean isPermisoActualizar();
	public void destroy();

}