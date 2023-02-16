package org.saram.accesos;

import java.util.List;

import org.saram.modelo.AdUser;
import org.saram.modelo.MPriceList;


public interface MPriceList_I {
	public List<MPriceList> buscarTodos(AdUser ad);
	public MPriceList buscarUno(Integer MPriceListID);
}
