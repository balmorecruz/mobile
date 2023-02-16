package org.saram.accesos;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		COrderLine_X daoOrderline=new COrderLine_X();
		daoOrderline.buscarlineasPromoEscala(1262771).forEach(x->{
			System.out.println(x.getQtyordered());
		});; ;
	}

}
