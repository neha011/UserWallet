package com.userwallet.wallet.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.userwallet.wallet.entities.Transactions;

public interface TransactionsDAO extends CrudRepository<Transactions, Long> {

	@Query(value = "Select * from transactions a where a.user_id= :user", nativeQuery = true)
	Iterable<Transactions> findByUserId(@Param("user") Long user);

}
