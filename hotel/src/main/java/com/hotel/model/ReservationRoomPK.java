package com.hotel.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the reservation_room database table.
 * 
 */
@Embeddable
public class ReservationRoomPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable = false, updatable = false, unique = true, nullable = false)
	private Integer reservationid;

	@Column(insertable = false, updatable = false, unique = true, nullable = false)
	private Integer roomid;

	public ReservationRoomPK() {
	}

	public Integer getReservationid() {
		return this.reservationid;
	}

	public void setReservationid(final Integer reservationid) {
		this.reservationid = reservationid;
	}

	public Integer getRoomid() {
		return this.roomid;
	}

	public void setRoomid(final Integer roomid) {
		this.roomid = roomid;
	}

	@Override
	public boolean equals(final Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ReservationRoomPK)) {
			return false;
		}
		final ReservationRoomPK castOther = (ReservationRoomPK) other;
		return this.reservationid.equals(castOther.reservationid)
				&& this.roomid.equals(castOther.roomid);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.reservationid.hashCode();
		hash = hash * prime + this.roomid.hashCode();

		return hash;
	}
}