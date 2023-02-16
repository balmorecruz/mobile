package org.saram.accesos;

import java.util.List;

import org.saram.modelo.CCharge;


public interface CCharge_I {
	public List<CCharge> buscarTodos(CCharge ad);
	public List<CCharge> buscarUno(String name);
	public List<CCharge> buscarUnoByID(String id);
	public CCharge buscarUnoByCity(Integer id);
	public CCharge buscarUnoByCityTax(Integer city, String Tax);
	public Long salvar(CCharge cbp);
}
