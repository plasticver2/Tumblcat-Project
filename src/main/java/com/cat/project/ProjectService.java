package com.cat.project;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cat.DataNotFoundException;
import com.cat.project.entity.Project;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProjectService {
	private final ProjectRepository projectRepository;
	
	public List<Project> getList(){
		return this.projectRepository.findAll();
	}
	
	public Project getProject(Long pId) {
		Optional<Project> project = this.projectRepository.findById(pId);
		if(project.isPresent()) {
			return project.get();
		}else {
			throw new DataNotFoundException("project not found");
		}
	}
	
	public void create(String pCate, String pName, String pDesc, 
			BigDecimal pGoal, LocalDate pSdate, LocalDate pEdate, String pCreator)
	{
		Project p = new Project();
		p.setPCate(pCate);
		p.setPName(pName);
        p.setPDesc(pDesc);
        p.setPGoal(pGoal);
        p.setPSdate(pSdate);
        p.setPEdate(pEdate);
        p.setPCreator(pCreator);
        this.projectRepository.save(p);
	}
}