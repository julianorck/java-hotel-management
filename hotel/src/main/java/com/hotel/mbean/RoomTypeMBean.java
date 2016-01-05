package com.hotel.mbean;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hotel.common.model.PageAddress;
import com.hotel.model.RoomType;
import com.hotel.services.impl.RoomTypeServices;

@ManagedBean(name = "roomTypeMBean")
@RequestScoped
public class RoomTypeMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(RoomTypeMBean.class);
	@Inject
	private RoomTypeServices roomTypeServices;
	@Inject
	private RoomType roomType;
	@ManagedProperty("#{language}")
	private ResourceBundle bundle;

	private List<RoomType> roomTypeList;

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(final RoomType roomType) {
		this.roomType = roomType;
	}

	public List<RoomType> getRoomTypeList() {
		roomTypeList = roomTypeServices.fyndAll();
		return roomTypeList;
	}

	public void setRoomTypeList(final List<RoomType> roomTypeList) {
		this.roomTypeList = roomTypeList;
	}

	public String save() {
		if (roomType.getId() == null) {
			roomTypeServices.add(roomType);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							getBundle().getString("messages.roomtype.success.add")));
		} else {
			roomTypeServices.update(roomType);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							getBundle().getString("messages.roomtype.success.update")));
		}

		roomType = new RoomType();

		return PageAddress.ROOM_TYPE_LIST;

	}

	public String update(final RoomType roomType) {
		setRoomType(roomType);
		return PageAddress.ROOM_TYPE;

	}

	public String remove(final RoomType roomType) {
		try {
			roomTypeServices.remove(roomType);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							getBundle().getString("messages.roomtype.success.remove")));
			roomTypeList = roomTypeServices.fyndAll();
		} catch (final EJBException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							getBundle().getString("messages.roomtype.error.remove")));

		}
		return PageAddress.ROOM_TYPE_LIST;

	}

	public ResourceBundle getBundle() {
		return bundle;
	}

	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

}
