package org.saram.accesos;

import java.util.List;

import org.saram.modelo.COrderLine;
import org.saram.modelo.MPromocion;

public interface MPromocion_I {
	public MPromocion buscarTodos(Integer mp, Integer cb);
	public COrderLine buscarPromocionNoIngresada(Integer mp, Integer cb);
	public MPromocion buscarUno(Integer mp);
	public MPromocion buscarPorCliente(Integer mp,Integer cb);

}
