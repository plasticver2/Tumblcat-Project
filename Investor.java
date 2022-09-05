package com.cat.investor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.CreatedDate;

import com.cat.account.Account;
import com.cat.project.Project;
import com.cat.reward.Reward;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Investor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long in_id;
	
	@ManyToOne
	@JoinColumn(nullable = false, name = "project", referencedColumnName = "p_id")
	private Project p_id;
	
	@ManyToOne
	@JoinColumn(nullable = false, name = "account", referencedColumnName = "a_id")
	private Account a_id;
	
	@Column(nullable = false)
	private BigDecimal in_pled;
	
	@CreatedDate
	private LocalDateTime in_ts;
	
	@OneToOne
	@JoinColumn(nullable = false, name = "reward", referencedColumnName = "rw_id")
	private Reward rw_id;
	
	@Column(nullable = false, length = 64)
	private String in_phone;
	
	@Column(nullable = false,columnDefinition = "TEXT")
	private String in_add;
	
	@Column(nullable = false, length = 64)
	private String in_pay;
	
	
}
