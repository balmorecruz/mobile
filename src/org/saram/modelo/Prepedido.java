package org.saram.modelo;

import java.sql.Timestamp;

public class Prepedido {

	private Integer c_order_id;
	private String documentno;
	private Timestamp created;
	private Float grandtotal;
	public Integer getC_order_id() {
		return c_order_id;
	}
	public void setC_order_id(Integer c_order_id) {
		this.c_order_id = c_order_id;
	}
	public String getDocumentno() {
		return documentno;
	}
	public void setDocumentno(String documentno) {
		this.documentno = documentno;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public Float getGrandtotal() {
		return grandtotal;
	}
	public void setGrandtotal(Float grandtotal) {
		this.grandtotal = grandtotal;
	}

	
	
}
