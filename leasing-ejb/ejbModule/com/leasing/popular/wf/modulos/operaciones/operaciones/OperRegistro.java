package com.leasing.popular.wf.modulos.operaciones.operaciones;

import java.util.List;

import javax.ejb.Local;

import com.leasing.popular.wf.table.operaciones.observaciones.MoperObservaciones;
import com.leasing.popular.wf.table.operaciones.operaciones.MoperOperaciones;




/**
 * @author          Usuario
 */
@Local
public interface OperRegistro{
	public MoperOperaciones getMoperOperacionesReg();
	public void setMoperOperacionesReg(MoperOperaciones moperOperacionesReg);
	public void setVerify(String verify);
	public String getVerify();
	public boolean isRegistered();
	public MoperObservaciones getMoperObservacionesReg();
	public void seleccionMoperOperaciones(MoperOperaciones moperOperacionesReg_);
	public void setMoperObservacionesReg(MoperObservaciones moperObservacionesReg);
	public String getObserv();
	public void setObserv(String observ);
	public void invalid();
	public void guardar();
	public List<MoperObservaciones> getListaObservaciones();
	public void setListaObservaciones(List<MoperObservaciones> listaObservaciones);
	public void generarFichero();
	public void destroy();
}