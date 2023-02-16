package org.saram.modelo;

import java.sql.Timestamp;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class COrderLine {
	private Integer c_orderline_id;
	private Integer ad_client_id;
	private Integer ad_org_id;
	private String isactive;
	private Timestamp created;
	private Integer createdby;
	private Timestamp updated;
	private Integer updatedby;
	private Integer c_order_id;
	private Integer line;
	private Integer c_bpartner_id;
	private Integer c_bpartner_location_id;
	private Timestamp dateordered;
	private Timestamp datepromised;
	private Timestamp datedelivered;
	private Timestamp dateinvoiced;
	private String description;
	private Integer m_product_id;
	private Integer m_warehouse_id;
	private Integer c_uom_id;
	private Float qtyordered;
	private Float qtypendiente;
	private Float qtyreserved;
	private Float qtydelivered;
	private Float qtyinvoiced;
	private Integer m_shipper_id;
	private Integer c_currency_id;
	private Float pricelist;
	private Float priceactual;
	private Float pricelimit;
	private Float linenetamt;
	private Float discount;
	private Float freightamt;
	private Integer c_Stringge_id;
	private Integer c_tax_id;
	private Integer s_resourceassignment_id;
	private Integer ref_orderline_id;
	private Integer m_attributesetinstance_id;
	private String isdescription;
	private String processed;
	private Float qtyentered;
	private Float priceentered;
	private Integer c_project_id;
	private Float pricecost;
	private Float qtylostsales;
	private Integer c_projectphase_id;
	private Integer c_projecttask_id;
	private Timestamp rrstartdate;
	private Float rramt;
	private Integer c_campaign_id;
	private Integer c_activity_id;
	private Integer user1_id;
	private Integer user2_id;
	private Integer ad_orgtrx_id;
	private Integer link_orderline_id;
	private Integer pp_cost_collector_id;
	private Integer m_promotion_id;
	private String destino;
	private String factura;
	private String recibo;
	private Float totalunidades;
	private String isconsumesforecast;
	private String createfrom;
	private String createshipment;
	private Integer c_charge_id;
	private String espromocion;
	private String esgeneradoauto;
    private Integer c_orderline_id_discount;
	private String withpromo;
	private String ispromomix;

	public Integer getC_orderline_id_discount() {
		return c_orderline_id_discount;
	}

	public void setC_orderline_id_discount(Integer c_orderline_id_discount) {
		this.c_orderline_id_discount = c_orderline_id_discount;
	}

	public COrder getCOrder() {
		return COrder;
	}

	public void setCOrder(COrder cOrder) {
		COrder = cOrder;
	}

	@ManyToOne
	@JoinColumn(name="c_order_id")
	private COrder COrder;
	
	public Integer getC_charge_id() {
		return c_charge_id;
	}

	public void setC_charge_id(Integer c_charge_id) {
		this.c_charge_id = c_charge_id;
	}

	public Integer getC_orderline_id() {
		return c_orderline_id;
	}

	public void setC_orderline_id(Integer c_orderline_id) {
		this.c_orderline_id = c_orderline_id;
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

	public Integer getC_order_id() {
		return c_order_id;
	}

	public void setC_order_id(Integer c_order_id) {
		this.c_order_id = c_order_id;
	}

	public Integer getLine() {
		return line;
	}

	public void setLine(Integer line) {
		this.line = line;
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

	public Timestamp getDatedelivered() {
		return datedelivered;
	}

	public void setDatedelivered(Timestamp datedelivered) {
		this.datedelivered = datedelivered;
	}

	public Timestamp getDateinvoiced() {
		return dateinvoiced;
	}

	public void setDateinvoiced(Timestamp dateinvoiced) {
		this.dateinvoiced = dateinvoiced;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getM_product_id() {
		if (m_product_id!=null) return m_product_id;
		else return null;
	}

	public void setM_product_id(Integer m_product_id) {
		this.m_product_id = m_product_id;
	}

	public Integer getM_warehouse_id() {
		return m_warehouse_id;
	}

	public void setM_warehouse_id(Integer m_warehouse_id) {
		this.m_warehouse_id = m_warehouse_id;
	}

	public Integer getC_uom_id() {
		return c_uom_id;
	}

	public void setC_uom_id(Integer c_uom_id) {
		this.c_uom_id = c_uom_id;
	}

	public Float getQtyordered() {
		return qtyordered;
	}

	public void setQtyordered(Float qtyordered) {
		this.qtyordered = qtyordered;
	}

	public Float getQtyreserved() {
		return qtyreserved;
	}

	public void setQtyreserved(Float qtyreserved) {
		this.qtyreserved = qtyreserved;
	}

	public Float getQtydelivered() {
		return qtydelivered;
	}

	public void setQtydelivered(Float qtydelivered) {
		this.qtydelivered = qtydelivered;
	}

	public Float getQtyinvoiced() {
		return qtyinvoiced;
	}

	public void setQtyinvoiced(Float qtyinvoiced) {
		this.qtyinvoiced = qtyinvoiced;
	}

	public Integer getM_shipper_id() {
		return m_shipper_id;
	}

	public void setM_shipper_id(Integer m_shipper_id) {
		this.m_shipper_id = m_shipper_id;
	}

	public Integer getC_currency_id() {
		return c_currency_id;
	}

	public void setC_currency_id(Integer c_currency_id) {
		this.c_currency_id = c_currency_id;
	}

	public Float getPricelist() {
		return pricelist;
	}

	public void setPricelist(Float pricelist) {
		this.pricelist = pricelist;
	}

	public Float getPriceactual() {
		return priceactual;
	}

	public void setPriceactual(Float priceactual) {
		this.priceactual = priceactual;
	}

	public Float getPricelimit() {
		return pricelimit;
	}

	public void setPricelimit(Float pricelimit) {
		this.pricelimit = pricelimit;
	}

	public Float getLinenetamt() {
		return linenetamt;
	}

	public void setLinenetamt(Float linenetamt) {
		this.linenetamt = linenetamt;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public Float getFreightamt() {
		return freightamt;
	}

	public void setFreightamt(Float freightamt) {
		this.freightamt = freightamt;
	}

	public Integer getC_Stringge_id() {
		return c_Stringge_id;
	}

	public void setC_Stringge_id(Integer c_Stringge_id) {
		this.c_Stringge_id = c_Stringge_id;
	}

	public Integer getC_tax_id() {
		return c_tax_id;
	}

	public void setC_tax_id(Integer c_tax_id) {
		this.c_tax_id = c_tax_id;
	}

	public Integer getS_resourceassignment_id() {
		return s_resourceassignment_id;
	}

	public void setS_resourceassignment_id(Integer s_resourceassignment_id) {
		this.s_resourceassignment_id = s_resourceassignment_id;
	}

	public Integer getRef_orderline_id() {
		return ref_orderline_id;
	}

	public void setRef_orderline_id(Integer ref_orderline_id) {
		this.ref_orderline_id = ref_orderline_id;
	}

	public Integer getM_attributesetinstance_id() {
		return m_attributesetinstance_id;
	}

	public void setM_attributesetinstance_id(Integer m_attributesetinstance_id) {
		this.m_attributesetinstance_id = m_attributesetinstance_id;
	}

	public String getIsdescription() {
		return isdescription;
	}

	public void setIsdescription(String isdescription) {
		this.isdescription = isdescription;
	}

	public String getProcessed() {
		return processed;
	}

	public void setProcessed(String processed) {
		this.processed = processed;
	}

	public Float getQtyentered() {
		return qtyentered;
	}

	public void setQtyentered(Float qtyentered) {
		this.qtyentered = qtyentered;
	}

	public Float getPriceentered() {
		return priceentered;
	}

	public void setPriceentered(Float priceentered) {
		this.priceentered = priceentered;
	}

	public Integer getC_project_id() {
		return c_project_id;
	}

	public void setC_project_id(Integer c_project_id) {
		this.c_project_id = c_project_id;
	}

	public Float getPricecost() {
		return pricecost;
	}

	public void setPricecost(Float pricecost) {
		this.pricecost = pricecost;
	}

	public Float getQtylostsales() {
		return qtylostsales;
	}

	public void setQtylostsales(Float qtylostsales) {
		this.qtylostsales = qtylostsales;
	}

	public Integer getC_projectphase_id() {
		return c_projectphase_id;
	}

	public void setC_projectphase_id(Integer c_projectphase_id) {
		this.c_projectphase_id = c_projectphase_id;
	}

	public Integer getC_projecttask_id() {
		return c_projecttask_id;
	}

	public void setC_projecttask_id(Integer c_projecttask_id) {
		this.c_projecttask_id = c_projecttask_id;
	}

	public Timestamp getRrstartdate() {
		return rrstartdate;
	}

	public void setRrstartdate(Timestamp rrstartdate) {
		this.rrstartdate = rrstartdate;
	}

	public Float getRramt() {
		return rramt;
	}

	public void setRramt(Float rramt) {
		this.rramt = rramt;
	}

	public Integer getC_campaign_id() {
		return c_campaign_id;
	}

	public void setC_campaign_id(Integer c_campaign_id) {
		this.c_campaign_id = c_campaign_id;
	}

	public Integer getC_activity_id() {
		return c_activity_id;
	}

	public void setC_activity_id(Integer c_activity_id) {
		this.c_activity_id = c_activity_id;
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

	public Integer getAd_orgtrx_id() {
		return ad_orgtrx_id;
	}

	public void setAd_orgtrx_id(Integer ad_orgtrx_id) {
		this.ad_orgtrx_id = ad_orgtrx_id;
	}

	public Integer getLink_orderline_id() {
		return link_orderline_id;
	}

	public void setLink_orderline_id(Integer link_orderline_id) {
		this.link_orderline_id = link_orderline_id;
	}

	public Integer getPp_cost_collector_id() {
		return pp_cost_collector_id;
	}

	public void setPp_cost_collector_id(Integer pp_cost_collector_id) {
		this.pp_cost_collector_id = pp_cost_collector_id;
	}

	public Integer getM_promotion_id() {
		return m_promotion_id;
	}

	public void setM_promotion_id(Integer m_promotion_id) {
		this.m_promotion_id = m_promotion_id;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getFactura() {
		return factura;
	}

	public void setFactura(String factura) {
		this.factura = factura;
	}

	public String getRecibo() {
		return recibo;
	}

	public void setRecibo(String recibo) {
		this.recibo = recibo;
	}

	public Float getTotalunidades() {
		return totalunidades;
	}

	public void setTotalunidades(Float totalunidades) {
		this.totalunidades = totalunidades;
	}

	public String getIsconsumesforecast() {
		return isconsumesforecast;
	}

	public void setIsconsumesforecast(String isconsumesforecast) {
		this.isconsumesforecast = isconsumesforecast;
	}

	public String getCreatefrom() {
		return createfrom;
	}

	public void setCreatefrom(String createfrom) {
		this.createfrom = createfrom;
	}

	public String getCreateshipment() {
		return createshipment;
	}

	public void setCreateshipment(String createshipment) {
		this.createshipment = createshipment;
	}

	public String getEspromocion() {
		return espromocion;
	}

	public void setEspromocion(String espromocion) {
		this.espromocion = espromocion;
	}

	public Float getQtypendiente() {
		return qtypendiente;
	}

	public void setQtypendiente(Float qtypendiente) {
		this.qtypendiente = qtypendiente;
	}

	public String getEsgeneradoauto() {
		return esgeneradoauto;
	}

	public void setEsgeneradoauto(String esgeneradoauto) {
		this.esgeneradoauto = esgeneradoauto;
	}

	public String getWithpromo() {
		return withpromo;
	}

	public void setWithpromo(String withpromo) {
		this.withpromo = withpromo;
	}

	public String getIspromomix() {
		return ispromomix;
	}

	public void setIspromomix(String ispromomix) {
		this.ispromomix = ispromomix;
	}
	
	

}
