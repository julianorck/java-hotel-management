package com.hotel.mbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hotel.model.Employee;
import com.hotel.services.common.SessionContext;
import com.hotel.services.impl.EmployeeServices;

@ManagedBean(name = "loginMBean")
@SessionScoped
public class LoginMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7161199492653715832L;
	private static final Logger logger = LoggerFactory.getLogger(LoginMBean.class);
	@Inject
	EmployeeServices employeeServices;
	@ManagedProperty("#{language}")
	private ResourceBundle bundle;

	private String username;
	private String password;

	public String login() {

		if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
			FacesContext.getCurrentInstance().validationFailed();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, null,
							getBundle().getString("messages.login.error")));
			username = "";
			password = "";
			return "login";
		}
		final Employee emp = employeeServices.findEmployeeByUsernamePassword(getUsername(),
				getPassword());
		if (emp == null) {
			FacesContext.getCurrentInstance().validationFailed();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							bundle.getString("messages.login.error"), null));
			username = "";
			password = "";
			return "login";
		}
		logger.info("Succesfully logged");
		SessionContext.getInstance().setAttribute("user", emp);

		return "reservation";

	}

	public void logout() throws IOException {
		SessionContext.getInstance().endSession();
		FacesContext.getCurrentInstance()
				.getExternalContext().redirect("login.xhtml");

	}

	public ResourceBundle getBundle() {
		return bundle;
	}

	public void setBundle(final ResourceBundle bundle) {
		this.bundle = bundle;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

}
