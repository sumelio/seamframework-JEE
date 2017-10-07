package com.leasing.popular.wf.generico.view.list;

import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.faces.convert.Converter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.security.Restrict;

import com.leasing.popular.wf.generico.view.converter.ConverterPersona;
import com.leasing.popular.wf.generico.view.converter.ConverterStatus;
import com.leasing.popular.wf.generico.view.converter.ConverterValor;
import com.leasing.popular.wf.table.negocio.persona.McomPersonas;
import com.leasing.popular.wf.table.negocio.persona.McomPersonasActionDAO;
import com.leasing.popular.wf.table.negocio.persona.McomPersonasDAO;
import com.leasing.popular.wf.table.parametros.status.MoperStatus;
import com.leasing.popular.wf.table.parametros.status.MoperStatusActionDAO;
import com.leasing.popular.wf.table.parametros.valores.MpaValores;
import com.leasing.popular.wf.table.parametros.valores.MpaValoresActionDAO;
import com.leasing.popular.wf.table.parametros.valores.MpaValoresDAO;



/**
 * @author       Usuario
 */
@Stateful
@Name("listasAction")
@Scope(ScopeType.SESSION)
@Restrict("#{identity.loggedIn}")
public class ListasAction implements Listas
{
	@PersistenceContext
	private EntityManager em;



	@In
	private MpaValoresActionDAO mpaValoresActionDAO;
	@In
	private McomPersonasActionDAO mcomPersonasActionDAO;

	@In
	private MoperStatusActionDAO moperStatusActionDAO;

	private static Long PARA_PERSONA_1       = new Long(1); 
	private static Long PARA_TIPO_ID         = new Long(5);
	private static Long PARA_CIUDAD          = new Long(6);
	private static Long  PARA_TIPOBIEN       = new Long(7);
	private static Long  PARA_MARCAAUTOMOVIL = new Long(8);
	
	//private static Long  PARA_TIPOPROVEEDOR  = new Long(9);
	private static Long  PARA_TIPOPROVEEDOR  = new Long(9);
	
	private static Long  PARA_CONTCOMERCIAL  = new Long(10);
	private static Long  PARA_LINEAVEHICULO  = new Long(11);
	private static Long  PARA_TIPOVEHICULO  = new Long(12);
	private static Long  PARA_CLASEVEHICULO  = new Long(13);
	private static Long  PARA_TIPOEJECUTIVO  = new Long(14);
	private static Long  PARA_PLANCREDITO    = new Long(15);
	private static Long  PARA_TIPONEGOCIO    = new Long(16);
	private static Long  PARA_ESTADOSOLICITUD= new Long(17);
	private static Long  PARA_FUENTENEGOCIO  = new Long(18);
	private static Long  PARA_CAUSALDESI1    = new Long(19);
	private static Long  PARA_CAUSALDESI2    = new Long(20);
	private static Long  PARA_CAUSALDESI3    = new Long(21);
	private static Long  PARA_TIPORADICADOR  = new Long(22);
	
	private static Long  PARA_CAUSALDEVOL= new Long(23);
	private static Long  PARA_ANALISTA1= new Long(24);
	private static Long  PARA_ANALISTA2= new Long(25);
	private static Long  PARA_RECORIESGO= new Long(26);
	private static Long  PARA_DETERFINAL= new Long(27);
	private static Long  PARA_NIVELDETER= new Long(28);
	private static Long  PARA_CAUNEGA1= new Long(29);
	private static Long  PARA_CAUNEGA2= new Long(30);
	private static Long  PARA_CAUNEGA3= new Long(31);
	private static Long  PARA_MARCASEG= new Long(32);
	private static Long  PARA_ANALISTA_OPER= new Long(33);
 


	/**
	 * @uml.property  name="listaPersona1"
	 */
	private List<MpaValores> listaPersona1 ;
	private List<MpaValores> listaPersona2 ;
	private List<MpaValores> listaPersona3 ;
	private List<MpaValores> listaPersona4 ;
	/**
	 * @uml.property  name="listaCiudad"
	 */
	private List<MpaValores> listaCiudad ;
    /**
	 * @uml.property  name="listaTipoId"
	 */
    private List<MpaValores> listaTipoId ;
    /**
	 * @uml.property  name="tipoBien"
	 */
    private List<MpaValores> TipoBien;
    /**
	 * @uml.property  name="marcaAutomovil"
	 */
    private List<MpaValores> MarcaAutomovil;
    /**
	 * @uml.property  name="tipoProveedor"
	 */
    private List<McomPersonas> TipoProveedor;
    /**
	 * @uml.property  name="contComercial"
	 */
    private List<MpaValores> ContComercial;
    /**
	 * @uml.property  name="lineaVehiculo"
	 */
    private List<MpaValores> LineaVehiculo;
    /**
	 * @uml.property  name="tipoVehiculo"
	 */
    private List<MpaValores> TipoVehiculo;
    /**
	 * @uml.property  name="claseVehiculo"
	 */
    private List<MpaValores> ClaseVehiculo;
    /**
	 * @uml.property  name="tipoEjecutivo"
	 */
    private List<MpaValores> TipoEjecutivo;
    /**
	 * @uml.property  name="planCredito"
	 */
    private List<MpaValores> PlanCredito;
    /**
	 * @uml.property  name="tipoNegocio"
	 */
    private List<MpaValores> TipoNegocio;
    /**
	 * @uml.property  name="estadoSolicitud"
	 */
    private List<MpaValores> EstadoSolicitud;
    /**
	 * @uml.property  name="fuenteNegocio"
	 */
    private List<MpaValores> FuenteNegocio;
    /**
	 * @uml.property  name="causaldesi1"
	 */
    private List<MpaValores> Causaldesi1;
    /**
	 * @uml.property  name="causaldesi2"
	 */
    private List<MpaValores> Causaldesi2;
    /**
	 * @uml.property  name="causaldesi3"
	 */
    private List<MpaValores> Causaldesi3;
    /**
	 * @uml.property  name="tipoRadicador"
	 */
    private List<MpaValores> TipoRadicador;

  
	
   
	public Converter getConverterPersona1() {	   return new ConverterValor(mpaValoresActionDAO.findParam( PARA_PERSONA_1));} 
	public Converter getConverterTipoId() {        return new ConverterValor(mpaValoresActionDAO.findParam( PARA_TIPO_ID));}
	public Converter getConverterCiudad() {        return new ConverterValor(mpaValoresActionDAO.findParam( PARA_CIUDAD));}
	public Converter getConverterTipoBien() {      return new ConverterValor(mpaValoresActionDAO.findParam( PARA_TIPOBIEN));}
	public Converter getConverterMarcaAutomovil() {return new ConverterValor(mpaValoresActionDAO.findParam( PARA_MARCAAUTOMOVIL));}
	public Converter getConverterTipoProveedor() { return new ConverterPersona(mcomPersonasActionDAO.findProveedor( PARA_TIPOPROVEEDOR));}
	public Converter getConverterContComercial() { return new ConverterValor(mpaValoresActionDAO.findParam( PARA_CONTCOMERCIAL));}
	public Converter getConverterLineaVehiculo() { return new ConverterValor(mpaValoresActionDAO.findParam( PARA_LINEAVEHICULO));}
	public Converter getConverterTipoVehiculo() { return new ConverterValor(mpaValoresActionDAO.findParam( PARA_TIPOVEHICULO));}
	public Converter getConverterClaseVehiculo() { return new ConverterValor(mpaValoresActionDAO.findParam( PARA_CLASEVEHICULO));}
	public Converter getConverterTipoEjecutivo() { return new ConverterValor(mpaValoresActionDAO.findParam( PARA_TIPOEJECUTIVO));}
	public Converter getConverterPlanCredito() {   return new ConverterValor(mpaValoresActionDAO.findParam( PARA_PLANCREDITO));}
	public Converter getConverterTipoNegocio() {   return new ConverterValor(mpaValoresActionDAO.findParam( PARA_TIPONEGOCIO));}
	public Converter getConverterEstadoSolicitud(){return new ConverterValor(mpaValoresActionDAO.findParam( PARA_ESTADOSOLICITUD));}
	public Converter getConverterFuenteNegocio() { return new ConverterValor(mpaValoresActionDAO.findParam( PARA_FUENTENEGOCIO));}
	public Converter getConverterCausaldesi1() {   return new ConverterValor(mpaValoresActionDAO.findParam( PARA_CAUSALDESI1));}
	public Converter getConverterCausaldesi2() {   return new ConverterValor(mpaValoresActionDAO.findParam( PARA_CAUSALDESI2));}
	public Converter getConverterCausaldesi3() {   return new ConverterValor(mpaValoresActionDAO.findParam( PARA_CAUSALDESI3));}
	public Converter getConverterTipoRadicador() { return new ConverterValor(mpaValoresActionDAO.findParam( PARA_TIPORADICADOR));}
	
	public Converter getConverterCausaldevol() {	   return new ConverterValor(mpaValoresActionDAO.findParam( PARA_CAUSALDEVOL));}
	public Converter getConverterAnalista1() {	   return new ConverterValor(mpaValoresActionDAO.findParam( PARA_ANALISTA1));}
	public Converter getConverterAnalista2() {	   return new ConverterValor(mpaValoresActionDAO.findParam( PARA_ANALISTA2));}
	public Converter getConverterRecoriesgo() {	   return new ConverterValor(mpaValoresActionDAO.findParam( PARA_RECORIESGO));}
	public Converter getConverterDeterfinal() {	   return new ConverterValor(mpaValoresActionDAO.findParam( PARA_DETERFINAL));}
	public Converter getConverterNiveldeter() {	   return new ConverterValor(mpaValoresActionDAO.findParam( PARA_NIVELDETER));}
	public Converter getConverterCaunega1() {	   return new ConverterValor(mpaValoresActionDAO.findParam( PARA_CAUNEGA1));}
	public Converter getConverterCaunega2() {	   return new ConverterValor(mpaValoresActionDAO.findParam( PARA_CAUNEGA2));}
	public Converter getConverterCaunega3() {	   return new ConverterValor(mpaValoresActionDAO.findParam( PARA_CAUNEGA3));}
	public Converter getConverterMarcaseg() {	   return new ConverterValor(mpaValoresActionDAO.findParam( PARA_MARCASEG));}
	public Converter getConverterAnalistaOper() {	   return new ConverterValor(mpaValoresActionDAO.findParam( PARA_ANALISTA_OPER));}
	public Converter getconverterMoperStatus() {	   return new ConverterStatus(moperStatusActionDAO.findAll());}

	
	
	
 
	
	

	/**
	 * @return
	 * @uml.property  name="listaPersona1"
	 */
	public List<MpaValores> getListaPersona1() {return  mpaValoresActionDAO.findParam( PARA_PERSONA_1);	} 
	/**
	 * @return
	 * @uml.property  name="listaTipoId"
	 */
	public List<MpaValores> getListaTipoId() {	return  mpaValoresActionDAO.findParam( PARA_TIPO_ID);}
	/**
	 * @return
	 * @uml.property  name="listaCiudad"
	 */
	public List<MpaValores> getListaCiudad() {  return mpaValoresActionDAO.findParam( PARA_CIUDAD);}
	/**
	 * @return
	 * @uml.property  name="tipoBien"
	 */
	public List<MpaValores> getTipoBien() {     return  mpaValoresActionDAO.findParam( PARA_TIPOBIEN);}
	/**
	 * @return
	 * @uml.property  name="marcaAutomovil"
	 */
	public List<MpaValores> getMarcaAutomovil(){return  mpaValoresActionDAO.findParam( PARA_MARCAAUTOMOVIL);}
	/**
	 * @return
	 * @uml.property  name="tipoProveedor"
	 */
	public List<McomPersonas> getTipoProveedor() {return  mcomPersonasActionDAO.findProveedor( PARA_TIPOPROVEEDOR);}
	/**
	 * @return
	 * @uml.property  name="contComercial"
	 */
	public List<MpaValores> getContComercial() {return  mpaValoresActionDAO.findParam( PARA_CONTCOMERCIAL);}
	/**
	 * @return
	 * @uml.property  name="lineaVehiculo"
	 */
	public List<MpaValores> getLineaVehiculo() {return  mpaValoresActionDAO.findParam( PARA_LINEAVEHICULO);}
	/**
	 * @return
	 * @uml.property  name="tipoVehiculo"
	 */
	public List<MpaValores> getTipoVehiculo() {return  mpaValoresActionDAO.findParam( PARA_TIPOVEHICULO);}
	/**
	 * @return
	 * @uml.property  name="claseVehiculo"
	 */
	public List<MpaValores> getClaseVehiculo() {return  mpaValoresActionDAO.findParam( PARA_CLASEVEHICULO);}
	/**
	 * @return
	 * @uml.property  name="tipoEjecutivo"
	 */
	public List<MpaValores> getTipoEjecutivo() {return  mpaValoresActionDAO.findParam( PARA_TIPOEJECUTIVO);}
	/**
	 * @return
	 * @uml.property  name="planCredito"
	 */
	public List<MpaValores> getPlanCredito() {  return  mpaValoresActionDAO.findParam( PARA_PLANCREDITO);}
	/**
	 * @return
	 * @uml.property  name="tipoNegocio"
	 */
	public List<MpaValores> getTipoNegocio() {  return  mpaValoresActionDAO.findParam( PARA_TIPONEGOCIO);}
	/**
	 * @return
	 * @uml.property  name="fuenteNegocio"
	 */
	public List<MpaValores> getFuenteNegocio() {return  mpaValoresActionDAO.findParam( PARA_FUENTENEGOCIO);}
	/**
	 * @return
	 * @uml.property  name="estadoSolicitud"
	 */
	public List<MpaValores> getEstadoSolicitud(){return  mpaValoresActionDAO.findParam( PARA_ESTADOSOLICITUD);}
	/**
	 * @return
	 * @uml.property  name="causaldesi1"
	 */
	public List<MpaValores> getCausaldesi1() {  return  mpaValoresActionDAO.findParam( PARA_CAUSALDESI1);}
	/**
	 * @return
	 * @uml.property  name="causaldesi2"
	 */
	public List<MpaValores> getCausaldesi2() {  return  mpaValoresActionDAO.findParam( PARA_CAUSALDESI2);}
	/**
	 * @return
	 * @uml.property  name="causaldesi3"
	 */
	public List<MpaValores> getCausaldesi3() {  return  mpaValoresActionDAO.findParam( PARA_CAUSALDESI3);}
	/**
	 * @return
	 * @uml.property  name="tipoRadicador"
	 */
	public List<MpaValores> getTipoRadicador(){ return  mpaValoresActionDAO.findParam( PARA_TIPORADICADOR);}
	
	
	public List<MpaValores> getCausaldevol(){ return  mpaValoresActionDAO.findParam( PARA_CAUSALDEVOL);}
	public List<MpaValores> getAnalista1(){ return  mpaValoresActionDAO.findParam( PARA_ANALISTA1);}
	public List<MpaValores> getAnalista2(){ return  mpaValoresActionDAO.findParam( PARA_ANALISTA2);}
	public List<MpaValores> getRecoriesgo(){ return  mpaValoresActionDAO.findParam( PARA_RECORIESGO);}
	public List<MpaValores> getDeterfinal(){ return  mpaValoresActionDAO.findParam( PARA_DETERFINAL);}
	public List<MpaValores> getNiveldeter(){ return  mpaValoresActionDAO.findParam( PARA_NIVELDETER);}
	public List<MpaValores> getCaunega1(){ return  mpaValoresActionDAO.findParam( PARA_CAUNEGA1);}
	public List<MpaValores> getCaunega2(){ return  mpaValoresActionDAO.findParam( PARA_CAUNEGA2);}
	public List<MpaValores> getCaunega3(){ return  mpaValoresActionDAO.findParam( PARA_CAUNEGA3);}
	public List<MpaValores> getMarcaseg(){ return  mpaValoresActionDAO.findParam( PARA_MARCASEG);}
	public List<MpaValores> getAnalistaOper(){ return  mpaValoresActionDAO.findParam( PARA_ANALISTA_OPER);}
	
	public List<MoperStatus> getListaMoperStatus(){ return  moperStatusActionDAO.findAll();};
	
	
	@Remove
	public void destroy() {}
}
