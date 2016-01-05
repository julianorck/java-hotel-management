package com.hotel.mbean;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hotel.common.model.PageAddress;
import com.hotel.model.Room;
import com.hotel.model.RoomType;
import com.hotel.services.impl.RoomServices;
import com.hotel.services.impl.RoomTypeServices;

@ManagedBean(name = "roomMBean")
@RequestScoped
public class RoomMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7161199492653715832L;
	private static final Logger logger = LoggerFactory.getLogger(RoomMBean.class);
	@Inject
	private RoomTypeServices roomTypeServices;
	@Inject
	private RoomServices roomServices;
	@Inject
	private Room room;
	@ManagedProperty("#{language}")
	private ResourceBundle bundle;

	private Long roomTypeId;

	private List<Room> roomList;
	private List<RoomType> roomTypeList;

	public Room getRoom() {
		return room;
	}

	public void setRoom(final Room room) {
		this.room = room;
	}

	public List<Room> getRoomList() {
		roomList = roomServices.fyndAll();
		return roomList;
	}

	public void setRoomList(final List<Room> roomList) {
		this.roomList = roomList;
	}

	public List<RoomType> getRoomTypeList() {
		roomTypeList = roomTypeServices.fyndAll();
		return roomTypeList;
	}

	public String save() {
		final RoomType rt = new RoomType();
		rt.setId(roomTypeId);
		room.setRoomType(rt);
		if (room.getId() == null) {
			roomServices.add(room);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							getBundle().getString("messages.room.success.add")));
		} else {
			roomServices.update(room);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							getBundle().getString("messages.room.success.update")));
		}

		room = new Room();

		return PageAddress.ROOM_LIST;

	}

	public String update(final Room room) {
		setRoom(room);
		setRoomTypeId(room.getRoomType().getId());
		return PageAddress.ROOM;

	}

	public String remove(final Room room) {
		roomServices.remove(room);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", getBundle().getString("messages.room.success.remove")));
		roomList = roomServices.fyndAll();
		return PageAddress.ROOM_LIST;

	}

	public Long getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(final Long roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public ResourceBundle getBundle() {
		return bundle;
	}

	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

}
