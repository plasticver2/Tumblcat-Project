package com.cat.project;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cat.project.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

	@Query("select p from Project p where p_cate = :pCate")
	List<Project> findAllByCate(@Param("pCate") String pCate);

}
