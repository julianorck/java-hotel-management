package com.hotel.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;

import com.hotel.exception.GuestNotFoundException;
import com.hotel.model.Room;
import com.hotel.repository.RoomRepository;

public class RoomServices {
	@Inject
	RoomRepository roomRepository;

	public Room add(final Room room) {
		return roomRepository.add(room);
	}

	public void update(final Room room) {

		if (!roomRepository.existsById(room.getId())) {
			throw new GuestNotFoundException();
		}

		roomRepository.update(room);
	}

	public Room findById(final Long id) {
		final Room room = roomRepository.findById(id);
		if (room == null) {
			throw new GuestNotFoundException();
		}
		return room;
	}

	public List<Room> fyndAll() {
		return roomRepository.findAll("roomNumber");
	}

	public void remove(final Room room) {
		roomRepository.remove(room, room.getId());
	}

	public List<Room> findAvailableRooms() {
		return roomRepository.findAvailableRooms();
	}

	public List<Room> findRoomsByDate(final LocalDateTime startDate, final LocalDateTime endDate) {
		/*
		 * final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		 * final LocalDateTime lStartDate = LocalDateTime.parse(startDate, DATE_FORMAT);
		 * final LocalDateTime lEndDate = LocalDateTime.parse(endDate, DATE_FORMAT);
		 */
		return roomRepository.findAvailableRoomsByDate(startDate, endDate);
	}

	public Room refresh(final Room room, final Long roomId) {
		return roomRepository.refresh(room, roomId);
	}

}
