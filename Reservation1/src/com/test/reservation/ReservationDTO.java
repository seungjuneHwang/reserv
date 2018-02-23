package com.test.reservation;

public class ReservationDTO {
	private String ch;
	private int isch;
	private int idx;
	
	@Override
	public String toString() {
		return "ReservationDTO [b_idx=" + idx + "isch=" + isch + "ch=" + ch + "]";
	}
	
	public int getIsch() {
		return isch;
	}

	public void setIsch(int isch) {
		this.isch = isch;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
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
