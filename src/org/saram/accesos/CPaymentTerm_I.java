package org.saram.accesos;

import java.util.List;

import org.saram.modelo.CPaymentTerm;


public interface CPaymentTerm_I {
	public List<CPaymentTerm> buscarTodos(CPaymentTerm cpt);
	public Long salvar(CPaymentTerm cbp);
}
