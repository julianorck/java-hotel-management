package com.hotel.services.impl;

import java.util.List;

import javax.inject.Inject;

import com.hotel.exception.GuestNotFoundException;
import com.hotel.model.Reservation;
import com.hotel.repository.ReservationRepository;

public class ReservationServices {
	@Inject
	ReservationRepository reservationRepository;

	public Reservation add(final Reservation reservation) {
		return reservationRepository.add(reservation);
	}

	public void update(final Reservation reservation) {

		if (!reservationRepository.existsById(reservation.getId())) {
			throw new GuestNotFoundException();
		}

		reservationRepository.update(reservation);
	}

	public Reservation findById(final Long id) {
		final Reservation reservation = reservationRepository.findById(id);
		if (reservation == null) {
			throw new GuestNotFoundException();
		}
		return reservation;
	}

	public List<Reservation> fyndAll() {
		return reservationRepository.findAll("checkinDate");
	}

	public void remove(final Reservation reservation) {
		reservationRepository.remove(reservation, reservation.getId());
	}

}
