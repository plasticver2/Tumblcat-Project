package com.cat.project;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;

import com.cat.account.Account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ProjectUpdate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long u_id;
	
	@ManyToOne
	@JoinColumn(nullable = false, name = "project", referencedColumnName = "p_id")
	private Project p_id;
	
	@ManyToOne
	@JoinColumn(nullable = false, name = "account", referencedColumnName = "a_id")
	private Account a_id;
	
	@Column(nullable = false, columnDefinition = "TEXT")
	private String u_text;
	
	@CreatedDate
	private  LocalDateTime u_ts;
}
