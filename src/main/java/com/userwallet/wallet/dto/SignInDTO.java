package com.userwallet.wallet.dto;

public class SignInDTO {

	private Long mobileNumber;
	private String password;

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "SignInDTO [mobileNumber=" + mobileNumber + ", password=" + password + "]";
	}

	public SignInDTO(Long mobileNumber, String password) {
		super();
		this.mobileNumber = mobileNumber;
		this.password = password;
	}

}
