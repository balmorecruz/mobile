package org.saram.modelo;

public class MPromocion {
	private Integer m_promocion_id;
	private String description;
	private String isactive;
	private String value;
	private Integer c_bpartner_id;
	private Integer qtyPromocion;
	private Integer m_product_id;
	private Integer m_product_promo_id;
	private Integer t_clasificacion_id;
	
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
	public Integer getC_bpartner_id() {
		return c_bpartner_id;
	}
	public void setC_bpartner_id(Integer c_bpartner_id) {
		this.c_bpartner_id = c_bpartner_id;
	}
	public Integer getQtyPromocion() {
		return qtyPromocion;
	}
	public void setQtyPromocion(Integer qtyPromocion) {
		this.qtyPromocion = qtyPromocion;
	}
	public Integer getM_product_id() {
		return m_product_id;
	}
	public void setM_product_id(Integer m_product_id) {
		this.m_product_id = m_product_id;
	}
	public Integer getM_product_promo_id() {
		return m_product_promo_id;
	}
	public void setM_product_promo_id(Integer m_product_promo_id) {
		this.m_product_promo_id = m_product_promo_id;
	}
	public Integer getT_clasificacion_id() {
		return t_clasificacion_id;
	}
	public void setT_clasificacion_id(Integer t_clasificacion_id) {
		System.out.println(t_clasificacion_id);
		this.t_clasificacion_id = t_clasificacion_id;
	}
}
