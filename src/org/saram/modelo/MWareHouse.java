package org.saram.modelo;

public class MWareHouse {
	private Integer m_warehouse_id;
	private Integer ad_client_id;
	private String name;
	private String value;
	private Integer c_location_id;

	public Integer getM_warehouse_id() {
		return m_warehouse_id;
	}

	public void setM_warehouse_id(Integer m_warehouse_id) {
		this.m_warehouse_id = m_warehouse_id;
	}

	public Integer getAd_client_id() {
		return ad_client_id;
	}

	public void setAd_client_id(Integer ad_client_id) {
		this.ad_client_id = ad_client_id;
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

	public Integer getC_location_id() {
		return c_location_id;
	}

	public void setC_location_id(Integer c_location_id) {
		this.c_location_id = c_location_id;
	}

}
