//$Id: UsuarioBuscarAction.java 5509 2007-06-25 16:19:40Z gavin $
package com.leasing.popular.wf.modulos.parametros.valores;

import java.util.List;

import javax.ejb.Local;
import javax.faces.convert.Converter;

import com.leasing.popular.wf.table.parametros.parametro.MpaParametro;


/**
 * @author          Usuario
 */
@Local
public interface ValoresBuscar
{


	public void nextPage();
	public String getSearchPattern();
	public boolean isNextPageAvailable();
	/**
	 * @return
	 * @uml.property  name="pageSize"
	 */
	public int getPageSize() ;
	/**
	 * @param  pageSize
	 * @uml.property  name="pageSize"
	 */
	public void setPageSize(int pageSize) ;
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
    /**
	 * @uml.property  name="searchStringP"
	 * @uml.associationEnd  
	 */
    public MpaParametro getSearchStringP();
	/**
	 * @param  searchStringP
	 * @uml.property  name="searchStringP"
	 */
	public void setSearchStringP(MpaParametro searchStringP);
	public void eliminarValor();
	public void find(); 
	public int getPage();
	public void firstPage();
	public void lastPage();
	public void backPage();
	public int getQueryCantidadTotal();
	/**
	 * @return
	 * @uml.property  name="listaParametroValores"
	 */
	public List<MpaParametro> getListaParametroValores();
	/**
	 * @param  listaParametros
	 * @uml.property  name="listaParametroValores"
	 */
	public void setListaParametroValores(List<MpaParametro> listaParametros);
    public Converter getConverter();
    /**
	 * @uml.property  name="mpaParametroBuscar"
	 * @uml.associationEnd  
	 */
    public MpaParametro getMpaParametroBuscar();
    /**
	 * @param  tipoParametroBuscar
	 * @uml.property  name="mpaParametroBuscar"
	 */
    public void setMpaParametroBuscar(MpaParametro tipoParametroBuscar);
    /**
	 * @return
	 * @uml.property  name="deleteValores"
	 */
    public String getDeleteValores();
    /**
	 * @param  deleteParametro
	 * @uml.property  name="deleteValores"
	 */
    public void setDeleteValores(String deleteParametro) ;
     public boolean isPermisoConsulta();
	 public boolean isPermisoInsertar();
	 public boolean isPermisoEliminar();
	 public boolean isPermisoActualizar();
	
	
    public void destroy();

}