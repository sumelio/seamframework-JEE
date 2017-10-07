package com.leasing.popular.wf.table.parametros.valores;

// Generated 12/12/2009 10:26:43 AM by Hibernate Tools 3.2.4.GA

 
 

import javax.persistence.Column;
import javax.persistence.Embeddable;
 

/**
 * MpaValoresId generated by hbm2java
 */
/**
 * MpaValoresId generated by hbm2java
 */
@Embeddable
public class MpaValoresId implements java.io.Serializable {

	/**
	 * @uml.property  name="varCodigo"
	 */
	private Long varCodigo;
	/**
	 * @uml.property  name="parCodigo"
	 */
	private Long parCodigo;

	public MpaValoresId() {
	}

	public MpaValoresId(Long varCodigo, Long parCodigo) {
		this.varCodigo = varCodigo;
		this.parCodigo = parCodigo;
	}

	/**
	 * @return
	 * @uml.property  name="varCodigo"
	 */
	@Column(name = "VAR_CODIGO", nullable = false, precision = 22, scale = 0)
	public Long getVarCodigo() {
		return this.varCodigo;
	}

	/**
	 * @param  varCodigo
	 * @uml.property  name="varCodigo"
	 */
	public void setVarCodigo(Long varCodigo) {
		this.varCodigo = varCodigo;
	}

	/**
	 * @return
	 * @uml.property  name="parCodigo"
	 */
	@Column(name = "PAR_CODIGO", nullable = false, precision = 22, scale = 0)
	public Long getParCodigo() {
		return this.parCodigo;
	}

	/**
	 * @param  parCodigo
	 * @uml.property  name="parCodigo"
	 */
	public void setParCodigo(Long parCodigo) {
		this.parCodigo = parCodigo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MpaValoresId))
			return false;
		MpaValoresId castOther = (MpaValoresId) other;

		return ((this.getVarCodigo() == castOther.getVarCodigo()) || (this
				.getVarCodigo() != null
				&& castOther.getVarCodigo() != null && this.getVarCodigo()
				.equals(castOther.getVarCodigo())))
				&& ((this.getParCodigo() == castOther.getParCodigo()) || (this
						.getParCodigo() != null
						&& castOther.getParCodigo() != null && this
						.getParCodigo().equals(castOther.getParCodigo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getVarCodigo() == null ? 0 : this.getVarCodigo().hashCode());
		result = 37 * result
				+ (getParCodigo() == null ? 0 : this.getParCodigo().hashCode());
		return result;
	}

}
