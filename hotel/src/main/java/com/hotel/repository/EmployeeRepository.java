package com.hotel.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.hotel.common.repository.GenericRepository;
import com.hotel.model.Employee;

@Stateless
public class EmployeeRepository extends GenericRepository<Employee> {

	@PersistenceContext
	EntityManager em;

	@Override
	protected Class<Employee> getPersistentClass() {
		return Employee.class;
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	@SuppressWarnings("unchecked")
	public Employee findEmployeeByUsernamePassword(final String username, final String password) {
		final List<Employee> listEmp = getEntityManager().createQuery(
				"Select e From " + getPersistentClass().getSimpleName()
						+ " e where e.userName = :user and e.password = :password")
				.setParameter("user", username)
				.setParameter("password", password)
				.getResultList();
		Employee emp = null;
		if (!listEmp.isEmpty()) {
			emp = listEmp.get(0);
		}
		return emp;
	}

}