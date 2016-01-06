package com.hotel.services.impl;

import java.util.List;

import javax.inject.Inject;

import com.hotel.model.Employee;
import com.hotel.repository.EmployeeRepository;
import com.hotel.util.PasswordUtil;

public class EmployeeServices {

	@Inject
	private EmployeeRepository employeeRepository;

	public Employee findEmployeeByUsernamePassword(final String username, String password) {
		password = PasswordUtil.convertStringToMd5(password);
		return employeeRepository.findEmployeeByUsernamePassword(username, password);

	}

	public boolean employeeAlreadyExists(final String username) {

		return employeeRepository.alreadyExists("userName", username, null);

	}

	public Employee add(final Employee emp) {
		emp.setPassword(PasswordUtil.convertStringToMd5(emp.getPassword()));
		return employeeRepository.add(emp);
	}

	public List<Employee> fyndAll() {
		return employeeRepository.findAll("userName");
	}

	public void remove(final Long employeeId) {

		employeeRepository.remove(new Employee(), employeeId);
	}

	public void update(final Employee employee) {
		employeeRepository.update(employee);

	}

}
