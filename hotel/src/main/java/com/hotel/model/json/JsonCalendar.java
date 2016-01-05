package com.hotel.model.json;

import java.io.Serializable;

public class JsonCalendar implements Serializable {

	// "available":0,"bind":0,"info":"","notes":"","price":0,"promo":0,"status":"booked"

	/**
	 * 
	 */
	private static final long serialVersionUID = 91900161785735674L;
	private String available;
	private Integer bind;
	private String info;
	private String notes;
	private String price;
	private String promo;
	private String status;

	public JsonCalendar() {
		super();
		this.available = "1";
		this.bind = 0;
		this.info = "fechou a info";
		this.notes = "notas do caroco";
		this.price = "20,0";
		this.promo = "";
		this.status = "available";
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(final String available) {
		this.available = available;
	}

	public Integer getBind() {
		return bind;
	}

	public void setBind(final Integer bind) {
		this.bind = bind;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(final String info) {
		this.info = info;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(final String notes) {
		this.notes = notes;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(final String price) {
		this.price = price;
	}

	public String getPromo() {
		return promo;
	}

	public void setPromo(final String promo) {
		this.promo = promo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

}
