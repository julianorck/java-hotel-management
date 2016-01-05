package com.hotel.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the reservation database table.
 * 
 */
@Entity
@Table(name = "reservation")
public class Reservation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "RESERVATION_ID_GENERATOR", sequenceName = "reservation_id_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RESERVATION_ID_GENERATOR")
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(name = "checkin_date")
	private LocalDateTime checkinDate;

	@Column(name = "checkout_date")
	private LocalDateTime checkoutDate;

	@Column(name = "num_guests")
	private Integer numGuests;

	@Column(name = "num_nights")
	private Integer numNights;

	@Column(name = "room_total")
	private float roomTotal;

	@Column(length = 255)
	private String status;

	@Column(length = 600)
	private String requirement;

	// bi-directional many-to-one association to Bill
	@OneToMany(mappedBy = "reservation")
	private List<Bill> bills;

	// bi-directional m(cascade = CascadeType.ALL)any-to-one association to Employee
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "employeeid", nullable = false)
	private Employee employee;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "guestid", nullable = false)
	private Guest guest;

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "reservation_room", joinColumns = {
			@JoinColumn(name = "reservationid", nullable = false)
	}, inverseJoinColumns = {
			@JoinColumn(name = "roomid", referencedColumnName = "id", nullable = false)
	})
	private List<Room> rooms;

	public Reservation() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public LocalDateTime getCheckinDate() {
		return this.checkinDate;
	}

	public void setCheckinDate(final LocalDateTime checkinDate) {
		this.checkinDate = checkinDate;
	}

	public LocalDateTime getCheckoutDate() {
		return this.checkoutDate;
	}

	public void setCheckoutDate(final LocalDateTime checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public Integer getNumGuests() {
		return this.numGuests;
	}

	public void setNumGuests(final Integer numGuests) {
		this.numGuests = numGuests;
	}

	public Integer getNumNights() {
		return this.numNights;
	}

	public void setNumNights(final Integer numNights) {
		this.numNights = numNights;
	}

	public float getRoomTotal() {
		return this.roomTotal;
	}

	public void setRoomTotal(final float roomTotal) {
		this.roomTotal = roomTotal;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public List<Bill> getBills() {
		return this.bills;
	}

	public void setBills(final List<Bill> bills) {
		this.bills = bills;
	}

	public Bill addBill(final Bill bill) {
		getBills().add(bill);
		bill.setReservation(this);

		return bill;
	}

	public Bill removeBill(final Bill bill) {
		getBills().remove(bill);
		bill.setReservation(null);

		return bill;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(final Employee employee) {
		this.employee = employee;
	}

	public List<Room> getRooms() {
		return this.rooms;
	}

	public void setRooms(final List<Room> rooms) {
		this.rooms = rooms;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(final Guest guest) {
		this.guest = guest;
	}

	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

}