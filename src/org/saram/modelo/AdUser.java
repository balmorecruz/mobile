package org.saram.modelo;

import java.io.Serializable;

public class AdUser implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer ad_user_id;
	private Integer c_bpartner_id;
	private String name;
	private String password;
	private String value;

	public Integer getAd_user_id() {
		return ad_user_id;
	}

	public void setAd_user_id(Integer ad_user_id) {
		this.ad_user_id = ad_user_id;
	}

	public String getName() {
		return name;
	}

	public Integer getC_bpartner_id() {
		return c_bpartner_id;
	}

	public void setC_bpartner_id(Integer c_bpartner_id) {
		this.c_bpartner_id = c_bpartner_id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}	
}
