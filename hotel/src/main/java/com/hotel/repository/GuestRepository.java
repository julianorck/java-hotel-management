package com.hotel.repository;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.hotel.common.model.PaginatedData;
import com.hotel.common.model.filter.GuestFilter;
import com.hotel.common.repository.GenericRepository;
import com.hotel.model.Guest;

@Stateless
public class GuestRepository extends GenericRepository<Guest> {

	@PersistenceContext
	EntityManager em;

	@Override
	protected Class<Guest> getPersistentClass() {
		return Guest.class;
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public PaginatedData<Guest> findByFilter(final GuestFilter filter) {
		final StringBuilder clause = new StringBuilder("WHERE e.id is not null");
		final Map<String, Object> queryParameters = new HashMap<>();
		if (filter.getName() != null) {
			clause.append(" And UPPER(e.name) Like UPPER(:name)");
			queryParameters.put("name", "%" + filter.getName() + "%");
		}

		return findByParameters(clause.toString(), filter.getPaginationData(), queryParameters, "name ASC");
	}

}