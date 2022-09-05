package com.cat.reward;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.cat.project.Project;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Reward {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long rw_id;
	
	
	@ManyToOne
	@JoinColumn(nullable = false, name = "project", referencedColumnName = "p_id")
	private Project p_id;
	
	@Column(nullable = false)
	private BigDecimal rw_min;
	
	@Column(nullable = false)
	private BigDecimal rw_max;
	
	@Column(nullable = false, length = 64)
	private String rw_name;
	
	@Column(nullable = false, columnDefinition = "TEXT") 
	private String rw_desc;
	
	
}
