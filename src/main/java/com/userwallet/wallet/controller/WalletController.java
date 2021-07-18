package com.userwallet.wallet.controller;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.userwallet.wallet.dto.AddMoneyRequestDTO;
import com.userwallet.wallet.dto.TransferMoneyRequestDTO;
import com.userwallet.wallet.entities.Transactions;
import com.userwallet.wallet.entities.Users;
import com.userwallet.wallet.entities.Wallet;
import com.userwallet.wallet.services.UserWalletService;
import com.userwallet.wallet.util.ApiResponse;

@RestController
public class WalletController {

	@Autowired
	private UserWalletService userwallet;

	// sign up.
	@PostMapping("/users")
	public ApiResponse<Users> addUser(@RequestBody Users user)
			throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException,
			IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeySpecException {
		return userwallet.addUser(user);
	}

	// Get all the Users.
	@GetMapping("/users")
	public Iterable<Users> getUsers() {
		return userwallet.getUsers();
	}

	// View specific account
	@GetMapping("/users/{userId}")
	public Optional<Users> getUser(@PathVariable Long userId) {
		return userwallet.getUser(userId);
	}

	// view passbook.
	@GetMapping("/users/passbook/{userId}")
	public Iterable<Transactions> getPassBook(@PathVariable Long userId) {
		return userwallet.getPassBook(userId);

	}

	// add money
	@PostMapping("/wallet")
	public ApiResponse<Wallet> addMoney(@RequestBody AddMoneyRequestDTO addMoneyReq) {
		return userwallet.addMoney(addMoneyReq);
	}

	// update User.
	@PutMapping("/users")
	public ApiResponse<Users> updateUser(@RequestBody Users user) {
		return userwallet.updateUser(user);
	}

	// transfer money.
	@PutMapping("/users/transaction")
	public ApiResponse<Wallet> tranferMoney(@RequestBody TransferMoneyRequestDTO transferMoneyReq) {
		return userwallet.tranferMoney(transferMoneyReq);
	}

}
