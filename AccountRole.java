package com.cat.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class AccountRole {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ar_id;
	
	@Column(nullable = false, length = 20)
	private String ar_name;
}
