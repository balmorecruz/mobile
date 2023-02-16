package org.saram.modelo;

public class CTax {
	private Integer c_tax_id;
	private Integer name;
	/*
	 * Filtrar por ad_client=10000
	 * */
	private Integer ad_client_id;
	public Integer getC_tax_id() {
		return c_tax_id;
	}
	public void setC_tax_id(Integer c_tax_id) {
		this.c_tax_id = c_tax_id;
	}
	public Integer getName() {
		return name;
	}
	public void setName(Integer name) {
		this.name = name;
	}
	public Integer getAd_client_id() {
		return ad_client_id;
	}
	public void setAd_client_id(Integer ad_client_id) {
		this.ad_client_id = ad_client_id;
	}
}
