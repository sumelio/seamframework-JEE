package com.leasing.popular.wf.modulos.negocio.estudio;

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

import com.leasing.popular.wf.table.negocio.estudio.McomEstudio;
import com.leasing.popular.wf.table.negocio.estudio.McomEstudioActionDAO;
import com.leasing.popular.wf.table.negocio.negocio.McomNegocio;
import com.leasing.popular.wf.table.negocio.negocio.McomNegocioActionDAO;
import com.leasing.popular.wf.table.negocio.persona.McomPersonas;
import com.leasing.popular.wf.table.negocio.persona.McomPersonasId;



/**
 * @author       Usuario
 */
@Stateful
@Name("estRegistroAction")
@Scope(ScopeType.SESSION)
@Restrict("#{identity.loggedIn}")
public class EstRegistroAction implements EstRegistro
{
	@PersistenceContext
	private EntityManager em;

	@In
	private   FacesMessages facesMessages;

    /*@Out(required=false, scope=ScopeType.SESSION)
	@In(required=false, scope=ScopeType.SESSION)*/
	private McomEstudio mcomEstudioReg;

	
	@Out(required=false)
	private String verify;

	
	@In
	private McomEstudioActionDAO mcomEstudioActionDAO; 
	
	@In
	private McomNegocioActionDAO mcomNegocioActionDAO;
	
	
	private boolean registered;
	private boolean enviarOpericiones = false;


       

	private boolean isEdicion = false;
	
	public void nuevo(){
		mcomEstudioReg = new McomEstudio();
		mcomEstudioReg.setMcomPersonas(new McomPersonas(new McomPersonasId() ));
		isEdicion = false;
	}

	public void seleccionEstudio(McomEstudio mcomEstudio_){
		isEdicion = true;
		this.mcomEstudioReg =  mcomEstudioActionDAO.findById( mcomEstudio_.getEstId());
		enviarOpericiones = (this.mcomEstudioReg.getEstDisabled() != null &&  (this.mcomEstudioReg.getEstDisabled().longValue() ==1) );
	 }


	public String siguiente(){
		System.out.println("siguiente en estudio");
		if(mcomEstudioReg.getMcomPersonas().getId().getPerIdentificacion()!= null && mcomEstudioReg.getMcomPersonas().getId().getPerIdentificacion().trim().length() > 0 ){

			System.out.println("siguiente en estudio si ");
			mcomEstudioReg.getMcomPersonas().getId().setPerParaTipoId(mcomEstudioReg.getMcomPersonas().getMpaValoresByFkPersonasValoresTipoid().getId().getParCodigo());
			mcomEstudioReg.getMcomPersonas().getId().setPerTipoIdentificacion(mcomEstudioReg.getMcomPersonas().getMpaValoresByFkPersonasValoresTipoid().getId().getVarCodigo());
			return "OK";
		}else{

			System.out.println("siguiente en estudio no");
			facesMessages.add("Por favor registre el Id del negocio");
			return "NO";

		}
	}

	public void guardar(){
		System.out.println("cambio -----------------");
		registered = true;
		 this.mcomEstudioReg.setEstDisabled(new Long(enviarOpericiones?1:0));
		if(mcomEstudioActionDAO.findById( mcomEstudioReg.getEstId()) == null){
			mcomEstudioReg.setEstId(mcomEstudioReg.getMcomNegocio().getNegId());
			//mcomEstudioReg.setMcomNegocio(mcomEstudioReg.getMcomNegocio());
			mcomEstudioActionDAO.persist(mcomEstudioReg);	
			isEdicion = true;
			facesMessages.add("Con éxito registrado el estudio \""+mcomEstudioReg.getEstId() + "\"");
		}else{
			mcomEstudioReg = mcomEstudioActionDAO.merge(mcomEstudioReg);	
			isEdicion = true;
			facesMessages.add("Con éxito actualizado el estudio \""+mcomEstudioReg.getEstId() + "\"");
		}
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
//
//
//	public void findPersona(){
//		if(mcomEstudioReg.getMcomPersonas().getId().getPerIdentificacion() != null && mcomEstudioReg.getMcomPersonas().getMpaValoresByFkPersonasValoresTipoid().getId().getVarCodigo() != null){
//			Query query  = em.createQuery("select per.perNombres||' '||per.perApellidos from McomPersonas per  where  (:id) = (per.id.perIdentificacion) and per.id.perTipoIdentificacion = :tipId")
//			.setParameter("id", mcomEstudioReg.getMcomPersonas().getId().getPerIdentificacion())
//			.setParameter("tipId", mcomEstudioReg.getMcomPersonas().getMpaValoresByFkPersonasValoresTipoid().getId().getVarCodigo());
//
//			if(query.getResultList() != null && query.getResultList().size() > 0){
//				resultPersona = (String)query.getResultList().get(0);
//			}else{
//				facesMessages.instance().addToControl("mcomPersonas", "No existe una persona con este número de identifiación");
//				resultPersona=" "; 
//			}
//		}
//		
//		 
//
//	}
	
	public void findNegocioId(){
		if(mcomEstudioReg.getEstId() != null){
			McomNegocio  mcomNegocio = mcomNegocioActionDAO.findById(mcomEstudioReg.getEstId());
		

			if(mcomNegocio != null){
				if( mcomEstudioActionDAO.findById(mcomEstudioReg.getEstId()) == null){
					mcomEstudioReg.setMcomPersonas(mcomNegocio.getMcomPersonasByFkClientePersonas());
					mcomEstudioReg.setMcomNegocio(mcomNegocio); 
				}else{
					mcomEstudioReg.setMcomPersonas(new McomPersonas());
					mcomEstudioReg.setMcomNegocio(new McomNegocio()); 
					facesMessages.instance().addToControl("mcomfindNegocio", "Existe un estudio para este Negiocio con Id "+ mcomEstudioReg.getEstId());
					 
				}

			}else{
				mcomEstudioReg.setMcomPersonas(new McomPersonas());
				mcomEstudioReg.setMcomNegocio(new McomNegocio()); 
				facesMessages.instance().addToControl("mcomfindNegocio", "No existe un negocio con este id.");
				 
			}
		}else{
			mcomEstudioReg.setMcomPersonas(new McomPersonas());
			mcomEstudioReg.setMcomNegocio(new McomNegocio()); 
			facesMessages.instance().addToControl("mcomfindNegocio", "No existe un negocio con este id.");
			 
		}
		
		 

	}
	
	




 
 
	public boolean isEdicion() {
		return isEdicion;
	}

 
	public McomEstudio getMcomEstudioReg() {
		return mcomEstudioReg;
	}


    public void setMcomEstudioReg(McomEstudio mcomEstudioReg) {
		this.mcomEstudioReg = mcomEstudioReg;
	}

	public boolean isEnviarOpericiones() {
		return enviarOpericiones;
	}

	public void setEnviarOpericiones(boolean enviarOpericiones) {
		this.enviarOpericiones = enviarOpericiones;
	}

	@Remove
	public void destroy() {}
}
