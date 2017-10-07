//$Id: UsuarioBuscarAction.java 5509 2007-06-25 16:19:40Z gavin $
package com.leasing.popular.wf.modulos.seguridad.rol.bean;

import com.leasing.popular.wf.table.seguridad.roles.MsegRolmodulos;
 
/**
 * @author       Usuario
 */
public class BooleanCheckboxRolMod
{
	/**
	 * @uml.property  name="msegRolmodulos"
	 * @uml.associationEnd  
	 */
	public MsegRolmodulos msegRolmodulos;
	/**
	 * @uml.property  name="rolConsultaBoolean"
	 */
	private boolean rolConsultaBoolean;
	/**
	 * @uml.property  name="rolEliminarBoolean"
	 */
	private boolean rolEliminarBoolean;
	/**
	 * @uml.property  name="rolActualizarBoolean"
	 */
	private boolean rolActualizarBoolean;
	/**
	 * @uml.property  name="rolInsertarBoolean"
	 */
	private boolean rolInsertarBoolean;
	
	public  BooleanCheckboxRolMod(){
		
	}
	
	
	public  BooleanCheckboxRolMod(MsegRolmodulos msegRolmodulos){
	try{
		this.msegRolmodulos = msegRolmodulos;
		this.rolActualizarBoolean = msegRolmodulos.getRolActualizar()==1;
		this.rolEliminarBoolean = msegRolmodulos.getRolEliminar()==1;
		this.rolInsertarBoolean = msegRolmodulos.getRolInsertar()==1;
		this.rolConsultaBoolean = msegRolmodulos.getRolConsulta()==1;
	}catch (Exception e) {
		// TODO: handle exception
		System.out.println(""+e.getMessage());
	}
	}

	/**
	 * @return
	 * @uml.property  name="msegRolmodulos"
	 */
	public MsegRolmodulos getMsegRolmodulos() {
		return msegRolmodulos;
	}

	/**
	 * @param  msegRolmodulos
	 * @uml.property  name="msegRolmodulos"
	 */
	public void setMsegRolmodulos(MsegRolmodulos msegRolmodulos) {
		this.msegRolmodulos = msegRolmodulos;
	}
	
	

	/**
	 * @return
	 * @uml.property  name="rolConsultaBoolean"
	 */
	public boolean isRolConsultaBoolean() {
		return rolConsultaBoolean;
	}

	/**
	 * @param  rolConsultaBoolean
	 * @uml.property  name="rolConsultaBoolean"
	 */
	public void setRolConsultaBoolean(boolean rolConsultaBoolean) {
		this.rolConsultaBoolean = rolConsultaBoolean;
		msegRolmodulos.setRolConsulta(valorLong(rolConsultaBoolean));
	}

	/**
	 * @return
	 * @uml.property  name="rolEliminarBoolean"
	 */
	public boolean isRolEliminarBoolean() {
		return rolEliminarBoolean;
	}

	/**
	 * @param  rolEliminarBoolean
	 * @uml.property  name="rolEliminarBoolean"
	 */
	public void setRolEliminarBoolean(boolean rolEliminarBoolean) {
		this.rolEliminarBoolean = rolEliminarBoolean;
		msegRolmodulos.setRolEliminar(valorLong(rolEliminarBoolean));
	}

	/**
	 * @return
	 * @uml.property  name="rolActualizarBoolean"
	 */
	public boolean isRolActualizarBoolean() {
		return rolActualizarBoolean;
	}

	/**
	 * @param  rolActualizarBoolean
	 * @uml.property  name="rolActualizarBoolean"
	 */
	public void setRolActualizarBoolean(boolean rolActualizarBoolean) {
		this.rolActualizarBoolean = rolActualizarBoolean;
		msegRolmodulos.setRolActualizar(valorLong(rolActualizarBoolean));
	}

	/**
	 * @return
	 * @uml.property  name="rolInsertarBoolean"
	 */
	public boolean isRolInsertarBoolean() {
		return rolInsertarBoolean;
	}

	/**
	 * @param  rolInsertarBoolean
	 * @uml.property  name="rolInsertarBoolean"
	 */
	public void setRolInsertarBoolean(boolean rolInsertarBoolean) {
		this.rolInsertarBoolean = rolInsertarBoolean;
		msegRolmodulos.setRolInsertar(valorLong(rolInsertarBoolean));
	}
	
  private Long valorLong(boolean checked){
	  
	if(checked){
		return new Long(1);
	}else{
		return new Long(0);
	}
	  
  }

}