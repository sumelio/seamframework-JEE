//$Id: UsuarioBuscarAction.java 5509 2007-06-25 16:19:40Z gavin $
package com.leasing.popular.wf.modulos.negocio.negocio
;

import javax.ejb.Local;

/**
 * @author          Usuario
 */
@Local
public interface NegBuscar
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
	 * @uml.property  name="searchStringNegocio"
	 */
	public String getSearchStringNegocio();
	/**
	 * @param  searchString
	 * @uml.property  name="searchStringNegocio"
	 */
	public void setSearchStringNegocio(String searchString);
	public String getSearchPatternNegocio();
	public void find(); 
	public int getPage();
	public void firstPage();
	public void nextPage();
	public void lastPage();
	public void backPage();
	public int getQueryCantidadTotal();
	public boolean isNextPageAvailable();
	public void eliminarNegocio();
	/**
	 * @return
	 * @uml.property  name="deleteNegocio"
	 */
	public String getDeleteNegocio();
	/**
	 * @param  deleteNegocio_
	 * @uml.property  name="deleteNegocio"
	 */
	public void setDeleteNegocio(String deleteNegocio_);
	public boolean isPermisoConsulta();
	public boolean isPermisoInsertar();
	public boolean isPermisoEliminar();
	public boolean isPermisoActualizar();
	public void destroy();

}