package com.cat.account;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cat.account.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	
}
