package com.hotel.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the alert database table.
 * 
 */
@Entity
@Table(name = "alert")
@NamedQuery(name = "Alert.findAll", query = "SELECT a FROM Alert a")
public class Alert implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ALERT_ID_GENERATOR")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ALERT_ID_GENERATOR")
	@Column(unique = true, nullable = false)
	private Integer id;

	@Column(length = 255)
	private String desc;

	@Column(length = 255)
	private String type;

	// bi-directional many-to-many association to ReservationRoom
	@ManyToMany
	@JoinTable(name = "alert_reservation_room", joinColumns = {
			@JoinColumn(name = "alertid", nullable = false)
	}, inverseJoinColumns = {
			@JoinColumn(name = "reservation_roomreservationid", referencedColumnName = "reservationid", nullable = false),
			@JoinColumn(name = "reservation_roomroomid", referencedColumnName = "roomid", nullable = false)
	})
	private List<ReservationRoom> reservationRooms;

	public Alert() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(final String desc) {
		this.desc = desc;
	}

	public String getType() {
		return this.type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public List<ReservationRoom> getReservationRooms() {
		return this.reservationRooms;
	}

	public void setReservationRooms(final List<ReservationRoom> reservationRooms) {
		this.reservationRooms = reservationRooms;
	}

}