package org.saram.accesos;

import java.util.List;

import org.saram.modelo.CBPartner;
import org.saram.modelo.MProduct;

public interface MProduct_I {
	public List<MProduct> buscarTodos(MProduct mp);
	/**
	 * Sin implementar
	 * **/
	public Long salvar(MProduct mp);
	public MProduct buscarUno(Integer id);
	public List<MProduct> buscarTodosPorCliente(MProduct mp, Integer cb);
	public List<MProduct> buscarTodosPorClientePecuario(MProduct mp, Integer cb);
	public List<MProduct> buscarTodosPorClienteExtranjero(MProduct mp, Integer cb);
	public List<MProduct> buscarTodosPorClienteMascota(MProduct mp, Integer cb);
	public List<MProduct> buscarTodosPorClienteTrotemor(MProduct mp, Integer cb);
	public List<MProduct> buscarTodosPorClienteMateriaPrima(MProduct mp, Integer cb);
	public List<MProduct> buscarTodosPorClienteHuevo(MProduct mp, Integer cb);
	public List<MProduct> buscarTodosPorClienteInsumo(MProduct mp, Integer cb);
	public List<MProduct> buscarTodosPorClienteProductoProceso(MProduct mp, Integer cb);

	public boolean buscarProductoEsMascota(Integer cb);
	public boolean buscarProductoMP(Integer cb);
}
