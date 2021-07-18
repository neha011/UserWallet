package com.userwallet.wallet.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import com.userwallet.wallet.entities.Users;

@Repository
public interface UserDAO extends CrudRepository<Users, Long> {

	@Query(value = "Select * from users a where a.user_id= :userid", nativeQuery = true)
	Optional<Users> findById(@Param("userid") Long user);

	@Query(value = "Select * from users a where a.mobile_number= :mobNum", nativeQuery = true)
	Optional<Users> findByMobileNumb(@Param("mobNum") Long mobNum);

	Optional<Users> findByEmailId(String email);

}
