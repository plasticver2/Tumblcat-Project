package com.cat.project.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private Long pId;
	
	@Column(nullable = false, updatable = true, length = 64)
	private String pName;
	
//	@ManyToOne
//	@JoinColumn(nullable = false, name = "account", referencedColumnName = "aId")
//	private Account account;
	
	//창작자 소개
	@Column(nullable = false, columnDefinition = "TEXT")
	private String  pCreator;
	
    @Column(nullable = false, columnDefinition = "TEXT")
	private String pDesc;
    
    @Column(nullable = false, length = 64)
	private String pCate;

    @Column(nullable = false)
	private BigDecimal  pGoal;
	
    @ColumnDefault("0") 
	private BigDecimal  pPled;

    @ColumnDefault("0") 
	private Integer pIns;
    
	private  LocalDate pSdate;

	private  LocalDate pEdate;
	
//	@OneToOne
//	@JoinColumn(nullable = false, name = "projectStatus",referencedColumnName = "psId")
//	private ProjectStatus projectStatus; 
	
	@OneToOne
	private Image image;
	
}
