package com.userwallet.wallet.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.userwallet.wallet.entities.Roles;

@Repository
public interface RoleDAO extends CrudRepository<Roles, Long>{


}
