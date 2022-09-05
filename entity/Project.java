package com.cat.project;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ColumnDefault;

import com.cat.account.Account;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long p_id;
	
	@Column(nullable = false, updatable = true, length = 64)
	private String p_name;
	
	@ManyToOne
	@JoinColumn(nullable = false, name = "account", referencedColumnName = "a_id")
	private Account a_id;
	
	@Column(nullable = false, columnDefinition = "TEXT")
	private String  p_creator;
	
    @Column(nullable = false, updatable = true, 
    		columnDefinition = "TEXT")
	private String p_desc;
    
    @Column(nullable = false, updatable = true, length = 64)
	private String p_cate;

    @Column(nullable = false, updatable = true)
	private BigDecimal  p_goal;
	
    @ColumnDefault("0") 
    @Column(nullable = false, updatable = true)
	private BigDecimal  p_pled;

    @ColumnDefault("0") 
    @Column(nullable = false)
	private Integer p_ins;
    
    
	private LocalDateTime p_sdate;
	
    
	private LocalDateTime p_edate;
	
	
	@OneToOne
	@JoinColumn(nullable = false, name = "project_status", referencedColumnName = "ps_id")
	private ProjectStatus projectStatus; 
	
}
