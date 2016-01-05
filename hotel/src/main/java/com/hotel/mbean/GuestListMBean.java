package com.hotel.mbean;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hotel.common.model.PageAddress;
import com.hotel.model.Guest;
import com.hotel.services.GenericServices;
import com.hotel.services.impl.GuestServices;

@ManagedBean(name = "guestListMBean")
@RequestScoped
public class GuestListMBean extends PaginationBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(GuestListMBean.class);
	@Inject
	private GuestServices guestServices;
	@Inject
	private Guest guest;
	@ManagedProperty("#{language}")
	private ResourceBundle bundle;

	@PostConstruct
	public void postConstructGuest() {

		setPageRet(PageAddress.GUEST_LIST);
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(final Guest guest) {
		this.guest = guest;
	}

	public String remove(final Long guestId) {
		guestServices.remove(guestId);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
						bundle.getString("messages.guest.success.remove")));
		recreateModel();
		return PageAddress.GUEST_LIST;

	}

	public String save() {
		if (guest.getId() == null) {
			guestServices.add(guest);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							bundle.getString("messages.guest.success.add")));
		} else {
			guestServices.update(guest);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							bundle.getString("messages.guest.success.update")));
		}

		guest = new Guest();
		recreateModel();
		return PageAddress.GUEST_LIST;

	}

	public String update(final Long guestId) {
		guest = guestServices.findById(guestId);

		return PageAddress.GUEST;

	}

	@Override
	protected GenericServices getServiceClass() {

		return guestServices;
	}

	public ResourceBundle getBundle() {
		return bundle;
	}

	public void setBundle(final ResourceBundle bundle) {
		this.bundle = bundle;
	}

}
