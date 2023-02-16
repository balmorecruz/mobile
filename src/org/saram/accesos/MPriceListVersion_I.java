package org.saram.accesos;

import java.util.List;

import org.saram.modelo.MPriceList;
import org.saram.modelo.MPriceListVersion;


public interface MPriceListVersion_I {
	public List<MPriceListVersion> buscarTodos(MPriceList mpl);
	public Long salvar(MPriceListVersion mpl);
}
