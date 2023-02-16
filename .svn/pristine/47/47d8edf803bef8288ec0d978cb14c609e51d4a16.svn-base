package org.saram.busquedas;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class utilerias {

	public Float decimalRedondeo(double d) {
		Float finalValue = new Float(0.0);
		if (d!=0.0){
//			DecimalFormat def=new DecimalFormat("0.00");
//			String formate = "";
//			formate = def.format(d);
//			finalValue = new Float(formate);
			BigDecimal big = new BigDecimal(d);
		    big = big.setScale(2, RoundingMode.HALF_UP);
		    finalValue = new Float(big.floatValue());
		}
		return finalValue;
	}

	public String decimalMiles(Float n){
		DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
	    simbolo.setDecimalSeparator('.');
	    simbolo.setGroupingSeparator(',');
	    DecimalFormat formateador = new DecimalFormat("###,###,###.##",simbolo);
	    String nu = formateador.format(n);
		return nu;
	}
}
