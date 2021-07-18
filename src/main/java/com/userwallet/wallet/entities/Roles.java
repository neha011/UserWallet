package com.userwallet.wallet.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity(name = "Roles")
@Table(name = "roles")
public class Roles {

	@Id
	private Long id;
	private String authority;

	@ManyToMany(mappedBy = "role", cascade = CascadeType.ALL)
	private List<Users> users;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}

	public Roles() {
		super();
	}

	public Roles(Long id, String authority) {
		super();
		this.id = id;
		this.authority = authority;
	}

}
