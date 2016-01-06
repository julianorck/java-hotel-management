package com.hotel.mbean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hotel.common.model.PaginatedData;
import com.hotel.common.model.PaginationHelper;
import com.hotel.common.model.filter.PaginationData.OrderMode;
import com.hotel.model.Guest;
import com.hotel.services.GenericServices;

public abstract class PaginationBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(PaginationBean.class);
	private String searchTerm;
	private int pageSize;
	private int[] page;
	private int totalCount;
	private int selectedItemIndex;
	private PaginationHelper pagination;
	private DataModel<Guest> dtmdl = null;
	private String pageRet = "";

	protected abstract GenericServices getServiceClass();

	@PostConstruct
	public void postConstruct() {

		setPage(new int[] { 5, 10, 15, 20, 30, 50 });
		setPageSize(5);
	}

	public PaginationHelper getPagination() {
		if (pagination == null) {
			pagination = new PaginationHelper(this.getPageSize(), 0) {

				@Override
				public int getItemsCount() {
					final GenericServices service = getServiceClass();
					return service.findByFilter(getPageFirstItem(),
							getPageSize(), searchTerm, OrderMode.ASCENDING).getNumberOfRows();
				}

				@Override
				public DataModel createPageDataModel() {

					final PaginatedData<Guest> paginatedData = getServiceClass().findByFilter(getPageFirstItem(),
							getPageSize(), searchTerm, OrderMode.ASCENDING);
					return new ListDataModel(paginatedData.getRows());
				}
			};

		}
		return pagination;

	}

	public DataModel getdtmdl() {
		if (dtmdl == null) {
			dtmdl = getPagination().createPageDataModel();
		}
		return dtmdl;
	}

	protected void recreateModel() {
		dtmdl = null;
	}

	protected void recreatePagination() {
		pagination = null;
	}

	protected <T> void updateCurrentItem(T item) {
		final int count = totalCount;
		if (selectedItemIndex >= count) {

			// selected index cannot be bigger than number of items:
			selectedItemIndex = count - 1;

			// go to previous page if last page disappeared:
			if (pagination.getPageFirstItem() >= count) {
				pagination.previousPage();
			}
		}
		if (selectedItemIndex >= 0) {
			final PaginatedData<Guest> paginatedData = getServiceClass().findByFilter(selectedItemIndex,
					selectedItemIndex + 1, "", OrderMode.ASCENDING);
			item = (T) paginatedData.getRow(0);
			// item = itembean.findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
		}
	}

	public String next() {
		getPagination().nextPage();
		recreateModel();
		return pageRet;
	}

	public String previous() {
		getPagination().previousPage();
		recreateModel();
		return pageRet;
	}

	// go to the 1st page

	public String firstPage() {
		recreatePagination();
		recreateModel();
		return pageRet;
	}

	// go to the last page
	public String lastPage() {

		getPagination().setFinalPages();
		recreateModel();
		return pageRet;
	}

	public String recreatePageSize(final AjaxBehaviorEvent e) {
		recreatePagination();
		recreateModel();
		return pageRet;
	}

	public String search() {
		recreatePagination();
		recreateModel();
		return pageRet;

	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(final String searchTerm) {
		this.searchTerm = searchTerm;
	}

	public int[] getPage() {
		return page;
	}

	public void setPage(final int[] page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;
	}

	public String getPageRet() {
		return pageRet;
	}

	public void setPageRet(final String pageRet) {
		this.pageRet = pageRet;
	}

}
