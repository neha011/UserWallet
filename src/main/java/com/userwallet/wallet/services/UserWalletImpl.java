package com.userwallet.wallet.services;

import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.userwallet.wallet.Enum.TransType;
import com.userwallet.wallet.dao.RoleDAO;
import com.userwallet.wallet.dao.TransactionsDAO;
import com.userwallet.wallet.dao.UserDAO;
import com.userwallet.wallet.dao.WalletDao;
import com.userwallet.wallet.dto.AddMoneyRequestDTO;
import com.userwallet.wallet.dto.SignInDTO;
import com.userwallet.wallet.dto.TransferMoneyRequestDTO;
import com.userwallet.wallet.entities.Roles;
import com.userwallet.wallet.entities.Transactions;
import com.userwallet.wallet.entities.Users;
import com.userwallet.wallet.entities.Wallet;
import com.userwallet.wallet.security.AppUser;
import com.userwallet.wallet.util.AESCryptography;
import com.userwallet.wallet.util.ApiResponse;

@Service
public class UserWalletImpl implements UserWalletService {

	private Logger logger = LoggerFactory.getLogger(UserWalletImpl.class);

	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Value("${encryption.password}")
	private String password;
	@Value("${encryption.salt}")
	private String salt;
	@Value("${encryption.iv}")
	private String iv;

	@Autowired
	private UserDAO userdao;

	@Autowired
	private WalletDao walletdao;

	@Autowired
	private TransactionsDAO transdao;

	@Autowired
	private RoleDAO roledao;

	@Override
	public Optional<Users> getUser(Long userId) {
		return userdao.findById(userId);

	}

	@Override
	public ApiResponse<Users> addUser(Users user)
			throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException,
			IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeySpecException {

		Optional<Users> existingMobNum = userdao.findByMobileNumb(user.getMobileNumber());
		if (existingMobNum.isPresent()) {
			logger.info("Logging Failed...{}", user.getMobileNumber());
			return new ApiResponse<>(HttpStatus.NOT_ACCEPTABLE, "Mobile Number already registered!", null);
		} else {
			logger.info("User registration started...{}", user.getMobileNumber());
			Optional<Roles> r1 = roledao.findById(2l);
			Users newuser = new Users();
			newuser.setFirstName(user.getFirstName());
			newuser.setMiddleName(user.getMiddleName());
			newuser.setLastName(user.getLastName());
			newuser.setDateOfBirth(user.getDateOfBirth());
			newuser.setEmailId(user.getEmailId());
			newuser.setMobileNumber(user.getMobileNumber());
			/*
			 * AESCryptography aesCryptography = new AESCryptography(password, salt, iv);
			 * String encyptedPassword = aesCryptography.encrypt(user.getPassword());
			 * newuser.setPassword(encyptedPassword);
			 */
			
			newuser.setPassword(passwordEncoder.encode(user.getPassword()));
			newuser.setRoles(Arrays.asList(r1.get()));
			newuser.setCreatedAt(LocalDateTime.now());
			userdao.save(newuser);
			return new ApiResponse<>(HttpStatus.CREATED, "User created Successfully!", null);
		}

	}

	@Override
	public ApiResponse<Users> updateUser(Users user) {
		Optional<Users> userdetails = userdao.findById(user.getUserId());
		if (userdetails.isPresent()) {
			logger.info("Updating user details started...}");
			Users u1 = userdetails.get();
			if (!com.google.common.base.Strings.isNullOrEmpty(user.getFirstName()))
				u1.setFirstName(user.getFirstName());
			if (!com.google.common.base.Strings.isNullOrEmpty(user.getMiddleName()))
				u1.setMiddleName(user.getMiddleName());
			if (!com.google.common.base.Strings.isNullOrEmpty(user.getLastName()))
				u1.setLastName(user.getLastName());
			if (!com.google.common.base.Strings.isNullOrEmpty(user.getEmailId()))
				u1.setEmailId(user.getEmailId());
			if (user.getDateOfBirth() != null)
				u1.setDateOfBirth(user.getDateOfBirth());
			u1.setUpdatedAt(LocalDateTime.now());
			userdao.save(u1);
			return new ApiResponse<>(HttpStatus.OK, "User updated Successfully!", null);
		} else {
			return new ApiResponse<>(HttpStatus.NOT_FOUND, "User doesn't exist!", null);
		}

	}

	@Override
	public Iterable<Users> getUsers() {
		return userdao.findAll();
	}

	@Override
	public ApiResponse<Wallet> addMoney(AddMoneyRequestDTO addMoneyReq) {
		Optional<Users> userdetails = userdao.findByMobileNumb(addMoneyReq.getMobileNumber());
		Users u1 = userdetails.get();
		Optional<Wallet> walletdetails = walletdao.findByUserId(u1.getUserId());
		Wallet deposit;
		logger.info("Adding Money to the wallet...}");
		if (walletdetails.isPresent()) {
			deposit = walletdetails.get();

		} else {
			deposit = new Wallet();
			deposit.setUserId(u1.getUserId());
			deposit.setTotalBalance(BigDecimal.ZERO);
		}
		deposit.setTotalBalance(deposit.getTotalBalance().add(addMoneyReq.getAmount()));
		deposit.setUpdatedAt(LocalDateTime.now());

		walletdao.save(deposit);

		Transactions trans = new Transactions();

		trans.setTransId(createTransId(u1.getUserId()));
		trans.setTransType(TransType.CREDIT);
		trans.setAmount(addMoneyReq.getAmount());
		trans.setUserId(deposit.getUserId());
		trans.setCreatedAt(deposit.getUpdatedAt());

		transdao.save(trans);
		return new ApiResponse<>(HttpStatus.OK, "Amount added successfully!", deposit);

	}

	private Long createTransId(Long userId) {

		DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
		Date date = new Date();
		String todaysDate = df.format(date);
		Long transId = userId + Long.parseLong(todaysDate);
		return transId;

	}

	@Override
	public ApiResponse<Wallet> tranferMoney(TransferMoneyRequestDTO transferMoneyReq) {
		Optional<Users> usercheck = userdao.findById(transferMoneyReq.getFromUserId());
		Optional<Users> userdetails = userdao.findByMobileNumb(transferMoneyReq.getMobileNumberTo());
		Wallet withdrawal;
		Users user;

		if (!usercheck.isPresent()) {
			return new ApiResponse<>(HttpStatus.NOT_FOUND, "Sender ID is incorrect!", null);
		} else if (!userdetails.isPresent()) {
			return new ApiResponse<>(HttpStatus.NOT_FOUND, "Receiver doesn't exist!", null);
		} else {
			Optional<Wallet> walletdetailsFrom = walletdao.findByUserId(transferMoneyReq.getFromUserId());
			if (!walletdetailsFrom.isPresent()) {
				return new ApiResponse<>(HttpStatus.CONFLICT, "Wallet is empty!", null);
			} else {
				logger.info("Transfer Process started...}");
				withdrawal = walletdetailsFrom.get();
				withdrawal.setTotalBalance(withdrawal.getTotalBalance().subtract(transferMoneyReq.getAmount()));
				withdrawal.setUpdatedAt(LocalDateTime.now());

				Transactions dbTrans = new Transactions();

				dbTrans.setTransId(createTransId(transferMoneyReq.getFromUserId()));
				dbTrans.setTransType(TransType.DEBIT);
				dbTrans.setAmount(transferMoneyReq.getAmount());
				dbTrans.setUserId(transferMoneyReq.getFromUserId());
				dbTrans.setCreatedAt(withdrawal.getUpdatedAt());

				transdao.save(dbTrans);

				user = userdetails.get();
				Optional<Wallet> walletdetailsTo = walletdao.findByUserId(user.getUserId());
				Wallet deposit = new Wallet();
				if (walletdetailsTo.isPresent()) {
					deposit = walletdetailsTo.get();

				} else {
					deposit.setUserId(user.getUserId());
					deposit.setTotalBalance(BigDecimal.ZERO);
				}

				deposit.setTotalBalance(deposit.getTotalBalance().add(transferMoneyReq.getAmount()));
				deposit.setUpdatedAt(LocalDateTime.now());

				walletdao.save(withdrawal);
				walletdao.save(deposit);

				Transactions crTrans = new Transactions();

				crTrans.setTransId(createTransId(deposit.getUserId()));
				crTrans.setTransType(TransType.CREDIT);
				crTrans.setAmount(transferMoneyReq.getAmount());
				crTrans.setUserId(deposit.getUserId());
				crTrans.setCreatedAt(deposit.getUpdatedAt());

				transdao.save(crTrans);

				return new ApiResponse<>(HttpStatus.OK, "Amount tranferred successfull!", null);
			}
		}

	}

	@Override
	public Iterable<Transactions> getPassBook(Long userId) {
		return transdao.findByUserId(userId);
	}

	@Override
	public ApiResponse<Users> signIn(SignInDTO signin) {
		Optional<Users> userdetail = userdao.findByMobileNumb(signin.getMobileNumber());
		if (userdetail.isPresent()) {
			logger.info("Logging in shortly...}");
			Users u1 = userdetail.get();
			String encryptedString = u1.getPassword();
			AESCryptography aesCryptography = new AESCryptography(password, salt, iv);
			String decryptedPassword;
			try {
				decryptedPassword = aesCryptography.decrypt(encryptedString);
			} catch (Exception e) {

				return new ApiResponse<>(HttpStatus.BAD_REQUEST,
						"Exception occurred because Bad key is used during decryption", null);
			}

			if (decryptedPassword.equals(signin.getPassword())) {
				return new ApiResponse<>(HttpStatus.OK, "Logged in successfull!", null);
			} else {
				return new ApiResponse<>(HttpStatus.CONFLICT, "Password is incorrect!", null);
			}
		} else {
			return new ApiResponse<>(HttpStatus.NOT_FOUND, "Mobile Number is incorrect!", null);
		}

	}

	public AppUser loadUserByEmail(String email) {
		Optional<Users> user = userdao.findByEmailId(email);
		if (user.isPresent()) {

			return new AppUser(user.get());
		} else {
			throw new RuntimeException();
		}
	}

}
