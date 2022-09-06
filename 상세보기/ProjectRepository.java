package com.cat.project;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cat.project.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
