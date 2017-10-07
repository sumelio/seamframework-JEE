package com.leasing.popular.wf.modulos.negocio.estudio;

import javax.ejb.Local;

import com.leasing.popular.wf.table.negocio.estudio.McomEstudio;


@Local
public interface EstRegistro{

	public McomEstudio getMcomEstudioReg();
	public void setMcomEstudioReg(McomEstudio mcomEstudioReg);
	public void setVerify(String verify);
	public void seleccionEstudio(McomEstudio mcomEstudio_);
	public String getVerify();
	public boolean isRegistered();
	public void invalid();
	public void guardar();
	public void nuevo(); 
	public boolean isEdicion();
	public void findNegocioId();
	public String siguiente();
	
	public boolean isEnviarOpericiones();
	public void setEnviarOpericiones(boolean enviarOpericiones);
	public void destroy();

}