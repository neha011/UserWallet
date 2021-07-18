package com.userwallet.wallet.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

import com.userwallet.wallet.Enum.TransType;

@Entity(name = "Transactions")
@Table(name = "transactions")
public class Transactions {

	@Id
	private Long transId;
	private Long userId;
	private TransType transType;
	private BigDecimal amount;
	private LocalDateTime createdAt;

	public Long getTransId() {
		return transId;
	}

	public void setTransId(Long transId) {
		this.transId = transId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public TransType getTransType() {
		return transType;
	}

	public void setTransType(TransType transType) {
		this.transType = transType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Transactions [transId=" + transId + ", userId=" + userId + ", transType=" + transType + ", amount="
				+ amount + ", createdAt=" + createdAt + "]";
	}

	public Transactions(Long transId, Long userId, TransType transType, BigDecimal amount, LocalDateTime createdAt) {
		super();
		this.transId = transId;
		this.userId = userId;
		this.transType = transType;
		this.amount = amount;
		this.createdAt = createdAt;
	}

	public Transactions() {
		super();
		// TODO Auto-generated constructor stub
	}

}
