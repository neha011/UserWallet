package com.userwallet.wallet.util;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.userwallet.wallet.dao.RoleDAO;
import com.userwallet.wallet.dao.UserDAO;
import com.userwallet.wallet.entities.Roles;
import com.userwallet.wallet.entities.Users;

@Component
public class Bootstrap implements ApplicationRunner {

	@Value("${encryption.password}")
	private String password;
	@Value("${encryption.salt}")
	private String salt;
	@Value("${encryption.iv}")
	private String iv;

	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	UserDAO userdao;

	@Autowired
	RoleDAO roledao;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		Iterable<Roles> r1 = roledao.findAll();
		if (!r1.iterator().hasNext()) {
			Roles role1 = new Roles();
			role1.setAuthority("ADMIN");
			role1.setId(1l);
			Users user = new Users();
			user.setFirstName("admin");
			user.setLastName("admin");
			user.setEmailId("admin@mail.com");
			user.setCreatedAt(LocalDateTime.now());
			user.setPassword(passwordEncoder.encode("admin@123"));
			user.setRoles(Arrays.asList(role1));
			userdao.save(user);

			Roles role2 = new Roles(2l, "USER");

			roledao.save(role1);
			roledao.save(role2);
		}
	}
}