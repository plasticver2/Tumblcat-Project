package com.cat.account.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ColumnDefault;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long aId;
	
	@Column(nullable = false, columnDefinition = "TEXT")
	private String aPw;
	
	@Column(nullable = false, length = 64)
	private String aEmail;
	
	@OneToOne
	@ColumnDefault("2")
	@JoinColumn(name = "arId", referencedColumnName = "arId")
	private AccountRole accountRole;
	
	@Column(nullable = false, unique = true, columnDefinition = "TEXT")
	private String aName;
	
	
}