//$Id: UsuarioBuscarAction.java 5509 2007-06-25 16:19:40Z gavin $
package com.leasing.popular.wf.modulos.negocio.estudio
;

import javax.ejb.Local;

/**
 * @author          Usuario
 */
@Local
public interface EstBuscar
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
	 * @uml.property  name="searchStringEstudio"
	 */
	public String getSearchStringEstudio();
	/**
	 * @param  searchString
	 * @uml.property  name="searchStringEstudio"
	 */
	public void setSearchStringEstudio(String searchString);
	public String getSearchPatternEstudio();
	public void find(); 
	public int getPage();
	public void firstPage();
	public void lastPage();
	public void backPage();
	public void nextPage();
	public int getQueryCantidadTotal();
	public boolean isNextPageAvailable();
	public void eliminarEstudio();
	/**
	 * @return
	 * @uml.property  name="deleteEstudio"
	 */
	public String getDeleteEstudio();
	/**
	 * @param  deleteRol
	 * @uml.property  name="deleteEstudio"
	 */
	public void setDeleteEstudio(String deleteRol);
	public boolean isPermisoConsulta();
	public boolean isPermisoInsertar();
	public boolean isPermisoEliminar();
	public boolean isPermisoActualizar();
	public void destroy();

}