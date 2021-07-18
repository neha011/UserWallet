package com.userwallet.wallet.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.userwallet.wallet.dao.RoleDAO;
import com.userwallet.wallet.dao.UserDAO;

@Component
public class Bootstrap implements ApplicationRunner {

	@Value("${encryption.password}")
	private String password;
	@Value("${encryption.salt}")
	private String salt;
	@Value("${encryption.iv}")
	private String iv;

	@Autowired
	UserDAO userdao;

	@Autowired
	RoleDAO roledao;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		/*
		 * 
		 * Roles role1 = new Roles(); role1.setAuthority("ADMIN"); role1.setId(1l);
		 * Users user = new Users(); user.setFirstName("admin");
		 * user.setLastName("admin"); user.setEmailId("admin@mail.com");
		 * user.setCreatedAt(LocalDateTime.now()); AESCryptography aesCryptography = new
		 * AESCryptography(password, salt, iv); String encyptedPassword =
		 * aesCryptography.encrypt("admin@123");
		 * 
		 * user.setPassword(encyptedPassword); user.setRoles(Arrays.asList(role1));
		 * userdao.save(user);
		 * 
		 * Roles role2 = new Roles(2l, "USER");
		 * 
		 * roledao.save(role1); roledao.save(role2);
		 */}
}