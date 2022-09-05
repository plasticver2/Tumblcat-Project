package com.cat.project;

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
public class ProjectStatus {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ps_id;
	
	@Column(nullable = false, length = 64)
	private String ps_name;
}
