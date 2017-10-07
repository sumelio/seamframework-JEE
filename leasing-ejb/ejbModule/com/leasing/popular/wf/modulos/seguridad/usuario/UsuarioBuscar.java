//$Id: UsuarioBuscarAction.java 5509 2007-06-25 16:19:40Z gavin $
package com.leasing.popular.wf.modulos.seguridad.usuario;

import javax.ejb.Local;

/**
 * @author          Usuario
 */
@Local
public interface UsuarioBuscar
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
	public int getPage();
	public void firstPage();
	public void lastPage();
	public void backPage();
	public boolean isNextPageAvailable();
	public void eliminarUsuario();
	/**
	 * @return
	 * @uml.property  name="deleteUsuario"
	 */
	public String getDeleteUsuario();
	/**
	 * @param  deleteUsuario
	 * @uml.property  name="deleteUsuario"
	 */
	public void setDeleteUsuario(String deleteUsuario);
	public boolean getPermisoConsulta();
	public boolean getPermisoInsertar();
	public boolean getPermisoEliminar();
	public boolean getPermisoActualizar();
	public int getQueryCantidadTotal();
	public void destroy();

}