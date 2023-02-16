package org.saram.accesos;

import java.util.List;

import org.saram.modelo.COrder;

public interface sqlHQL_I {
	public List<Object> buscarUno(String sql);
	public int sql(String sql);
	public int sqlInsert(String sql);
	public List<COrder> sqlIds(String sql);
}
