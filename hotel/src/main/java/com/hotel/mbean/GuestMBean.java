package com.hotel.mbean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hotel.common.model.PageAddress;
import com.hotel.model.Guest;
import com.hotel.services.impl.GuestServices;

@ManagedBean(name = "guestMBean")
@RequestScoped
public class GuestMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(GuestMBean.class);
	@Inject
	private GuestServices guestServices;
	@Inject
	private Guest guest;

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(final Guest guest) {
		this.guest = guest;
	}

	public String save() {
		if (guest.getId() == null) {
			guestServices.add(guest);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Usuario cadastrado com sucesso."));
		} else {
			guestServices.update(guest);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Usuario alterado com sucesso."));
		}

		guest = new Guest();

		return PageAddress.GUEST_LIST;

	}

	public String update(final Long guestId) {
		guest = guestServices.findById(guestId);

		return PageAddress.GUEST;

	}

}
