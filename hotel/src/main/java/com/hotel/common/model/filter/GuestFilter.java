package com.hotel.common.model.filter;

public class GuestFilter extends GenericFilter {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "GuestFilter [name=" + name + ", toString()=" + super.toString() + "]";
	}

}