package com.hotel.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the employee database table.
 * 
 */
@Entity
@Table(name = "employee")
@NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "EMPLOYEE_ID_GENERATOR", sequenceName = "employee_id_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPLOYEE_ID_GENERATOR")
	@Column(unique = true, nullable = false)
	private Integer id;

	@Column(length = 255)
	private String password;

	@Column(length = 255)
	private String type;

	@Column(name = "user_name", length = 20)
	private String userName;

	// bi-directional many-to-one association to Reservation
	@OneToMany(mappedBy = "employee")
	private List<Reservation> reservations;

	public Employee() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getType() {
		return this.type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(final String userName) {
		this.userName = userName;
	}

	public List<Reservation> getReservations() {
		return this.reservations;
	}

	public void setReservations(final List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Reservation addReservation(final Reservation reservation) {
		getReservations().add(reservation);
		reservation.setEmployee(this);

		return reservation;
	}

	public Reservation removeReservation(final Reservation reservation) {
		getReservations().remove(reservation);
		reservation.setEmployee(null);

		return reservation;
	}

}