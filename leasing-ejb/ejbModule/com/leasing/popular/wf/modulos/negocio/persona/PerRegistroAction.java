package com.leasing.popular.wf.modulos.negocio.persona;

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

import com.leasing.popular.wf.table.negocio.persona.McomPersonas;
import com.leasing.popular.wf.table.negocio.persona.McomPersonasActionDAO;
import com.leasing.popular.wf.table.negocio.persona.McomPersonasDAO;
import com.leasing.popular.wf.table.negocio.persona.McomPersonasId;
import com.leasing.popular.wf.table.parametros.valores.MpaValoresActionDAO;
import com.leasing.popular.wf.table.parametros.valores.MpaValoresDAO;



/**
 * @author       Usuario
 */
@Stateful
@Name("perRegistroAction")
@Scope(ScopeType.SESSION)
@Restrict("#{identity.loggedIn}")
public class PerRegistroAction implements PerRegistro
{
	@PersistenceContext
	private EntityManager em;

	@In
	private   FacesMessages facesMessages;

	/**
	 * @uml.property  name="mcomPersonasReg"
	 * @uml.associationEnd  
	 */
	@Out(required=false, scope=ScopeType.SESSION)
	@In(required=false, scope=ScopeType.SESSION)
	private McomPersonas mcomPersonasReg = new McomPersonas();

	/**
	 * @uml.property  name="registered"
	 */
	private boolean registered;
	/**
	 * @uml.property  name="isEdicion"
	 */
	private boolean isEdicion;

	
	/**
	 * @uml.property  name="mcomPersonasActionDAO"
	 * @uml.associationEnd  
	 */
	@In
	private McomPersonasActionDAO mcomPersonasActionDAO;
	/**
	 * @uml.property  name="mpaValoresDAO"
	 * @uml.associationEnd  
	 */
	@In
	private MpaValoresActionDAO mpaValoresActionDAO;






	public void guardar(){
		System.out.println("isEdicion " + isEdicion);
		if(isEdicion){ 
			mcomPersonasReg = mcomPersonasActionDAO.merge(mcomPersonasReg);	
			facesMessages.add("Con éxito actualizado como \""+mcomPersonasReg.getPerNombres()+" "+mcomPersonasReg.getPerApellidos() + "\"");

		}else{
			mcomPersonasReg.getId().setPerTipoIdentificacion(mcomPersonasReg.getMpaValoresByFkPersonasValoresTipoid().getId().getVarCodigo());
			mcomPersonasReg.getId().setPerParaTipoId(mcomPersonasReg.getMpaValoresByFkPersonasValoresTipoid().getId().getParCodigo());
			/*mcomPersonasReg.getId().getPerIdentificacion(mcomPersonasReg.getMpaValoresByFkPersonasValoresTipoid().getId().get);
			System.out.println("id " + id.getPerIdentificacion());
			System.out.println("id " + id.getPerTipoIdentificacion());
			System.out.println("id " + id.getPerParaTipoId());*/
			
			if(mcomPersonasActionDAO.findById( mcomPersonasReg.getId()) == null ){
				mcomPersonasActionDAO.persist(mcomPersonasReg);	
				isEdicion = true;
				facesMessages.add("Con éxito registrado como \""+mcomPersonasReg.getPerNombres()+" "+mcomPersonasReg.getPerApellidos() + "\"");

			}else{
				facesMessages.add("Existe una persona con este tipo y número de identificación. ");
			}

		}				
		registered = true;
	}

	public void nuevo(){
		mcomPersonasReg = new McomPersonas();
		mcomPersonasReg.setId(new McomPersonasId());
		isEdicion = false;
	}




	/* (non-Javadoc)
	 * @see com.leasing.popular.wf.modulos.negocio.persona.PerRegistro#seleccionPersona(com.leasing.popular.wf.table.negocio.McomPersonas)
	 */
	public void seleccionPersona(McomPersonas mcomPersonas_){

		this.mcomPersonasReg =  mcomPersonasActionDAO.findById( mcomPersonas_.getId());
		isEdicion = true;
	}

	/* (non-Javadoc)
	 * @see com.leasing.popular.wf.modulos.negocio.persona.PerRegistro#invalid()
	 */
	public void invalid(){
		facesMessages.add("Por favor intente otra vez");
	}

	/**
	 * @return
	 * @uml.property  name="registered"
	 */
	public boolean isRegistered(){
		return registered;
	}

	/**
	 * @return
	 * @uml.property  name="mcomPersonasReg"
	 */
	public McomPersonas getMcomPersonasReg() {
		return mcomPersonasReg;
	}


	/**
	 * @param  msegRoles
	 * @uml.property  name="mcomPersonasReg"
	 */
	public void setMcomPersonasReg(McomPersonas msegRoles) {
		this.mcomPersonasReg = msegRoles;
	}



	/**
	 * @return
	 * @uml.property  name="isEdicion"
	 */
	public boolean isEdicion() {
		return isEdicion;
	}







	@Remove
	public void destroy() {}
}
