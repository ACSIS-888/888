package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Admin {
	@Id
	@Column(name="Login")
	private String login;
	@Column(name="Password")
	private String password;
}
