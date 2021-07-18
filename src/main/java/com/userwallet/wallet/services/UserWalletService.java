package com.userwallet.wallet.services;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.userwallet.wallet.dto.AddMoneyRequestDTO;
import com.userwallet.wallet.dto.SignInDTO;
import com.userwallet.wallet.dto.TransferMoneyRequestDTO;
import com.userwallet.wallet.entities.Transactions;
import com.userwallet.wallet.entities.Users;
import com.userwallet.wallet.entities.Wallet;
import com.userwallet.wallet.util.ApiResponse;

public interface UserWalletService {

	public Iterable<Users> getUsers();

	public Optional<Users> getUser(Long userId);

	public ApiResponse<Users> addUser(Users user) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeySpecException;

	public ApiResponse<Users> updateUser(Users user);

	public ApiResponse<Wallet> addMoney(AddMoneyRequestDTO addMoneyReq);

	public ApiResponse<Wallet> tranferMoney(TransferMoneyRequestDTO transferMoneyReq);

	public Iterable<Transactions> getPassBook(Long userId);

	public ApiResponse<Users> signIn(SignInDTO signin);

}
