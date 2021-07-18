package com.userwallet.wallet.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.userwallet.wallet.entities.Wallet;

@Repository
public interface WalletDao extends CrudRepository<Wallet, Long>{
	@Query(value = "Select * from wallet a where a.user_id= :user",nativeQuery = true)
	Optional<Wallet> findByUserId(@Param("user") Long userid);
}
