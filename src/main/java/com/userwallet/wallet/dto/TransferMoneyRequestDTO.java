package com.userwallet.wallet.dto;

import java.math.BigDecimal;

public class TransferMoneyRequestDTO {

	public Long FromUserId;
	public BigDecimal amount;

	public Long mobileNumberTo;

	public Long getFromUserId() {
		return FromUserId;
	}

	public void setFromUserId(Long fromUserId) {
		FromUserId = fromUserId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Long getMobileNumberTo() {
		return mobileNumberTo;
	}

	public void setMobileNumberTo(Long mobileNumberTo) {
		this.mobileNumberTo = mobileNumberTo;
	}

	@Override
	public String toString() {
		return "TransferMoneyRequestDTO [FromUserId=" + FromUserId + ", amount=" + amount + ", mobileNumberTo="
				+ mobileNumberTo + "]";
	}

	public TransferMoneyRequestDTO(Long fromUserId, BigDecimal amount, Long mobileNumberTo) {
		super();
		FromUserId = fromUserId;
		this.amount = amount;
		this.mobileNumberTo = mobileNumberTo;
	}

}
