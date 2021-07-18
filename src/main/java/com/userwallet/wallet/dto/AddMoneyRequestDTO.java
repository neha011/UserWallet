package com.userwallet.wallet.dto;

import java.math.BigDecimal;

public class AddMoneyRequestDTO {

	Long mobileNumber;
	BigDecimal amount;

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public AddMoneyRequestDTO(Long mobileNumber, BigDecimal amount) {
		super();
		this.mobileNumber = mobileNumber;
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "AddMoneyRequestDTO [mobileNumber=" + mobileNumber + ", amount=" + amount + "]";
	}

}
