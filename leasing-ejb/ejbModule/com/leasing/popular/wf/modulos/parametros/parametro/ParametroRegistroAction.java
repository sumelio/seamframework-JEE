package com.leasing.popular.wf.modulos.parametros.parametro;

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

import com.leasing.popular.wf.table.parametros.parametro.MpaParametro;
import com.leasing.popular.wf.table.parametros.parametro.MpaParametroActionDAO;
import com.leasing.popular.wf.table.parametros.parametro.MpaParametroDAO;
 



/**
 * @author       Usuario
 */
@Stateful
@Name("parametroRegistroAction")
@Scope(ScopeType.EVENT)
@Restrict("#{identity.loggedIn}")
public class ParametroRegistroAction implements ParametroRegistro
{
	@PersistenceContext
	private EntityManager em;

	@In
	private   FacesMessages facesMessages;

   
	/**
	 * @uml.property  name="mpaParametroReg"
	 * @uml.associationEnd  
	 */
	private MpaParametro mpaParametroReg = new MpaParametro();

    /**
	 * @uml.property  name="verify"
	 */
    @Out(required=false)
	private String verify;

	/**
	 * @uml.property  name="registered"
	 */
	private boolean registered;


	/**
	 * @uml.property  name="mpaTipoParametroDAO"
	 * @uml.associationEnd  
	 */
	@In
	private MpaParametroActionDAO mpaParametroActionDAO;


	public void guardar(){
		registered = true;
		mpaParametroReg.setParCodigo(mpaParametroActionDAO.genereSecuencia( ));
		if(!mpaParametroActionDAO.isExistePara( mpaParametroReg)){
			mpaParametroActionDAO.persist( mpaParametroReg);	
			facesMessages.add("Con éxito registrado como \""+mpaParametroReg.getParNombre() + "\"");
			mpaParametroReg = new MpaParametro(); 
			registered = true;
		}else{
			facesMessages.add("Existe un parametro con el nombre " +mpaParametroReg.getParNombre() +". Por favor intente nuevamente con un nombre diferente.");
		}
	}

 
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
	 * @uml.property  name="verify"
	 */
	public String getVerify(){
		return verify;
	}

	/**
	 * @param  verify
	 * @uml.property  name="verify"
	 */
	public void setVerify(String verify){
		this.verify = verify;
	}

 
	
	
 


 

	/**
	 * @return
	 * @uml.property  name="mpaParametroReg"
	 */
	public MpaParametro getMpaParametroReg() {
		return mpaParametroReg;
	}


	/**
	 * @param  mpaParametroReg
	 * @uml.property  name="mpaParametroReg"
	 */
	public void setMpaParametroReg(MpaParametro mpaParametroReg) {
		this.mpaParametroReg = mpaParametroReg;
	}


	@Remove
	public void destroy() {}
}
