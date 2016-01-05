package com.hotel.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the reservation_room database table.
 * 
 */
@Entity
@Table(name = "reservation_room")
@NamedQuery(name = "ReservationRoom.findAll", query = "SELECT r FROM ReservationRoom r")
public class ReservationRoom implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ReservationRoomPK id;

	@Column(nullable = false)
	private Integer alertid;

	// bi-directional many-to-many association to Alert
	@ManyToMany(mappedBy = "reservationRooms")
	private List<Alert> alerts;

	public ReservationRoom() {
	}

	public ReservationRoomPK getId() {
		return this.id;
	}

	public void setId(final ReservationRoomPK id) {
		this.id = id;
	}

	public Integer getAlertid() {
		return this.alertid;
	}

	public void setAlertid(final Integer alertid) {
		this.alertid = alertid;
	}

	public List<Alert> getAlerts() {
		return this.alerts;
	}

	public void setAlerts(final List<Alert> alerts) {
		this.alerts = alerts;
	}

}