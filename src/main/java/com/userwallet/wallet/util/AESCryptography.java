package com.userwallet.wallet.util;

import javax.crypto.*;
import javax.crypto.spec.*;
import java.nio.charset.*;
import java.security.*;
import java.security.spec.*;
import java.util.*;

public class AESCryptography {

	private String password;
	private String algo = "AES";
	private String salt;
	private byte[] iv;

	public AESCryptography(String password, String salt, String iv) {
		this.password = password;
		this.salt = salt;
		this.iv = iv.getBytes();
	}

	public String encrypt(String data)
			throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException,
			IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeySpecException {
		SecretKeySpec key = generateKey();
		IvParameterSpec ivParameterSpec = generateIv();
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
		byte[] encryptedValueBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
		return Base64.getEncoder().encodeToString(encryptedValueBytes);

	}

	public String decrypt(String encryptedData)
			throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException,
			IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeySpecException {
		SecretKeySpec key = generateKey();
		IvParameterSpec ivParameterSpec = generateIv();
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);

		byte[] decodedVal = Base64.getDecoder().decode(encryptedData);
		byte[] decVal = cipher.doFinal(decodedVal);
		return new String(decVal);

	}

	private SecretKeySpec generateKey() throws InvalidKeySpecException, NoSuchAlgorithmException {
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
		SecretKey tmp = factory.generateSecret(spec);
		return new SecretKeySpec(tmp.getEncoded(), algo);

	}

	private IvParameterSpec generateIv() {
		return new IvParameterSpec(this.iv);
	}

}
