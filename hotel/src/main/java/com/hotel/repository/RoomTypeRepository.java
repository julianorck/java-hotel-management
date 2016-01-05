package com.hotel.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.hotel.common.repository.GenericRepository;
import com.hotel.model.RoomType;

@Stateless
public class RoomTypeRepository extends GenericRepository<RoomType> {

	@PersistenceContext
	EntityManager em;

	@Override
	protected Class<RoomType> getPersistentClass() {
		return RoomType.class;
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}