package org.saram.modelo;

public class AdSequence {
	private Integer ad_sequence_id;
	private Integer currentnext;
	private Integer currentnextsys;
	
	public Integer getCurrentnextsys() {
		return currentnextsys;
	}
	public void setCurrentnextsys(Integer currentnextsys) {
		this.currentnextsys = currentnextsys;
	}
	public Integer getAd_sequence_id() {
		return ad_sequence_id;
	}
	public void setAd_sequence_id(Integer ad_sequence_id) {
		this.ad_sequence_id = ad_sequence_id;
	}
	public Integer getCurrentnext() {
		return currentnext;
	}
	public void setCurrentnext(Integer currentnext) {
		this.currentnext = currentnext;
	}
}
