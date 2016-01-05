package com.hotel.services.impl;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import com.hotel.exception.GuestNotFoundException;
import com.hotel.model.RoomType;
import com.hotel.repository.RoomTypeRepository;

public class RoomTypeServices {
	@Inject
	RoomTypeRepository roomTypeRepository;

	public RoomType add(final RoomType roomType) {
		return roomTypeRepository.add(roomType);
	}

	public void update(final RoomType roomType) {

		if (!roomTypeRepository.existsById(roomType.getId())) {
			throw new GuestNotFoundException();
		}

		roomTypeRepository.update(roomType);
	}

	public RoomType findById(final Long id) {
		final RoomType roomType = roomTypeRepository.findById(id);
		if (roomType == null) {
			throw new GuestNotFoundException();
		}
		return roomType;
	}

	public List<RoomType> fyndAll() {
		return roomTypeRepository.findAll("description");
	}

	public void remove(final RoomType roomType) throws PersistenceException {

		roomTypeRepository.remove(roomType, roomType.getId());

	}

}
