package com.hotel.services.impl;

import java.util.List;

import javax.inject.Inject;

import com.hotel.common.model.PaginatedData;
import com.hotel.common.model.filter.GuestFilter;
import com.hotel.common.model.filter.PaginationData;
import com.hotel.common.model.filter.PaginationData.OrderMode;
import com.hotel.exception.GuestNotFoundException;
import com.hotel.model.Guest;
import com.hotel.repository.GuestRepository;
import com.hotel.services.GenericServices;

public class GuestServices implements GenericServices {
	@Inject
	GuestRepository guestRepository;

	public Guest add(final Guest guest) {
		return guestRepository.add(guest);
	}

	public void update(final Guest guest) {

		if (!guestRepository.existsById(guest.getId())) {
			throw new GuestNotFoundException();
		}

		guestRepository.update(guest);
	}

	public Guest findById(final Long id) {
		final Guest guest = guestRepository.findById(id);
		if (guest == null) {
			throw new GuestNotFoundException();
		}
		return guest;
	}

	@Override
	public PaginatedData<Guest> findByFilter(final int firstpage, final int numberOfPages, final String name,
			final OrderMode orderMode) {
		final GuestFilter guestFilter = new GuestFilter();
		final PaginationData page = new PaginationData(firstpage, numberOfPages, "name", orderMode);
		guestFilter.setName(name);
		guestFilter.setPaginationData(page);
		return guestRepository.findByFilter(guestFilter);
	}

	public List<Guest> fyndAll() {
		return guestRepository.findAll("name");
	}

	public void remove(final Long guestId) {

		guestRepository.remove(new Guest(), guestId);
	}

	public Guest findByDocument(final String document) {

		return guestRepository.findGuestByDocument(document);
	}

}
