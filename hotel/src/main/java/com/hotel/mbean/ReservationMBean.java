package com.hotel.mbean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hotel.common.model.PageAddress;
import com.hotel.model.Employee;
import com.hotel.model.Guest;
import com.hotel.model.Reservation;
import com.hotel.model.Room;
import com.hotel.services.impl.ReservationServices;
import com.hotel.services.impl.RoomServices;

@ManagedBean(name = "reservationMBean")
@SessionScoped
public class ReservationMBean implements Serializable {

	private static final long serialVersionUID = -603479319417268897L;

	private static final Logger logger = LoggerFactory.getLogger(ReservationMBean.class);
	@Inject
	private RoomServices roomServices;
	@Inject
	private Room room;
	@Inject
	private Reservation reservation;
	@Inject
	private ReservationServices reservationServices;
	@Inject
	private Guest guest;
	@ManagedProperty("#{language}")
	private ResourceBundle bundle;
	private LocalDateTime startDate;
	private LocalDateTime endDate;

	private List<Room> roomList;

	public Room getRoom() {
		return room;
	}

	public void setRoom(final Room room) {
		this.room = room;
	}

	public List<Room> getRoomList() {

		return roomList;
	}

	public void setRoomList(final List<Room> roomList) {
		this.roomList = roomList;
	}

	public String save() {
		final Employee emp = new Employee();
		emp.setPassword("adoooo");
		emp.setUserName("nomeiu");
		emp.setType("admoo");
		reservation.setGuest(guest);
		final List<Room> rooms = new ArrayList<>();
		rooms.add(room);
		reservation.setRooms(rooms);
		reservation.setEmployee(emp);
		if (reservation.getId() == null) {

			reservationServices.add(reservation);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							getBundle().getString("messages.reservation.success.add")));
		} else {
			reservationServices.update(reservation);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							getBundle().getString("messages.reservation.success.update")));
		}

		cleanAll();

		return PageAddress.RESERVATION;

	}

	public String bookRoom(final Room room) {
		this.room = room;
		this.roomList.clear();

		return PageAddress.RESERVATION_STEPS;
	}

	public String update(final Room room) {
		setRoom(room);
		return "roomtype";

	}

	public String remove(final Reservation reservation) {
		reservationServices.remove(reservation);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
						getBundle().getString("messages.reservation.success.remove")));
		roomList = roomServices.fyndAll();
		return "roomlist";

	}

	public void setRoomsByDate() {
		roomList = roomServices.findRoomsByDate(reservation.getCheckinDate(), reservation.getCheckoutDate());

	}

	public String getFormattedDate() {
		final String formattedDate = startDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
				+ " to " +
				endDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		return formattedDate;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(final LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(final LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(final Reservation reservation) {
		this.reservation = reservation;
	}

	public ReservationServices getReservationServices() {
		return reservationServices;
	}

	public void setReservationServices(final ReservationServices reservationServices) {
		this.reservationServices = reservationServices;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(final Guest guest) {
		this.guest = guest;
	}

	private void cleanAll() {
		room = new Room();
		reservation = new Reservation();
		guest = new Guest();
		roomList = new ArrayList<>();

	}

	public ResourceBundle getBundle() {
		return bundle;
	}

	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

}
