package org.saram.modelo;

public class MProduct {
	private Integer m_product_id;
	private String name;
	private String value;
	private Integer ad_client_id;
	private Integer m_product_category_id;
	private Float conversionqq;
	private String isactive;
	private Float freight_discount;
	private String apply_discount;
	
	
	public Float getFreight_discount() {
		return freight_discount;
	}

	public void setFreight_discount(Float freight_discount) {
		this.freight_discount = freight_discount;
	}

	public String getApply_discount() {
		return apply_discount;
	}

	public void setApply_discount(String apply_discount) {
		this.apply_discount = apply_discount;
	}

	public Integer getM_product_id() {
		return m_product_id;
	}

	public void setM_product_id(Integer m_product_id) {
		this.m_product_id = m_product_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getAd_client_id() {
		return ad_client_id;
	}

	public void setAd_client_id(Integer ad_client_id) {
		this.ad_client_id = ad_client_id;
	}

	public Integer getM_product_category_id() {
		return m_product_category_id;
	}

	public void setM_product_category_id(Integer m_product_category_id) {
		this.m_product_category_id = m_product_category_id;
	}

	public Float getConversionqq() {
		return conversionqq;
	}

	public void setConversionqq(Float conversionqq) {
		this.conversionqq = conversionqq;
	}

	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}
	
}
