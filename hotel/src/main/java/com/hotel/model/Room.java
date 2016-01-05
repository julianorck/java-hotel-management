package com.hotel.model;

import java.io.Serializable;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the room database table.
 * 
 */
@Entity
@Table(name = "room")
public class Room implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ROOM_ID_GENERATOR", sequenceName = "room_id_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROOM_ID_GENERATOR")
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(name = "room_number")
	private Integer roomNumber;

	@Column(length = 1)
	private String occupied;

	// bi-directional many-to-one association to RoomType
	@ManyToOne
	@JoinColumn(name = "room_typeid", nullable = false)
	private RoomType roomType;

	public Room() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Integer getRoomNumber() {
		return this.roomNumber;
	}

	public void setRoomNumber(final Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getOccupied() {
		return this.occupied;
	}

	public void setOccupied(final String occupied) {
		this.occupied = occupied;
	}

	public RoomType getRoomType() {
		return this.roomType;
	}

	public void setRoomType(final RoomType roomType) {
		this.roomType = roomType;
	}

	public String getFormatedPrice() {
		return String.format(new Locale("pt", "BR"), "%.2f", getRoomType().getRate());
	}

}