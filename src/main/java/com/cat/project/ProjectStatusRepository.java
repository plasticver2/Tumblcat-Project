package com.cat.project;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cat.project.entity.Project;
import com.cat.project.entity.ProjectStatus;

public interface ProjectStatusRepository extends JpaRepository<ProjectStatus,Long> {
	
	ProjectStatus findByPsName(String status);
	
}
