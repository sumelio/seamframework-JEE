package com.leasing.popular.wf.modulos.negocio.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.faces.FacesMessages;

 

import com.leasing.popular.wf.table.negocio.negocio.McomNegocio;
import com.leasing.popular.wf.table.negocio.negocio.McomNegocioActionDAO;
import com.leasing.popular.wf.table.negocio.persona.McomPersonas;
import com.leasing.popular.wf.table.negocio.persona.McomPersonasActionDAO;
import com.leasing.popular.wf.table.negocio.persona.McomPersonasId;
import com.leasing.popular.wf.table.parametros.valores.MpaValores;
import com.leasing.popular.wf.table.parametros.valores.MpaValoresActionDAO;




@Stateful
@Name("negRegistroAction")
@Scope(ScopeType.SESSION) 
@Restrict("#{identity.loggedIn}")
public class NegRegistroAction implements NegRegistro
{
	@PersistenceContext
	private EntityManager em;

	@In
	private   FacesMessages facesMessages;

	@Out(required=false, scope=ScopeType.SESSION)
	@In(required=false, scope=ScopeType.SESSION)
	private McomNegocio mcomNegocioReg = new McomNegocio();
	
	private McomNegocio mcomNegocioRegAntes = new McomNegocio();


	@Out(required=false)
	private String verify;

	@In
	private McomNegocioActionDAO   mcomNegocioActionDAO;
	@In
	private McomPersonasActionDAO mcomPersonasActionDAO;
	
	@In
	private MpaValoresActionDAO mpaValoresActionDAO;

	private boolean registered;
	private String resultPersona = " ";
	private boolean isEdicion = false;




	private boolean showCausal1 = false;
	private boolean showCausal2 = false;
	private boolean showCausal3 = false;
	
	private boolean disableVehiculo = false;


	


	private List<MpaValores> marcaAutomovil = new ArrayList<MpaValores>();






	public String siguiente(){

		if(mcomNegocioReg.getMcomPersonasByFkClientePersonas().getId().getPerIdentificacion()!= null && mcomNegocioReg.getMcomPersonasByFkClientePersonas().getId().getPerIdentificacion().trim().length() > 0 ){


			mcomNegocioReg.getMcomPersonasByFkClientePersonas().getId().setPerParaTipoId(mcomNegocioReg.getMcomPersonasByFkClientePersonas().getMpaValoresByFkPersonasValoresTipoid().getId().getParCodigo());
			mcomNegocioReg.getMcomPersonasByFkClientePersonas().getId().setPerTipoIdentificacion(mcomNegocioReg.getMcomPersonasByFkClientePersonas().getMpaValoresByFkPersonasValoresTipoid().getId().getVarCodigo());
			return "OK";
		}else{
			System.out.println("siguiente en estudio no");
			facesMessages.add("Por favor registre el Id de la persona.");

			return "NO";
		}

	}



	public void guardar(){

		registered = true;

		if(!mcomNegocioReg.getMcomPersonasByFkClientePersonas().getId().equals(mcomNegocioReg.getMcomPersonasByFkProveedorPersonas().getId())){

			if(mcomPersonasActionDAO.findById( mcomNegocioReg.getMcomPersonasByFkClientePersonas().getId()) != null){

				if(mcomNegocioActionDAO.findById( mcomNegocioReg.getNegId()) == null){

					mcomNegocioReg.setMcomPersonasByFkClientePersonas(mcomPersonasActionDAO.findById( mcomNegocioReg.getMcomPersonasByFkClientePersonas().getId()));
					mcomNegocioActionDAO.persist(mcomNegocioReg);	
					isEdicion = true;
					facesMessages.add("Con éxito registrado el negocio \""+mcomNegocioReg.getNegId() + "\"");
				}else{

					mcomNegocioReg.setMcomPersonasByFkClientePersonas(mcomPersonasActionDAO.findById( mcomNegocioReg.getMcomPersonasByFkClientePersonas().getId()));
					mcomNegocioReg = mcomNegocioActionDAO.merge(mcomNegocioReg);	
					isEdicion = true;
					facesMessages.add("Con éxito actualizado el negocio \""+mcomNegocioReg.getNegId() + "\"");
				}

			}else{
				facesMessages.add( "No existe un cliente con este tipo y número de identifiación.");
				resultPersona=" "; 
			}

		}else {
			facesMessages.add( "El cliente y proveedor no pueden ser la misma persona");
		}
	}



	public void nuevo(){
		mcomNegocioReg = new McomNegocio();
		mcomNegocioReg.setNegId(mcomNegocioActionDAO.genereSecuencia( ) );
		mcomNegocioReg.setMcomPersonasByFkClientePersonas(new McomPersonas(new McomPersonasId()));
		isEdicion = false;
		ajaxDisableVehiculo();
		mcomNegocioRegAntes = new McomNegocio();
		resultPersona = " ";
	}


	public void seleccionNegocio(McomNegocio mcomNegocio_){
		isEdicion = true; 
		this.mcomNegocioReg =  mcomNegocioActionDAO.findById(  mcomNegocio_.getNegId());
		mcomNegocioRegAntes =  mcomNegocioActionDAO.findById(  mcomNegocio_.getNegId());
		ajaxDisableVehiculo(); 
		//findPersona();

	}

	public void findPersona(){
		McomPersonas mcomPersonas = null;
		if(mcomNegocioReg.getMcomPersonasByFkClientePersonas() != null){
			McomPersonasId mcomPersonasId = new McomPersonasId();
			mcomPersonasId.setPerIdentificacion( mcomNegocioReg.getMcomPersonasByFkClientePersonas().getId().getPerIdentificacion());
			mcomPersonasId.setPerParaTipoId(mcomNegocioReg.getMcomPersonasByFkClientePersonas().getMpaValoresByFkPersonasValoresTipoid().getId().getParCodigo());
			mcomPersonasId.setPerTipoIdentificacion(mcomNegocioReg.getMcomPersonasByFkClientePersonas().getMpaValoresByFkPersonasValoresTipoid().getId().getVarCodigo());
			mcomPersonas = mcomPersonasActionDAO.findById( mcomPersonasId);

		}
		if(mcomPersonas != null){
			mcomNegocioReg.setMcomPersonasByFkClientePersonas(mcomPersonas);
		}else{
			mcomNegocioReg.setMcomPersonasByFkClientePersonas(new McomPersonas(new McomPersonasId()));
			facesMessages.instance().addToControl("mcomPersonas", "No existe una persona con este número de identifiación");
			resultPersona=" "; 
		}

	}

	public String resultPersona(){
		return resultPersona;
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



	public McomNegocio getMcomNegocioReg() {
		return mcomNegocioReg;
	}


	public void setMcomNegocioReg(McomNegocio mcomNegocioReg) {
		this.mcomNegocioReg = mcomNegocioReg;
	}


	public boolean isEdicion() {
		return isEdicion;
	}



	public boolean isShowCausal1() {
		if( mcomNegocioRegAntes.getMpaValoresByFkNegocioValoresCausaldesi1() != null && mcomNegocioReg.getMpaValoresByFkNegocioValoresCausaldesi1().getId().getParCodigo() > 0 ){
			showCausal1 = true;
		}else{
			showCausal1 = false;
		}

		return showCausal1;
	}





// Debe estar deshabilitado el 1 
	public boolean isShowCausal2() {
		if( !(mcomNegocioRegAntes.getMpaValoresByFkNegocioValoresCausaldesi1() != null &&	mcomNegocioReg.getMpaValoresByFkNegocioValoresCausaldesi1().getId().getParCodigo() > 0)  ||
			  mcomNegocioRegAntes.getMpaValoresByFkNegocioValoresCuasaldesi2() != null &&	mcomNegocioReg.getMpaValoresByFkNegocioValoresCuasaldesi2().getId().getParCodigo() > 0 ){
			showCausal2 = true;
		}else{
			showCausal2 = false;
		}

		return showCausal2;
	}





	public boolean isShowCausal3() {
		if( !(mcomNegocioRegAntes.getMpaValoresByFkNegocioValoresCausaldesi1() != null &&	mcomNegocioReg.getMpaValoresByFkNegocioValoresCausaldesi1().getId().getParCodigo() > 0) ||
				!(mcomNegocioRegAntes.getMpaValoresByFkNegocioValoresCuasaldesi2() != null &&	mcomNegocioReg.getMpaValoresByFkNegocioValoresCuasaldesi2().getId().getParCodigo() > 0)  ||
				 mcomNegocioRegAntes.getMpaValoresByFkNegocioValoresCausaldesi3() != null &&	mcomNegocioReg.getMpaValoresByFkNegocioValoresCausaldesi3().getId().getParCodigo() > 0 ){
			showCausal3 = true;
		}else{
			showCausal3 = false;
		}
	 
		return showCausal3;
	}



	public List<MpaValores> getMarcaAutomovil() {
		if(mcomNegocioReg.getMpaValoresByFkNegocioValoresTipobien() != null){
		//	System.out.println("getMarcaAutomovil " + mcomNegocioReg.getMpaValoresByFkNegocioValoresTipobien().getVarNombre()  );
		//	marcaAutomovil = mpaValoresActionDAO.findParamPadre(new Long(8), mcomNegocioReg.getMpaValoresByFkNegocioValoresTipobien().getId().getVarCodigo());
			// 1 es VEHICULO
			disableVehiculo = !(mcomNegocioReg.getMpaValoresByFkNegocioValoresTipobien().getId().getVarCodigo() == 1);
		}
		return marcaAutomovil;
	}
	public void ajaxDisableVehiculo() {
		if(mcomNegocioReg.getMpaValoresByFkNegocioValoresTipobien() != null){
	   disableVehiculo = !(mcomNegocioReg.getMpaValoresByFkNegocioValoresTipobien().getId().getVarCodigo() == 1);
		}
	 
	}


	public void setMarcaAutomovil(List<MpaValores> marcaAutomovil) {
		this.marcaAutomovil = marcaAutomovil;
	}


	public boolean isDisableVehiculo() {
		return disableVehiculo;
	}



	public void setDisableVehiculo(boolean disableVehiculo) {
		this.disableVehiculo = disableVehiculo;
	}



	@Remove
	public void destroy() {}
}
