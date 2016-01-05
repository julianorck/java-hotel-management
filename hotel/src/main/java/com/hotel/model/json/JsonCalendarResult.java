package com.hotel.model.json;

import java.io.Serializable;

public class JsonCalendarResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8564250249142929709L;
	private String check_in;
	private String check_out;

	public String getCheck_in() {
		return check_in;
	}

	public void setCheck_in(final String check_in) {
		this.check_in = check_in;
	}

	public String getCheck_out() {
		return check_out;
	}

	public void setCheck_out(final String check_out) {
		this.check_out = check_out;
	}

}
