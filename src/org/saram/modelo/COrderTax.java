package org.saram.modelo;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class COrderTax implements Serializable{
	private Integer c_order_id;
	private Integer c_tax_id;
	private Integer ad_org_id;
	private Integer ad_client_id;
	private String isactive;
	private Timestamp created;
	private Integer createdby;
	private Timestamp updated;
	private Integer updatedby;
	private Float taxbaseamt;
	private Float taxamt;
	private String istaxincluded;
	public Integer getAd_org_id() {
		return ad_org_id;
	}
	public void setAd_org_id(Integer ad_org_id) {
		this.ad_org_id = ad_org_id;
	}
	public Integer getAd_client_id() {
		return ad_client_id;
	}
	public void setAd_client_id(Integer ad_client_id) {
		this.ad_client_id = ad_client_id;
	}
	public Integer getC_order_id() {
		return c_order_id;
	}
	public void setC_order_id(Integer c_order_id) {
		this.c_order_id = c_order_id;
	}
	public Integer getC_tax_id() {
		return c_tax_id;
	}
	public void setC_tax_id(Integer c_tax_id) {
		this.c_tax_id = c_tax_id;
	}
	public String getIsactive() {
		return isactive;
	}
	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public Integer getCreatedby() {
		return createdby;
	}
	public void setCreatedby(Integer createdby) {
		this.createdby = createdby;
	}
	public Timestamp getUpdated() {
		return updated;
	}
	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}
	public Integer getUpdatedby() {
		return updatedby;
	}
	public void setUpdatedby(Integer updatedby) {
		this.updatedby = updatedby;
	}
	public Float getTaxbaseamt() {
		return taxbaseamt;
	}
	public void setTaxbaseamt(Float taxbaseamt) {
		this.taxbaseamt = taxbaseamt;
	}
	public Float getTaxamt() {
		return taxamt;
	}
	public void setTaxamt(Float taxamt) {
		this.taxamt = taxamt;
	}
	public String getIstaxincluded() {
		return istaxincluded;
	}
	public void setIstaxincluded(String istaxincluded) {
		this.istaxincluded = istaxincluded;
	}
	
}
