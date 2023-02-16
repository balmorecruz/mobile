package org.saram.accesos;

import java.util.List;

import org.saram.modelo.AdUser;


public interface AdUser_I {
	public int buscarUno(AdUser ad);
	public AdUser buscarUno(int ad);
	public List<AdUser> buscarTodos();
}
