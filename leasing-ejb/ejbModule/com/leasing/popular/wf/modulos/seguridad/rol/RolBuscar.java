//$Id: UsuarioBuscarAction.java 5509 2007-06-25 16:19:40Z gavin $
package com.leasing.popular.wf.modulos.seguridad.rol
;

import javax.ejb.Local;

/**
 * @author          Usuario
 */
@Local
public interface RolBuscar
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
	public void nextPage(); 
	public void firstPage();
	public void lastPage();
	public void backPage();
	public int getPage();
	public int getQueryCantidadTotal();
	public boolean isNextPageAvailable();
	public void eliminarRol();
	/**
	 * @return
	 * @uml.property  name="deleteRol"
	 */
	public String getDeleteRol();
	/**
	 * @param  deleteRol
	 * @uml.property  name="deleteRol"
	 */
	public void setDeleteRol(String deleteRol);
	public boolean isPermisoConsulta();
    public boolean isPermisoInsertar();
	public boolean isPermisoEliminar();
	public boolean isPermisoActualizar();
	public void destroy();

}