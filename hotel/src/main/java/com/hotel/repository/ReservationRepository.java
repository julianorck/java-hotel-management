package com.hotel.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.hotel.common.repository.GenericRepository;
import com.hotel.model.Reservation;

@Stateless
public class ReservationRepository extends GenericRepository<Reservation> {

	@PersistenceContext
	EntityManager em;

	@Override
	protected Class<Reservation> getPersistentClass() {
		return Reservation.class;
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}