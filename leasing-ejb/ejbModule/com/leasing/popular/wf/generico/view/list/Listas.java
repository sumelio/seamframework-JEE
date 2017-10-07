package com.leasing.popular.wf.generico.view.list;

import java.util.List;

import javax.ejb.Local;
import javax.faces.convert.Converter;

import com.leasing.popular.wf.table.negocio.persona.McomPersonas;
import com.leasing.popular.wf.table.parametros.status.MoperStatus;
import com.leasing.popular.wf.table.parametros.valores.MpaValores;




/**
 * @author          Usuario
 */
@Local
public interface Listas{
	 
	public Converter getConverterPersona1(); 
	public Converter getConverterTipoId();
	public Converter getConverterCiudad();
	public Converter getConverterTipoBien() ;
	public Converter getConverterMarcaAutomovil();
	public Converter getConverterTipoProveedor() ;
	public Converter getConverterContComercial() ;
	public Converter getConverterLineaVehiculo();
	public Converter getConverterTipoVehiculo();
	public Converter getConverterClaseVehiculo();
	public Converter getConverterTipoEjecutivo() ;
	public Converter getConverterPlanCredito();
	public Converter getConverterTipoNegocio() ;
	public Converter getConverterEstadoSolicitud();
	public Converter getConverterFuenteNegocio() ;
	public Converter getConverterCausaldesi1() ;
	public Converter getConverterCausaldesi2();
	public Converter getConverterCausaldesi3() ;
	public Converter getConverterTipoRadicador();
	public Converter getConverterCausaldevol() ;
	public Converter getConverterAnalista1();
	public Converter getConverterAnalista2();
	public Converter getConverterRecoriesgo();
	public Converter getConverterDeterfinal();
	public Converter getConverterNiveldeter();
	public Converter getConverterCaunega1();
	public Converter getConverterCaunega2();
	public Converter getConverterCaunega3();
	public Converter getConverterMarcaseg();
	public Converter getconverterMoperStatus();
	public Converter getConverterAnalistaOper();


	
	
	
	

	/**
	 * @return
	 * @uml.property  name="listaPersona1"
	 */
	public List<MpaValores> getListaPersona1();
 
	public List<MpaValores> getListaTipoId();
 
	public List<MpaValores> getListaCiudad();
 
	public List<MpaValores> getTipoBien();
 
	public List<MpaValores> getMarcaAutomovil();
 
	public List<McomPersonas> getTipoProveedor();
 
	public List<MpaValores> getContComercial() ;
 
	public List<MpaValores> getLineaVehiculo() ;
 
	public List<MpaValores> getTipoVehiculo();
 
	public List<MpaValores> getClaseVehiculo();
	 
	public List<MpaValores> getTipoEjecutivo() ;
 
	public List<MpaValores> getPlanCredito();
 
	public List<MpaValores> getTipoNegocio();
 
	public List<MpaValores> getEstadoSolicitud();
 
	public List<MpaValores> getFuenteNegocio();
	 
	public List<MpaValores> getCausaldesi1();
 
	public List<MpaValores> getCausaldesi2();
 
	public List<MpaValores> getCausaldesi3();
 
	public List<MpaValores> getTipoRadicador();
 
	public List<MpaValores> getCausaldevol();
	public List<MpaValores> getAnalista1();
	public List<MpaValores> getAnalista2();
	public List<MpaValores> getRecoriesgo();
	public List<MpaValores> getDeterfinal();
	public List<MpaValores> getNiveldeter();
	public List<MpaValores> getCaunega1();
	public List<MpaValores> getCaunega2();
	public List<MpaValores> getCaunega3();
	public List<MpaValores> getMarcaseg();
	public List<MoperStatus> getListaMoperStatus();
	public List<MpaValores> getAnalistaOper();
	
	
	
	 
	public void destroy();

}