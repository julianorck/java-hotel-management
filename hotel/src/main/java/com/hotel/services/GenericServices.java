package com.hotel.services;

import com.hotel.common.model.PaginatedData;
import com.hotel.common.model.filter.PaginationData.OrderMode;

public interface GenericServices {
	public PaginatedData findByFilter(final int firstpage, final int numberOfPages, final String name,
			final OrderMode orderMode);

}
