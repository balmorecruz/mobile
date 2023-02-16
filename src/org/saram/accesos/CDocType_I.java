package org.saram.accesos;

import java.util.List;

import org.saram.modelo.CDocType;


public interface CDocType_I {
	public List<CDocType> buscarTodos(CDocType cd);
	public Long salvar(CDocType cp);
}
