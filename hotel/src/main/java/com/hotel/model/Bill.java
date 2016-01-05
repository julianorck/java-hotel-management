package com.hotel.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the bill database table.
 * 
 */
@Entity
@Table(name = "bill")
@NamedQuery(name = "Bill.findAll", query = "SELECT b FROM Bill b")
public class Bill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "BILL_ID_GENERATOR")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BILL_ID_GENERATOR")
	@Column(unique = true, nullable = false)
	private Integer id;

	private float total;

	// bi-directional many-to-one association to Reservation
	@ManyToOne
	@JoinColumn(name = "reservationid", nullable = false)
	private Reservation reservation;

	public Bill() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public float getTotal() {
		return this.total;
	}

	public void setTotal(final float total) {
		this.total = total;
	}

	public Reservation getReservation() {
		return this.reservation;
	}

	public void setReservation(final Reservation reservation) {
		this.reservation = reservation;
	}

}