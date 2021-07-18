package com.userwallet.wallet.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.userwallet.wallet.Enum.TransType;

@Entity(name = "Wallet")
@Table(name = "wallet")
public class Wallet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long walletId;
	private Long userId;
	private BigDecimal totalBalance;
	private LocalDateTime updatedAt;

	public Long getWalletId() {
		return walletId;
	}

	public void setWalletId(Long walletId) {
		this.walletId = walletId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public BigDecimal getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(BigDecimal totalBalance) {
		this.totalBalance = totalBalance;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "Wallet [walletId=" + walletId + ", userId=" + userId + ", totalBalance=" + totalBalance + ", updatedAt="
				+ updatedAt + "]";
	}

	public Wallet(Long walletId, Long userId, TransType transType, Long amount, BigDecimal totalBalance,
			LocalDateTime updatedAt) {
		super();
		this.walletId = walletId;
		this.userId = userId;
		this.totalBalance = totalBalance;
		this.updatedAt = updatedAt;
	}

	public Wallet() {
		super();
		// TODO Auto-generated constructor stub
	}

}
