package com.hotel.common.model;

public enum UserType {
	ADM("admin"), User("user");

	private final String label;

	private UserType(final String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}

}
