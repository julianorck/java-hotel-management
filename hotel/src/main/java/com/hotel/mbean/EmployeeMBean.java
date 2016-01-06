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
import com.hotel.common.model.UserType;
import com.hotel.model.Employee;
import com.hotel.services.impl.EmployeeServices;

@ManagedBean(name = "employeeMBean")
@RequestScoped
public class EmployeeMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7161199492653715832L;
	private static final Logger logger = LoggerFactory.getLogger(EmployeeMBean.class);
	@Inject
	private EmployeeServices employeeServices;

	@ManagedProperty("#{language}")
	private ResourceBundle bundle;
	@Inject
	private Employee employee;

	private List<Employee> employeeList;

	public String save() {

		if (getEmployee().getId() == null) {
			employeeServices.add(getEmployee());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							getBundle().getString("messages.employee.success.add")));
		} else {
			employeeServices.update(getEmployee());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							getBundle().getString("messages.employee.success.update")));
		}

		setEmployee(new Employee());

		return PageAddress.EMPLOYEE_LIST;

	}

	public String update(final Employee employee) {
		setEmployee(employee);
		return PageAddress.EMPLOYEE;

	}

	public String remove(final Employee employee) {
		employeeServices.remove(employee.getId());
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
						getBundle().getString("messages.employee.success.remove")));
		setEmployeeList(employeeServices.fyndAll());
		return PageAddress.EMPLOYEE_LIST;

	}

	public UserType[] getUserTypeValues() {
		return UserType.values();
	}

	public ResourceBundle getBundle() {
		return bundle;
	}

	public void setBundle(final ResourceBundle bundle) {
		this.bundle = bundle;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(final Employee employee) {
		this.employee = employee;
	}

	public List<Employee> getEmployeeList() {
		employeeList = employeeServices.fyndAll();
		return employeeList;
	}

	public void setEmployeeList(final List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

}
