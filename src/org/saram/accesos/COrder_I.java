package org.saram.accesos;

import java.util.List;

import org.saram.modelo.AdUser;
import org.saram.modelo.COrder;


public interface COrder_I {
	public List<COrder> buscarTodos(AdUser ad);
//	public List<COrder> buscarTodosPorCliente(Integer CBPartnerID);
	public Long salvarOrden(COrder co);
	public Integer buscarMaximo();
	public COrder buscarUno(Integer id);
	public List<COrder> buscarTodosProcesados(AdUser ad);
	public List<COrder> buscarTodosDetenidos(AdUser ad);
	public List<COrder> buscarUnoDetenido(String adId, String coID);
	public List<COrder> buscarTodosEnSaram(AdUser ad);
	public List<COrder> buscarTodosEnSaramPrePedidos(AdUser ad);
	public List<COrder> buscarTodosPorFacturar(AdUser ad);
	
	public List<COrder> buscarTodosConProforma(AdUser ad);
	public List<COrder> buscarProformas(String adId, String coID);
	public List<COrder> buscarProformasconfacturas(AdUser ad);
	public List<COrder> buscarConFacturas(AdUser ad);
	public List<COrder> buscarTodosConProformaSinFactura(AdUser ad);


}
