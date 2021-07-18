package com.userwallet.wallet.entities;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "Users")
@Table(name = "users")
public class Users {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2827512551918161885L;
	

	/*
	 * public Users(Users user) { }
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	private String firstName;
	private String middleName;
	private String lastName;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dateOfBirth;
	private String emailId;
	private Long mobileNumber;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private String password;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	@JsonIgnore
	private List<Roles> role;

	public Users() {
	}
	
	public Users(Users user) {
//		super();
		this.userId = user.userId;
		this.firstName = user.firstName;
		this.middleName = user.middleName;
		this.lastName = user.lastName;
		this.dateOfBirth = user.dateOfBirth;
		this.emailId = user.emailId;
		this.mobileNumber = user.mobileNumber;
		this.createdAt = user.createdAt;
		this.updatedAt = user.updatedAt;
		this.password = user.password;
		this.role = user.role;
	}

	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Roles> getRoles() {
		return role;
	}

	public void setRoles(List<Roles> role) {
		this.role = role;
	}

	@Override
	public String toString() {
		final int maxLen = 10;
		return "Users [userId=" + userId + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName="
				+ lastName + ", dateOfBirth=" + dateOfBirth + ", emailId=" + emailId + ", mobileNumber=" + mobileNumber
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", password=" + password + ", role="
				+ (role != null ? role.subList(0, Math.min(role.size(), maxLen)) : null) + "]";
	}


}
