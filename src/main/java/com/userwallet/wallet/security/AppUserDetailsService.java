package com.userwallet.wallet.security;

import com.userwallet.wallet.services.UserWalletImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserWalletImpl userwalletimpl;

    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
        String encryptedPassword = passwordEncoder.encode("pass");
        System.out.println("Trying to authenticate user ::" + emailId);
        System.out.println("Encrypted Password ::"+encryptedPassword);
        UserDetails userDetails = userwalletimpl.loadUserByEmail(emailId);
        return userDetails;
    }
}