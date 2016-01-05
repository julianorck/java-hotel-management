package com.hotel.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the room_type database table.
 * 
 */
@Entity
@Table(name = "room_type")
public class RoomType implements Serializable {

	private static final long serialVersionUID = 7298794450617101966L;

	@Id
	@SequenceGenerator(name = "ROOM_TYPE_ID_GENERATOR", sequenceName = "room_type_id_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROOM_TYPE_ID_GENERATOR")
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(length = 600)
	private String description;

	@Column(length = 255)
	private String type;

	private Integer occupancy;

	private float rate;

	// bi-directional many-to-one association to Room
	@OneToMany(mappedBy = "roomType")
	private List<Room> rooms;

	public RoomType() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Integer getOccupancy() {
		return this.occupancy;
	}

	public void setOccupancy(final Integer occupancy) {
		this.occupancy = occupancy;
	}

	public float getRate() {
		return this.rate;
	}

	public void setRate(final float rate) {
		this.rate = rate;
	}

	public List<Room> getRooms() {
		return this.rooms;
	}

	public void setRooms(final List<Room> rooms) {
		this.rooms = rooms;
	}

	public Room addRoom(final Room room) {
		getRooms().add(room);
		room.setRoomType(this);

		return room;
	}

	public Room removeRoom(final Room room) {
		getRooms().remove(room);
		room.setRoomType(null);

		return room;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}