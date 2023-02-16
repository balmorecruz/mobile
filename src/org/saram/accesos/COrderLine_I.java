package org.saram.accesos;

import java.util.List;

import org.saram.modelo.COrderLine;


public interface COrderLine_I {
	public List<COrderLine> buscarTodos(Integer id);
	public COrderLine buscarUno(Integer id);
	public COrderLine buscarQuotaAve(Integer id, Integer cc);
	public Integer buscarTieneFlete(Integer id);
	public Long salvarOrden(COrderLine col);
	public Long eliminarPromosEscalaMix(Integer COrderID);
	public Integer buscarMaximo();
	public Long eliminarTodos(Integer COrderID);
	public Long eliminarUno(Integer id);
	public Float sumarTodoQTY(Integer co);
	public Float sumarTodoMonto(Integer co);
	public Float sumarTodoMontoGrandTotal(Integer co);
	public Float sumarTodoMontoGrandTotalSinAve(Integer co);
	public COrderLine buscarPromocion(Integer mp, Integer co);
	public List<COrderLine> buscarlineasPromo(Integer mp, Integer co);
	public Boolean buscarProductoIngresado(Integer mp, Integer co);
	public COrderLine buscarLineaDescuentoFlete(Integer id, Integer cc);
	public List<COrderLine> buscarlineasPromoEscala(Integer corderid);
    public Double getSumProductosEscala(Integer c_order_id);
    public List<COrderLine>buscarlineaPromoMix(Integer c_order_id);
    public Long eliminarPromosEscala(Integer c_order_id);
    public Long eliminarLineasDescuentoFlete(Integer c_order_id);
}
