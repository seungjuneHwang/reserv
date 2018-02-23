package com.test.reservation;

public class ReservationDTO {
	private String ch;
	private int isch;
	private int b_idx;
	
	@Override
	public String toString() {
		return "ReservationDTO [b_idx=" + b_idx + "isch=" + isch + "ch=" + ch + "]";
	}
	
	public int getIsch() {
		return isch;
	}

	public void setIsch(int isch) {
		this.isch = isch;
	}

	public int getB_idx() {
		return b_idx;
	}

	public void setB_idx(int b_idx) {
		this.b_idx = b_idx;
	}



	public String getCh() {
		return ch;
	}

	public void setCh(String ch) {
		this.ch = ch;
	}

	public ReservationDTO(String ch) {
		super();
		this.ch = ch;
	}

	public ReservationDTO() {
		super();
		
	}
	
}
