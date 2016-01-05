package com.hotel.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the guest database table.
 * 
 */
@Entity
@Table(name = "guest")
public class Guest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "GUEST_ID_GENERATOR", sequenceName = "guest_id_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GUEST_ID_GENERATOR")
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(length = 255)
	private String celphone;

	@Column(length = 255)
	private String city;

	@Column(length = 255)
	private String country;

	@Column(length = 255)
	private String email;

	@Column(length = 255)
	private String facebook;

	@Column(nullable = false, length = 255)
	private String name;

	@Column(length = 255)
	private String phone;

	@Column(length = 255)
	private String state;

	@Column(length = 255)
	private String street;

	@Column(name = "street_number", length = 255)
	private String streetNumber;

	public Guest() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getCelphone() {
		return this.celphone;
	}

	public void setCelphone(final String celphone) {
		this.celphone = celphone;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(final String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(final String country) {
		this.country = country;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getFacebook() {
		return this.facebook;
	}

	public void setFacebook(final String facebook) {
		this.facebook = facebook;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}

	public String getState() {
		return this.state;
	}

	public void setState(final String state) {
		this.state = state;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(final String street) {
		this.street = street;
	}

	public String getStreetNumber() {
		return this.streetNumber;
	}

	public void setStreetNumber(final String streetNumber) {
		this.streetNumber = streetNumber;
	}

}