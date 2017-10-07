package com.leasing.popular.wf.modulos.operaciones.operaciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.query.JRJpaQueryExecuterFactory;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.faces.FacesMessages;
 
import com.leasing.popular.wf.table.operaciones.observaciones.MoperObservaciones;
import com.leasing.popular.wf.table.operaciones.observaciones.MoperObservacionesActionDAO;
import com.leasing.popular.wf.table.operaciones.observaciones.MoperObservacionesId;
import com.leasing.popular.wf.table.operaciones.operaciones.MoperOperaciones;
import com.leasing.popular.wf.table.operaciones.operaciones.MoperOperacionesActionDAO;




/**
 * @author       Usuario
 */
@Stateful
@Name("operRegistroAction")
@Scope(ScopeType.SESSION)
@Restrict("#{identity.loggedIn}")
public class OperRegistroAction implements OperRegistro
{
	@PersistenceContext
	private EntityManager em;

	@In
	private   FacesMessages facesMessages;

	@DataModel
	private List<MoperObservaciones>listaObservaciones;

	@Out(required=false)
	private String verify;

    @In
	private MoperOperacionesActionDAO moperOperacionesActionDAO;

	@In
	private MoperObservacionesActionDAO moperObservacionesActionDAO;
	
	private static  String linea =" ::::::::::::::::::::::::::::::::::::::::::: ";
	
	private boolean registered;
	private long statusOperAntes = 0;
	private MoperOperaciones moperOperacionesReg = new MoperOperaciones();
	private MoperObservaciones moperObservacionesReg = new MoperObservaciones();
	private String observ = "";

	
	public void guardar(){
		String actTime = getFecha();
		long statusOperNuevo =  moperOperacionesReg.getMoperStatus().getStaId().longValue();
		// NO SE PUEDE CAMBIAR A UN ESTADO ANTERIOR
		if(!(statusOperAntes  <= statusOperNuevo ) ){
			this.observ = "";
			facesMessages.instance().addToControl("moperStatus", "Status no es valido");
			facesMessages.add("No es posible cambiar la operación a un estado anterior.");
			return;
		}
		
		// NO SE PUEDE SALTA UN ESTADO
		if(!(statusOperAntes == statusOperNuevo  ||  statusOperAntes + 1 == statusOperNuevo ) ){
			this.observ = "";
			
			facesMessages.instance().addToControl("moperStatus", "Status no es valido");  
			facesMessages.add("No es posible cambiar la operación a este estado.");
			return;
		}

		
		
		moperOperacionesReg = moperOperacionesActionDAO.merge( moperOperacionesReg);	
		MoperObservacionesId id = getObservacionId(moperOperacionesReg);
		moperObservacionesReg = getObservacion(moperOperacionesReg, id);

     
		String observ_ = moperObservacionesReg.getObsObservaciones() + ((observ==null || observ=="")?"":"\n \n" + linea + actTime  + " "+ linea + "\n \n" + observ );				
		moperObservacionesReg.setObsObservaciones(observ_);
		
		
		if(observ_ != null && observ_.trim().length() > 0){ 
			if(   moperObservacionesActionDAO.findById(id) != null ){
				moperObservacionesReg = moperObservacionesActionDAO.merge(moperObservacionesReg);
			}else{
				moperObservacionesReg.setOperFecha(new Date());
				moperObservacionesActionDAO.persist(moperObservacionesReg);
				
			}
		}
		observ ="";
		statusOperAntes =  moperOperacionesReg.getMoperStatus().getStaId().longValue(); 
		facesMessages.add("Con éxito actualizada la información de la operación con id \""+moperOperacionesReg.getOperId() + "\"");

	}


	public void seleccionMoperOperaciones(MoperOperaciones moperOperacionesReg_){
		this.moperOperacionesReg = moperOperacionesReg_;
		observ = "";
		MoperObservacionesId m = new MoperObservacionesId();
		m.setOperId(moperOperacionesReg.getOperId() );
		m.setStaId(moperOperacionesReg.getMoperStatus().getStaId() );
		moperObservacionesReg = moperObservacionesActionDAO.findById(m);
		statusOperAntes =  moperOperacionesReg.getMoperStatus().getStaId().longValue(); 
		System.out.println("statusOperAntes " + statusOperAntes);
		this.listaObservaciones = getListaObservaciones();
		//this.moperObservaciones  = moperObservacionesActionDAO.findById(moperOperacionesReg.getOperId());
	}


	public void invalid(){
		facesMessages.add("Por favor intente otra vez");
	}

	public boolean isRegistered(){
		return registered;
	}

	public String getVerify(){
		return verify;
	}

	public void setVerify(String verify){
		this.verify = verify;
	}




	public MoperOperaciones getMoperOperacionesReg() {
		return moperOperacionesReg;
	}


	public void setMoperOperacionesReg(MoperOperaciones moperOperacionesReg) {
		this.moperOperacionesReg = moperOperacionesReg;
	}




	public MoperObservaciones getMoperObservacionesReg() {

		return moperObservacionesReg;
	}


	public void setMoperObservacionesReg(MoperObservaciones moperObservacionesReg) {
		this.moperObservacionesReg = moperObservacionesReg;
	}


	public String getObserv() {
		return observ;
	}


	public void setObserv(String observ) {
		this.observ = observ;
	}

	public List<MoperObservaciones> getListaObservaciones() {
		MoperObservacionesId m = new MoperObservacionesId();
		m.setOperId(moperOperacionesReg.getOperId() );
		m.setStaId(moperOperacionesReg.getMoperStatus().getStaId() );
		moperObservacionesReg = moperObservacionesActionDAO.findById(m);
		if(moperObservacionesReg == null){
			moperObservacionesReg = new MoperObservaciones();
			moperObservacionesReg.setId(m);
			moperObservacionesReg.setObsObservaciones("");
		}
		listaObservaciones = moperObservacionesActionDAO.findAll(m);

		return listaObservaciones;
	}


	public void setListaObservaciones(List<MoperObservaciones> listaObservaciones) {
		this.listaObservaciones = listaObservaciones;
	}

	private String getFecha(){
		Calendar calendar = Calendar.getInstance();
		return	(new SimpleDateFormat ( "yyyy-MM-dd hh:mm:ss" )).format ( calendar.getTime (  )  ) ; 

	}

	/**
	 * @param moperOperacionesReg_
	 * @return
	 */
	private MoperObservaciones getObservacion(MoperOperaciones moperOperacionesReg_, MoperObservacionesId m){
		MoperObservaciones moperObservaciones_ = null;
		moperObservaciones_ = moperObservacionesActionDAO.findById(m) ;
		if(moperObservaciones_ == null){
			moperObservaciones_ = new MoperObservaciones();
			moperObservaciones_.setId(m);
			moperObservaciones_.setMoperStatus(moperOperacionesReg_.getMoperStatus() );
			moperObservaciones_.setObsObservaciones("");
		}
		
		return moperObservaciones_;
	}
	
	/**
	 * @param moperOperacionesReg_
	 * @return
	 */
	private MoperObservacionesId getObservacionId(MoperOperaciones moperOperacionesReg_  ){
		 
		MoperObservacionesId m = new MoperObservacionesId();
		m.setOperId(moperOperacionesReg_.getOperId() );
		m.setStaId(moperOperacionesReg_.getMoperStatus().getStaId() );
		return m;
	}

	
	public void generarFichero(){
		 
		String documentoSeguirdad = "C:/disco_D/reporte.jasper";
		String ficheros = "C:/disco_D/reporte.jasper";
		long inicio = System.currentTimeMillis();
		String destFileNamePdf = "C:/disco_D/reporte/ruta3.pdf";
 
		Map parameters = new HashMap();
		parameters.put("NIF","ss");
		parameters.put("ficheros", ficheros);
 
		try {
			//Class.forName( "oracle.jdbc.driver.OracleDriver" );
			System.out.println("ficheros " + ficheros);
			Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","HLPDSK","HLPDSK");
			System.out.println("ficheros " + ficheros);
			parameters.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER,em);
			JasperPrint jasperPrint = JasperFillManager.fillReport(documentoSeguirdad,  parameters);
			System.out.println("ficheros " + ficheros);
			JasperExportManager.exportReportToPdfFile(jasperPrint, destFileNamePdf);
			System.out.println("ficheros " + ficheros);
 
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		catch (JRException e) {
			e.printStackTrace();
		}  
	}
	@Remove
	public void destroy() {}
}
