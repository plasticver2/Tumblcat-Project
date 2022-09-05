package com.cat.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long a_id;
	
	@Column(nullable = false, length = 20)
	private String a_pw;
	
	@Column(nullable = false, length = 64)
	private String a_email;
	
	@OneToOne
	@JoinColumn(name = "ar_id", referencedColumnName = "ar_id")
	private AccountRole ar_id;
	
	@Column(nullable = false, length = 20)
	private String a_name;
	
	
}
