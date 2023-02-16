package org.saram.modelo;

import java.sql.Timestamp;

public class COrder {
	private Integer c_order_id;
	private Integer ad_client_id;
	private Integer ad_org_id;
	private String isactive;
	private Timestamp created;
	private Integer createdby;
	private Timestamp updated;
	private Integer updatedby;
	private String issotrx;
	private String documentno;
	private String docstatus;
	private String docaction;
	private String processing;
	private String processed;
	private Integer c_doctype_id;
	private Integer c_doctypetarget_id;
	private Integer c_charge_id;
	private String description;
	private String isapproved;
	private String iscreditapproved;
	private String isdelivered;
	private String isinvoiced;
	private String isprinted;
	private String istransferred;
	private String isselected;
	private Integer salesrep_id;
	private Timestamp dateordered;
	private Timestamp datepromised;
	private Timestamp dateprinted;
	private Timestamp dateacct;
	private Integer c_bpartner_id;
	private Integer c_bpartner_location_id;
	private String poreference;
	private String isdiscountprinted;
	private Integer c_currency_id;
	private String paymentrule;
	private Integer c_paymentterm_id;
	private String invoicerule;
	private String deliveryrule;
	private String freightcostrule;
	private Integer freightamt;
	private String deliveryviarule;
	private Integer m_shipper_id;
	private Integer c_Stringge_id;
	private Integer Stringgeamt;
	private String priorityrule;
	private Float totallines;
	private Float grandtotal;
	private Integer m_warehouse_id;
	private Integer m_pricelist_id;
	private String istaxincluded;
	private Integer c_campaign_id;
	private Integer c_project_id;
	private Integer c_activity_id;
	private String posted;
	private Integer c_payment_id;
	private Integer c_cashline_id;
	private String sendemail;
	private Integer ad_user_id;
	private String copyfrom;
	private String isselfservice;
	private Integer ad_orgtrx_id;
	private Integer user1_id;
	private Integer user2_id;
	private Integer c_conversiontype_id;
	private Integer bill_bpartner_id;
	private Integer bill_location_id;
	private Integer bill_user_id;
	private Integer pay_bpartner_id;
	private Integer pay_location_id;
	private Integer ref_order_id;
	private String isdropship;
	private Float volume;
	private Float weight;
	private String ordertype;
	private Integer c_pos_id;
	private Float amounttendered;
	private Float amountrefunded;
	private Integer link_order_id;
	private Integer m_freightcategory_id;
	private Integer dropship_bpartner_id;
	private Integer dropship_location_id;
	private Integer dropship_user_id;
	private String promotioncode;
	private Integer c_ordersource_id;
	private Float processedon;
	private Integer lco_taxpayertype_id;
	private Integer v_liability_acct;
	private String convertir_cotizacion;
	private String generar_factura;
	private String contribuyente;
	private String puestoen;
	private String placa;
	private String tipoflete;
	private String entrega;
	private String recibe;
	private Float fletetrans;
	private Float totaluni;
	private Float totalfc;
	private Float fpminimo;
	private String transporte;
	private String generar_orden;
	private Integer c_opportunity_id;
	private String vapor;
	private String almacenadora;
	private Float humedad;
	private Integer chargeamt;
	private String esentregalocal;
	private String esdeudor;
	private String escorreoenviado;
	private String escorreoenviadocyb;
	private String escorreoenviadop;
	private String isproforma;
	private Integer c_orderp_id;
	private CInvoice cinvoice;
	
	
	
	public CInvoice getC_invoice() {
		return cinvoice;
	}

	public void setC_invoice(CInvoice c_invoice) {
		this.cinvoice = c_invoice;
	}

	public Integer getC_orderp_id() {
		return c_orderp_id;
	}

	public void setC_orderp_id(Integer c_orderp_id) {
		this.c_orderp_id = c_orderp_id;
	}

	public String getIsproforma() {
		return isproforma;
	}

	public void setIsproforma(String isproforma) {
		this.isproforma = isproforma;
	}

	public Integer getC_order_id() {
		return c_order_id;
	}

	public void setC_order_id(Integer c_order_id) {
		this.c_order_id = c_order_id;
	}

	public Integer getAd_client_id() {
		return ad_client_id;
	}

	public void setAd_client_id(Integer ad_client_id) {
		this.ad_client_id = ad_client_id;
	}

	public Integer getAd_org_id() {
		return ad_org_id;
	}

	public void setAd_org_id(Integer ad_org_id) {
		this.ad_org_id = ad_org_id;
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

	public String getIssotrx() {
		return issotrx;
	}

	public void setIssotrx(String issotrx) {
		this.issotrx = issotrx;
	}

	public String getDocumentno() {
		return documentno;
	}

	public void setDocumentno(String documentno) {
		this.documentno = documentno;
	}

	public String getDocstatus() {
		return docstatus;
	}

	public void setDocstatus(String docstatus) {
		this.docstatus = docstatus;
	}

	public String getDocaction() {
		return docaction;
	}

	public void setDocaction(String docaction) {
		this.docaction = docaction;
	}

	public String getProcessing() {
		return processing;
	}

	public void setProcessing(String processing) {
		this.processing = processing;
	}

	public String getProcessed() {
		return processed;
	}

	public void setProcessed(String processed) {
		this.processed = processed;
	}

	public Integer getC_doctype_id() {
		return c_doctype_id;
	}

	public void setC_doctype_id(Integer c_doctype_id) {
		this.c_doctype_id = c_doctype_id;
	}

	public Integer getC_doctypetarget_id() {
		return c_doctypetarget_id;
	}

	public void setC_doctypetarget_id(Integer c_doctypetarget_id) {
		this.c_doctypetarget_id = c_doctypetarget_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsapproved() {
		return isapproved;
	}

	public void setIsapproved(String isapproved) {
		this.isapproved = isapproved;
	}

	public String getIscreditapproved() {
		return iscreditapproved;
	}

	public void setIscreditapproved(String iscreditapproved) {
		this.iscreditapproved = iscreditapproved;
	}

	public String getIsdelivered() {
		return isdelivered;
	}

	public void setIsdelivered(String isdelivered) {
		this.isdelivered = isdelivered;
	}

	public String getIsinvoiced() {
		return isinvoiced;
	}

	public void setIsinvoiced(String isinvoiced) {
		this.isinvoiced = isinvoiced;
	}

	public String getIsprinted() {
		return isprinted;
	}

	public void setIsprinted(String isprinted) {
		this.isprinted = isprinted;
	}

	public String getIstransferred() {
		return istransferred;
	}

	public void setIstransferred(String istransferred) {
		this.istransferred = istransferred;
	}

	public String getIsselected() {
		return isselected;
	}

	public void setIsselected(String isselected) {
		this.isselected = isselected;
	}

	public Integer getSalesrep_id() {
		return salesrep_id;
	}

	public void setSalesrep_id(Integer salesrep_id) {
		this.salesrep_id = salesrep_id;
	}

	public Timestamp getDateordered() {
		return dateordered;
	}

	public void setDateordered(Timestamp dateordered) {
		this.dateordered = dateordered;
	}

	public Timestamp getDatepromised() {
		return datepromised;
	}

	public void setDatepromised(Timestamp datepromised) {
		this.datepromised = datepromised;
	}

	public Timestamp getDateprinted() {
		return dateprinted;
	}

	public void setDateprinted(Timestamp dateprinted) {
		this.dateprinted = dateprinted;
	}

	public Timestamp getDateacct() {
		return dateacct;
	}

	public void setDateacct(Timestamp dateacct) {
		this.dateacct = dateacct;
	}

	public Integer getC_bpartner_id() {
		return c_bpartner_id;
	}

	public void setC_bpartner_id(Integer c_bpartner_id) {
		this.c_bpartner_id = c_bpartner_id;
	}

	public Integer getC_bpartner_location_id() {
		return c_bpartner_location_id;
	}

	public void setC_bpartner_location_id(Integer c_bpartner_location_id) {
		this.c_bpartner_location_id = c_bpartner_location_id;
	}

	public String getPoreference() {
		return poreference;
	}

	public void setPoreference(String poreference) {
		this.poreference = poreference;
	}

	public String getIsdiscountprinted() {
		return isdiscountprinted;
	}

	public void setIsdiscountprinted(String isdiscountprinted) {
		this.isdiscountprinted = isdiscountprinted;
	}

	public Integer getC_currency_id() {
		return c_currency_id;
	}

	public void setC_currency_id(Integer c_currency_id) {
		this.c_currency_id = c_currency_id;
	}

	public String getPaymentrule() {
		return paymentrule;
	}

	public void setPaymentrule(String paymentrule) {
		this.paymentrule = paymentrule;
	}

	public Integer getC_paymentterm_id() {
		return c_paymentterm_id;
	}

	public void setC_paymentterm_id(Integer c_paymentterm_id) {
		this.c_paymentterm_id = c_paymentterm_id;
	}

	public String getInvoicerule() {
		return invoicerule;
	}

	public void setInvoicerule(String invoicerule) {
		this.invoicerule = invoicerule;
	}

	public String getDeliveryrule() {
		return deliveryrule;
	}

	public void setDeliveryrule(String deliveryrule) {
		this.deliveryrule = deliveryrule;
	}

	public String getFreightcostrule() {
		return freightcostrule;
	}

	public void setFreightcostrule(String freightcostrule) {
		this.freightcostrule = freightcostrule;
	}

	public Integer getFreightamt() {
		return freightamt;
	}

	public void setFreightamt(Integer freightamt) {
		this.freightamt = freightamt;
	}

	public String getDeliveryviarule() {
		return deliveryviarule;
	}

	public void setDeliveryviarule(String deliveryviarule) {
		this.deliveryviarule = deliveryviarule;
	}

	public Integer getM_shipper_id() {
		return m_shipper_id;
	}

	public void setM_shipper_id(Integer m_shipper_id) {
		this.m_shipper_id = m_shipper_id;
	}

	public Integer getC_Stringge_id() {
		return c_Stringge_id;
	}

	public void setC_Stringge_id(Integer c_Stringge_id) {
		this.c_Stringge_id = c_Stringge_id;
	}

	public Integer getStringgeamt() {
		return Stringgeamt;
	}

	public void setStringgeamt(Integer Stringgeamt) {
		this.Stringgeamt = Stringgeamt;
	}

	public String getPriorityrule() {
		return priorityrule;
	}

	public void setPriorityrule(String priorityrule) {
		this.priorityrule = priorityrule;
	}

	public Float getTotallines() {
		return totallines;
	}

	public void setTotallines(Float totallines) {
		this.totallines = totallines;
	}

	public Float getGrandtotal() {
		return grandtotal;
	}

	public void setGrandtotal(Float grandtotal) {
		this.grandtotal = grandtotal;
	}

	public Integer getM_warehouse_id() {
		return m_warehouse_id;
	}

	public void setM_warehouse_id(Integer m_warehouse_id) {
		this.m_warehouse_id = m_warehouse_id;
	}

	public Integer getM_pricelist_id() {
		return m_pricelist_id;
	}

	public void setM_pricelist_id(Integer m_pricelist_id) {
		this.m_pricelist_id = m_pricelist_id;
	}

	public String getIstaxincluded() {
		return istaxincluded;
	}

	public void setIstaxincluded(String istaxincluded) {
		this.istaxincluded = istaxincluded;
	}

	public Integer getC_campaign_id() {
		return c_campaign_id;
	}

	public void setC_campaign_id(Integer c_campaign_id) {
		this.c_campaign_id = c_campaign_id;
	}

	public Integer getC_project_id() {
		return c_project_id;
	}

	public void setC_project_id(Integer c_project_id) {
		this.c_project_id = c_project_id;
	}

	public Integer getC_activity_id() {
		return c_activity_id;
	}

	public void setC_activity_id(Integer c_activity_id) {
		this.c_activity_id = c_activity_id;
	}

	public String getPosted() {
		return posted;
	}

	public void setPosted(String posted) {
		this.posted = posted;
	}

	public Integer getC_payment_id() {
		return c_payment_id;
	}

	public void setC_payment_id(Integer c_payment_id) {
		this.c_payment_id = c_payment_id;
	}

	public Integer getC_cashline_id() {
		return c_cashline_id;
	}

	public void setC_cashline_id(Integer c_cashline_id) {
		this.c_cashline_id = c_cashline_id;
	}

	public String getSendemail() {
		return sendemail;
	}

	public void setSendemail(String sendemail) {
		this.sendemail = sendemail;
	}

	public Integer getAd_user_id() {
		return ad_user_id;
	}

	public void setAd_user_id(Integer ad_user_id) {
		this.ad_user_id = ad_user_id;
	}

	public String getCopyfrom() {
		return copyfrom;
	}

	public void setCopyfrom(String copyfrom) {
		this.copyfrom = copyfrom;
	}

	public String getIsselfservice() {
		return isselfservice;
	}

	public void setIsselfservice(String isselfservice) {
		this.isselfservice = isselfservice;
	}

	public Integer getAd_orgtrx_id() {
		return ad_orgtrx_id;
	}

	public void setAd_orgtrx_id(Integer ad_orgtrx_id) {
		this.ad_orgtrx_id = ad_orgtrx_id;
	}

	public Integer getUser1_id() {
		return user1_id;
	}

	public void setUser1_id(Integer user1_id) {
		this.user1_id = user1_id;
	}

	public Integer getUser2_id() {
		return user2_id;
	}

	public void setUser2_id(Integer user2_id) {
		this.user2_id = user2_id;
	}

	public Integer getC_conversiontype_id() {
		return c_conversiontype_id;
	}

	public void setC_conversiontype_id(Integer c_conversiontype_id) {
		this.c_conversiontype_id = c_conversiontype_id;
	}

	public Integer getBill_bpartner_id() {
		return bill_bpartner_id;
	}

	public void setBill_bpartner_id(Integer bill_bpartner_id) {
		this.bill_bpartner_id = bill_bpartner_id;
	}

	public Integer getBill_location_id() {
		return bill_location_id;
	}

	public void setBill_location_id(Integer bill_location_id) {
		this.bill_location_id = bill_location_id;
	}

	public Integer getBill_user_id() {
		return bill_user_id;
	}

	public void setBill_user_id(Integer bill_user_id) {
		this.bill_user_id = bill_user_id;
	}

	public Integer getPay_bpartner_id() {
		return pay_bpartner_id;
	}

	public void setPay_bpartner_id(Integer pay_bpartner_id) {
		this.pay_bpartner_id = pay_bpartner_id;
	}

	public Integer getPay_location_id() {
		return pay_location_id;
	}

	public void setPay_location_id(Integer pay_location_id) {
		this.pay_location_id = pay_location_id;
	}

	public Integer getRef_order_id() {
		return ref_order_id;
	}

	public void setRef_order_id(Integer ref_order_id) {
		this.ref_order_id = ref_order_id;
	}

	public String getIsdropship() {
		return isdropship;
	}

	public void setIsdropship(String isdropship) {
		this.isdropship = isdropship;
	}

	public Float getVolume() {
		return volume;
	}

	public void setVolume(Float volume) {
		this.volume = volume;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public String getordertype() {
		return ordertype;
	}

	public void setordertype(String ordertype) {
		this.ordertype = ordertype;
	}

	public Integer getC_pos_id() {
		return c_pos_id;
	}

	public void setC_pos_id(Integer c_pos_id) {
		this.c_pos_id = c_pos_id;
	}

	public Float getAmounttendered() {
		return amounttendered;
	}

	public void setAmounttendered(Float amounttendered) {
		this.amounttendered = amounttendered;
	}

	public Float getAmountrefunded() {
		return amountrefunded;
	}

	public void setAmountrefunded(Float amountrefunded) {
		this.amountrefunded = amountrefunded;
	}

	public Integer getLink_order_id() {
		return link_order_id;
	}

	public void setLink_order_id(Integer link_order_id) {
		this.link_order_id = link_order_id;
	}

	public Integer getM_freightcategory_id() {
		return m_freightcategory_id;
	}

	public void setM_freightcategory_id(Integer m_freightcategory_id) {
		this.m_freightcategory_id = m_freightcategory_id;
	}

	public Integer getDropship_bpartner_id() {
		return dropship_bpartner_id;
	}

	public void setDropship_bpartner_id(Integer dropship_bpartner_id) {
		this.dropship_bpartner_id = dropship_bpartner_id;
	}

	public Integer getDropship_location_id() {
		return dropship_location_id;
	}

	public void setDropship_location_id(Integer dropship_location_id) {
		this.dropship_location_id = dropship_location_id;
	}

	public Integer getDropship_user_id() {
		return dropship_user_id;
	}

	public void setDropship_user_id(Integer dropship_user_id) {
		this.dropship_user_id = dropship_user_id;
	}

	public String getPromotioncode() {
		return promotioncode;
	}

	public void setPromotioncode(String promotioncode) {
		this.promotioncode = promotioncode;
	}

	public Integer getC_ordersource_id() {
		return c_ordersource_id;
	}

	public void setC_ordersource_id(Integer c_ordersource_id) {
		this.c_ordersource_id = c_ordersource_id;
	}

	public Float getProcessedon() {
		return processedon;
	}

	public void setProcessedon(Float processedon) {
		this.processedon = processedon;
	}

	public Integer getLco_taxpayertype_id() {
		return lco_taxpayertype_id;
	}

	public void setLco_taxpayertype_id(Integer lco_taxpayertype_id) {
		this.lco_taxpayertype_id = lco_taxpayertype_id;
	}

	public Integer getV_liability_acct() {
		return v_liability_acct;
	}

	public void setV_liability_acct(Integer v_liability_acct) {
		this.v_liability_acct = v_liability_acct;
	}

	public String getConvertir_cotizacion() {
		return convertir_cotizacion;
	}

	public void setConvertir_cotizacion(String convertir_cotizacion) {
		this.convertir_cotizacion = convertir_cotizacion;
	}

	public String getGenerar_factura() {
		return generar_factura;
	}

	public void setGenerar_factura(String generar_factura) {
		this.generar_factura = generar_factura;
	}

	public String getContribuyente() {
		return contribuyente;
	}

	public void setContribuyente(String contribuyente) {
		this.contribuyente = contribuyente;
	}

	public String getPuestoen() {
		return puestoen;
	}

	public void setPuestoen(String puestoen) {
		this.puestoen = puestoen;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getTipoflete() {
		return tipoflete;
	}

	public void setTipoflete(String tipoflete) {
		this.tipoflete = tipoflete;
	}

	public String getEntrega() {
		return entrega;
	}

	public void setEntrega(String entrega) {
		this.entrega = entrega;
	}

	public String getRecibe() {
		return recibe;
	}

	public void setRecibe(String recibe) {
		this.recibe = recibe;
	}

	public Float getFletetrans() {
		return fletetrans;
	}

	public void setFletetrans(Float fletetrans) {
		this.fletetrans = fletetrans;
	}

	public Float getTotaluni() {
		return totaluni;
	}

	public void setTotaluni(Float totaluni) {
		this.totaluni = totaluni;
	}

	public Float getTotalfc() {
		return totalfc;
	}

	public void setTotalfc(Float totalfc) {
		this.totalfc = totalfc;
	}

	public Float getFpminimo() {
		return fpminimo;
	}

	public void setFpminimo(Float fpminimo) {
		this.fpminimo = fpminimo;
	}

	public String getTransporte() {
		return transporte;
	}

	public void setTransporte(String transporte) {
		this.transporte = transporte;
	}

	public String getGenerar_orden() {
		return generar_orden;
	}

	public void setGenerar_orden(String generar_orden) {
		this.generar_orden = generar_orden;
	}

	public Integer getC_opportunity_id() {
		return c_opportunity_id;
	}

	public void setC_opportunity_id(Integer c_opportunity_id) {
		this.c_opportunity_id = c_opportunity_id;
	}

	public String getVapor() {
		return vapor;
	}

	public void setVapor(String vapor) {
		this.vapor = vapor;
	}

	public String getAlmacenadora() {
		return almacenadora;
	}

	public void setAlmacenadora(String almacenadora) {
		this.almacenadora = almacenadora;
	}

	public Float getHumedad() {
		return humedad;
	}

	public void setHumedad(Float humedad) {
		this.humedad = humedad;
	}

	public Integer getC_charge_id() {
		return c_charge_id;
	}

	public void setC_charge_id(Integer c_charge_id) {
		this.c_charge_id = c_charge_id;
	}

	public String getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}

	public Integer getChargeamt() {
		return chargeamt;
	}

	public void setChargeamt(Integer chargeamt) {
		this.chargeamt = chargeamt;
	}

	public String getEsentregalocal() {
		return esentregalocal;
	}

	public void setEsentregalocal(String esentregalocal) {
		this.esentregalocal = esentregalocal;
	}

	public String getEsdeudor() {
		return esdeudor;
	}

	public void setEsdeudor(String esdeudor) {
		this.esdeudor = esdeudor;
	}
	
	public String getEscorreoenviado() {
		if (escorreoenviado==null) return "N";
		return escorreoenviado;
	}

	public void setEscorreoenviado(String escorreoenviado) {
		this.escorreoenviado = escorreoenviado;
	}

	public String getEscorreoenviadocyb() {
		if (escorreoenviadocyb==null) return "N";
		return escorreoenviadocyb;
	}

	public void setEscorreoenviadocyb(String escorreoenviadocyb) {
		this.escorreoenviadocyb = escorreoenviadocyb;
	}
	public String getEscorreoenviadop() {
		if (escorreoenviadop==null) return "N";
		return escorreoenviadop;
	}

	public void setEscorreoenviadop(String escorreoenviadop) {
		this.escorreoenviadop = escorreoenviadop;
	}
}
