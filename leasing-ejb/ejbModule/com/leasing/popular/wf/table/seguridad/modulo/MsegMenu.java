package com.leasing.popular.wf.table.seguridad.modulo;

// Generated 24/10/2009 09:04:14 PM by Hibernate Tools 3.2.4.GA

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.NotNull;
import org.jboss.seam.annotations.Name;

/**
 * MsegServicios generated by hbm2java
 */
@Entity
@Name("msegMenu") 
@Table(name = "MSEG_MENU")
public class MsegMenu implements java.io.Serializable {

	 /**
	 * @uml.property  name="menCodigo"
	 */
	private Long menCodigo;
	 /**
	 * @uml.property  name="menNombre"
	 */
	private String menNombre;
	 
	 public MsegMenu() {}
	 
	 public MsegMenu(Long menCodigo) {
			this.menCodigo = menCodigo;
		}

		public MsegMenu(Long menCodigo, String menNombre ) {
			this.menCodigo = menCodigo;
			this.menNombre = menNombre; 
		}
	 
	/**
	 * @return
	 * @uml.property  name="menCodigo"
	 */
	@NotNull
	@Id
	@Column(name = "MEN_CODIGO", unique = true, nullable = false, precision = 4, scale = 0)
	public Long getMenCodigo() {
		return menCodigo;
	}
	/**
	 * @param  mencodigoo
	 * @uml.property  name="menCodigo"
	 */
	public void setMenCodigo(Long mencodigoo) {
		this.menCodigo = mencodigoo;
	}
	
	/**
	 * @return
	 * @uml.property  name="menNombre"
	 */
	@Column(name = "MEN_NOMBRE", nullable = false, length = 30)
	public String getMenNombre() {
		return menNombre;
	}
	/**
	 * @param  mennombre
	 * @uml.property  name="menNombre"
	 */
	public void setMenNombre(String mennombre) {
		this.menNombre = mennombre;
	}
}
