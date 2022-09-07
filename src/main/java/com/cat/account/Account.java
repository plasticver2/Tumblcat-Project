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
	private long aId;
	
	@Column(nullable = false, length = 20)
	private String aPw;
	
	@Column(nullable = false, length = 64)
	private String aEmail;
	
	@OneToOne
	@JoinColumn(name = "arId", referencedColumnName = "arId")
	private AccountRole accountRole;
	
	@Column(nullable = false, length = 20)
	private String aName;
}
