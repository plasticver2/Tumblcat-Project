package com.cat.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Image {

	//기본키 이미지 ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "imgId")
	private Long imgId;
	
	//외래키 프로젝트 ID
	@OneToOne(mappedBy = "image")
	private Project project;
	
	//이미지 링크
	@Column(nullable = false)
	private String imgLink;
	
	//이미지 소개
	@Column(nullable = false, columnDefinition = "TEXT")
	private String imgDesc;
}
