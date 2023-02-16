package org.saram.modelo;

public class MPromoClasificacion {
	private Integer m_promoclasificacion_id;
	private Integer m_promocion_id;
	private String name;
	private String description;
	private String isactive;
	private String value;
	private Integer t_clasificacion_id;
	
	public Integer getM_promoclasificacion_id() {
		return m_promoclasificacion_id;
	}
	public void setM_promoclasificacion_id(Integer m_promoclasificacion_id) {
		this.m_promoclasificacion_id = m_promoclasificacion_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getM_promocion_id() {
		return m_promocion_id;
	}
	public void setM_promocion_id(Integer m_promocion_id) {
		this.m_promocion_id = m_promocion_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIsactive() {
		return isactive;
	}
	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public Integer getT_clasificacion_id() {
		return t_clasificacion_id;
	}
	public void setT_clasificacion_id(Integer t_clasificacion_id) {
		this.t_clasificacion_id = t_clasificacion_id;
	}
}
