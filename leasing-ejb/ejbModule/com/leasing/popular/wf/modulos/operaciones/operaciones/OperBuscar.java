//$Id: UsuarioBuscarAction.java 5509 2007-06-25 16:19:40Z gavin $
package com.leasing.popular.wf.modulos.operaciones.operaciones;

import javax.ejb.Local;

/**
 * @author          Usuario
 */
@Local
public interface OperBuscar
{
	public void nextPage();
	public void backPage();
	public void firstPage();
	public void lastPage();
	public String getSearchPattern();
	public boolean isNextPageAvailable();
	public int getPageSize();
	public void setPageSize(int pageSize);
	public String getSearchString();
	public void setSearchString(String searchString);
	public void eliminarOperacion();
	public void find();
	public String getDeleteOperacion();
	public void setDeleteOperacion(String deleteOperacion);
	public boolean isPermisoConsulta();
	public boolean isPermisoInsertar();
	public boolean isPermisoEliminar();
	public boolean isPermisoActualizar();
	public int getPage();
	public void setPage(int page);
	public int getQueryCantidadTotal();
	public void setQueryCantidadTotal(int queryCantidadTotal);
	public void destroy();

}