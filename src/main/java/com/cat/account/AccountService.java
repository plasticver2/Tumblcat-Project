package com.cat.account;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cat.DataNotFoundException;
import com.cat.account.entity.Account;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AccountService {

	private final AccountRepository accountRepository;
	private final PasswordEncoder passwordEncoder;
	
	public Account create(String aName, String aEmail, String aPw) {
		Account account = new Account();
		account.setAName(aName);
		account.setAEmail(aEmail);
		account.setAPw(passwordEncoder.encode(aPw));
		this.accountRepository.save(account);
		return account;
	}
	
	public Account getAccount(String aEmail) {
		Optional<Account> account = this.accountRepository.findByaEmail(aEmail);
		if(account.isPresent()) {
			return account.get();
		}else {
			throw new DataNotFoundException("account not found");
		}
	}
	
}