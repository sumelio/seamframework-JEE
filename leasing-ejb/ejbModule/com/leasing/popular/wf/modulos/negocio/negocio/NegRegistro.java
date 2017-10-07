package com.leasing.popular.wf.modulos.negocio.negocio;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.Query;

import com.leasing.popular.wf.table.negocio.negocio.McomNegocio;
import com.leasing.popular.wf.table.parametros.valores.MpaValores;




/**
 * @author          Usuario
 */
@Local
public interface NegRegistro{

	public McomNegocio getMcomNegocioReg();
	public void setMcomNegocioReg(McomNegocio mcomNegocioReg);
	public void setVerify(String verify);
	public void seleccionNegocio(McomNegocio mcomNegocio_);
	public String getVerify();
	public boolean isRegistered();
	public void invalid();
	public String siguiente();
	public void guardar();
	public void nuevo();
	public void findPersona();
	public String resultPersona();
	public boolean isEdicion();
	public boolean isShowCausal1();
    public boolean isShowCausal2();
    public boolean isShowCausal3();
    
    public void ajaxDisableVehiculo();
	public boolean isDisableVehiculo();
   public void setDisableVehiculo(boolean disableVehiculo);

	public List<MpaValores> getMarcaAutomovil();
    public void setMarcaAutomovil(List<MpaValores> marcaAutomovil);
	public void destroy();

}