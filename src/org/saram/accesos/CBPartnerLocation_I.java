package org.saram.accesos;

import java.util.List;

import org.saram.modelo.CBPartnerLocation;


public interface CBPartnerLocation_I {
	public List<CBPartnerLocation> buscarTodos(Integer ad);
	public Long salvar(CBPartnerLocation cbp);
	public CBPartnerLocation buscarUno(Integer cbl);
}
