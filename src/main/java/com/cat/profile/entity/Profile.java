package com.cat.profile.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.cat.account.entity.Account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Profile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long pfId;
	
	 @Column(nullable = false, columnDefinition = "TEXT")
	 public String pfDesc;
	 
	 @Column(nullable = false, columnDefinition = "TEXT")
	 public String pfName;
}
