package com.leasing.popular.wf.table.seguridad.usuarios;
 

//Generated 21/11/2009 03:55:19 PM by Hibernate Tools 3.2.5.Beta

 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.Pattern;
import org.jboss.seam.annotations.Name;

import com.leasing.popular.wf.table.seguridad.estado.MsegEstados;

/**
 * MsegUsuarios generated by hbm2java
 */
@Entity
@Name("msegUsuarios") 
@Table(name = "MSEG_USUARIOS", uniqueConstraints = @UniqueConstraint(columnNames = "USU_USUARIO"))
public class MsegUsuarios implements java.io.Serializable {

	/**
	 * @uml.property  name="usuCedula"
	 */
	private Long usuCedula;
	/**
	 * @uml.property  name="usuUsuario"
	 */
	private String usuUsuario;
	/**
	 * @uml.property  name="usuContrasena"
	 */
	private String usuContrasena;
	/**
	 * @uml.property  name="usuNombres"
	 */
	private String usuNombres;
	/**
	 * @uml.property  name="usuApellidos"
	 */
	private String usuApellidos;
	/**
	 * @uml.property  name="msegEstados"
	 * @uml.associationEnd  
	 */
	private MsegEstados msegEstados;
	

	public MsegUsuarios() {
	}

	public MsegUsuarios(Long usuCedula, String usuContrasena,
			String usuNombres, String usuApellidos) {
		this.usuCedula = usuCedula;
		this.usuContrasena = usuContrasena;
		this.usuNombres = usuNombres;
		this.usuApellidos = usuApellidos;
	}

	public MsegUsuarios(Long usuCedula, String usuUsuario,
			String usuContrasena, String usuNombres, String usuApellidos,
			MsegEstados msegEstados) {
		this.usuCedula = usuCedula;
		this.usuUsuario = usuUsuario;
		this.usuContrasena = usuContrasena;
		this.usuNombres = usuNombres;
		this.usuApellidos = usuApellidos;
		this.msegEstados = msegEstados;
		
	}

	/**
	 * @return
	 * @uml.property  name="usuCedula"
	 */
	@Id
	@Column(name = "USU_CEDULA", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getUsuCedula() {
		return this.usuCedula;
	}

	/**
	 * @param  usuCedula
	 * @uml.property  name="usuCedula"
	 */
	public void setUsuCedula(Long usuCedula) {
		this.usuCedula = usuCedula;
	}

	/**
	 * @return
	 * @uml.property  name="usuUsuario"
	 */
	@Column(name = "USU_USUARIO", length = 30)
	public String getUsuUsuario() {
		return this.usuUsuario==null?usuUsuario:usuUsuario.toUpperCase();
	}

	/**
	 * @param  usuUsuario
	 * @uml.property  name="usuUsuario"
	 */
	public void setUsuUsuario(String usuUsuario) {
		this.usuUsuario = usuUsuario;
	}

	/**
	 * @return
	 * @uml.property  name="usuContrasena"
	 */
	@Column(name = "USU_CONTRASENA", nullable = false, length = 30)
	public String getUsuContrasena() {
		return this.usuContrasena;
	}

	/**
	 * @param  usuContrasena
	 * @uml.property  name="usuContrasena"
	 */
	public void setUsuContrasena(String usuContrasena) {
		this.usuContrasena = usuContrasena;
	}

	/**
	 * @return
	 * @uml.property  name="usuNombres"
	 */
	@Pattern(regex="[a-z������A-Z�����]+\\s*[a-z������A-Z�����]*\\s*",message="Dig�te un nombre valido")
	@Column(name = "USU_NOMBRES", nullable = false, length = 30)
	public String getUsuNombres() {
		return this.usuNombres;
	}

	/**
	 * @param  usuNombres
	 * @uml.property  name="usuNombres"
	 */
	public void setUsuNombres(String usuNombres) {
		this.usuNombres = usuNombres==null?usuNombres:usuNombres.toUpperCase();
	}

	/**
	 * @return
	 * @uml.property  name="usuApellidos"
	 */
	@Pattern(regex="[a-z������A-Z�����]+\\s*[a-z������A-Z�����]*\\s*",message="Dig�te un apellido valido")
	@Column(name = "USU_APELLIDOS", nullable = false, length = 30)
	public String getUsuApellidos() {
		return this.usuApellidos;
	}

	/**
	 * @param  usuApellidos
	 * @uml.property  name="usuApellidos"
	 */
	public void setUsuApellidos(String usuApellidos) {
		this.usuApellidos = usuApellidos==null?usuApellidos:usuApellidos.toUpperCase();
	}

	
	/**
	 * @return
	 * @uml.property  name="msegEstados"
	 */
	@ManyToOne
	@JoinColumn(name = "USU_ESTADOS")
	public MsegEstados getMsegEstados() {
		  
		return this.msegEstados;
	}

	/**
	 * @param  msegEstados
	 * @uml.property  name="msegEstados"
	 */
	public void setMsegEstados(MsegEstados msegEstados) {
		this.msegEstados = msegEstados;
	}


}
