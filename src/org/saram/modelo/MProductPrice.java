package org.saram.modelo;

import java.io.Serializable;

public class MProductPrice implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4836389277066952184L;
	private Integer m_pricelist_version_id;
	private Integer m_product_id;
	private float pricelist;
	private float isactive;
	private float pricestd;
	private float pricelimit;

	public Integer getm_pricelist_version_id() {
		return m_pricelist_version_id;
	}

	public void setm_pricelist_version_id(Integer m_pricelist_version_id) {
		this.m_pricelist_version_id = m_pricelist_version_id;
	}

	public Integer getM_product_id() {
		return m_product_id;
	}

	public void setM_product_id(Integer m_product_id) {
		this.m_product_id = m_product_id;
	}

	public float getPricelist() {
		return pricelist;
	}

	public void setPricelist(float pricelist) {
		this.pricelist = pricelist;
	}

	public float getPricestd() {
		return pricestd;
	}

	public void setPricestd(float pricestd) {
		this.pricestd = pricestd;
	}

	public float getPricelimit() {
		return pricelimit;
	}

	public void setPricelimit(float pricelimit) {
		this.pricelimit = pricelimit;
	}

	public float getIsactive() {
		return isactive;
	}

	public void setIsactive(float isactive) {
		this.isactive = isactive;
	}
	
	

}
