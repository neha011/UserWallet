package com.userwallet.wallet.Enum;

public enum TransType {

	DEBIT("Debit"), CREDIT("Credit");

	private String val;

	TransType(String val) {
		this.val = val;
	}

}
