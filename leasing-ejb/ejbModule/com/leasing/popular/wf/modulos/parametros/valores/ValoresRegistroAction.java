package com.leasing.popular.wf.modulos.parametros.valores;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.faces.convert.Converter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.faces.FacesMessages;

import com.leasing.popular.wf.generico.view.converter.ConverterParametro;
import com.leasing.popular.wf.table.parametros.parametro.MpaParametro;
import com.leasing.popular.wf.table.parametros.parametro.MpaParametroActionDAO;
import com.leasing.popular.wf.table.parametros.parametro.MpaParametroDAO;
import com.leasing.popular.wf.table.parametros.valores.MpaValores;
import com.leasing.popular.wf.table.parametros.valores.MpaValoresActionDAO;
import com.leasing.popular.wf.table.parametros.valores.MpaValoresDAO;
import com.leasing.popular.wf.table.parametros.valores.MpaValoresId;
 



/**
 * @author       Usuario
 */
@Stateful
@Name("valoresRegistroAction")
@Scope(ScopeType.EVENT)
@Restrict("#{identity.loggedIn}")
public class ValoresRegistroAction implements ValoresRegistro
{
	@PersistenceContext
	private EntityManager em;

	@In
	private   FacesMessages facesMessages;

   
	/**
	 * @uml.property  name="mpaValoresReg"
	 * @uml.associationEnd  
	 */
	private MpaValores mpaValoresReg = new MpaValores();

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
	 * @uml.property  name="mpaValoresDAO"
	 * @uml.associationEnd  
	 */
	@In
	private MpaValoresActionDAO mpaValoresActionDAO;
	/**
	 * @uml.property  name="mpaParametroDAO"
	 * @uml.associationEnd  
	 */
	@In
	private MpaParametroActionDAO mpaParametroActionDAO;
	
	/**
	 * @uml.property  name="listaParametros"
	 */
	private List<MpaParametro> listaParametros = new ArrayList<MpaParametro>();



	public void guardarValor(){
		registered = true;
		
		System.out.println("CODIGO "+ mpaValoresReg.getMpaParametro() );
		
		if (mpaValoresReg.getMpaParametro() != null) {
			
		
		Long codigoNuevo = mpaValoresActionDAO.genereSecuencia( mpaValoresReg.getMpaParametro());
		mpaValoresReg.setId(new MpaValoresId(codigoNuevo, mpaValoresReg.getMpaParametro().getParCodigo() ));
		//codigoNuevo
		if(!mpaValoresActionDAO.isExisteValor(  mpaValoresReg)){ 
			MpaValoresId mpaValoresId = new MpaValoresId(codigoNuevo, mpaValoresReg.getMpaParametro().getParCodigo());
			mpaValoresReg.setId(mpaValoresId);
			mpaValoresActionDAO.persist( mpaValoresReg);	
			facesMessages.add("Con éxito registrado como \""+mpaValoresReg.getVarNombre() + "\"");
			mpaValoresReg = new MpaValores(); 
			registered = true;
		}else{
			facesMessages.add("Existe un valor para el parametro \""+ mpaValoresReg.getMpaParametro().getParNombre()  +"\" con este nombre \""+mpaValoresReg.getVarNombre() + "\". Por favor intente nuevamente con un nombre diferente.");

		}
		}else {
			facesMessages.add("Debe seleccinar el parametro al cual pertence el nuevo valor que desea crear.");
		}
	}

 
	public void invalid(){
		facesMessages.add("Por favor intente nuevamente");
	}

	/**
	 * @return
	 * @uml.property  name="registered"
	 */
	public boolean isRegistered(){
		return registered;
	}
	
	public Converter getConverter() {
		return new ConverterParametro(mpaParametroActionDAO.findAll( ));
	}
	


	/**
	 * @return
	 * @uml.property  name="listaParametros"
	 */
	public List<MpaParametro> getListaParametros() {
		listaParametros = mpaParametroActionDAO.findAll( );
     return listaParametros;
	}


	/**
	 * @param  listaParametros
	 * @uml.property  name="listaParametros"
	 */
	public void setListaParametros(List<MpaParametro> listaParametros) {
		this.listaParametros = listaParametros;
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
	 * @uml.property  name="mpaValoresReg"
	 */
	public MpaValores getMpaValoresReg() {
		return mpaValoresReg;
	}


	/**
	 * @param  mpaValoresReg
	 * @uml.property  name="mpaValoresReg"
	 */
	public void setMpaValoresReg(MpaValores mpaValoresReg) {
		System.out.println("mpaValoresReg " + mpaValoresReg.getVarNombre());
		System.out.println("mpaValoresReg++ " + mpaValoresReg.getMpaParametro().getParNombre());
		this.mpaValoresReg = mpaValoresReg;
	}



	@Remove
	public void destroy() {}
}
