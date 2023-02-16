package org.saram.accesos;

import java.util.List;

import org.saram.modelo.MPromocionLine;

public interface MPromocionLine_I {
	public MPromocionLine buscarTodos(Integer mpromoid);
	public List<MPromocionLine> buscarLineasPromocion(Integer mpromoid);

}
