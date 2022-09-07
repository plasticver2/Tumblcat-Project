package com.cat.project;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cat.project.entity.Project;

import lombok.RequiredArgsConstructor;

@RequestMapping("/project")
@RequiredArgsConstructor
@Controller
public class ProjectController {
	private final ProjectService projectService;
	
	//db와 연결해주는 레퍼지토리를 가져와서 list를 조회
	//중간에 service 클래스를 추가해서 레퍼지토리에 직접 접근할 수 없도록 막아줌
	//model 클래스를 이용해서 가져온 list를 템플릿(html)에 전달 
	@RequestMapping("/list")
	public String list(Model model) {
		List<Project> projectList = this.projectService.getList();
		model.addAttribute("projectList", projectList);
		return "project_list";
	}
	
	@RequestMapping("/list/{pCate}")
	public String listCate(Model model, @PathVariable("pCate") String pCate) {
		List<Project> projectList = this.projectService.getCateList(pCate);
		model.addAttribute("projectList", projectList);
		return "project_list";
	}
	
	@RequestMapping(value = "/detail/{pId}")
	public String detail(Model model, @PathVariable("pId") Long pId) {
		Project project = this.projectService.getProject(pId);
		model.addAttribute("project", project);
		return "project_detail";
	}
	
	@GetMapping("/create")
	public String projectCreate(ProjectForm projectForm) {
		return "project_form";
	}
	
	@PostMapping("/create")
    public String projectCreate(
    		@Valid ProjectForm projectForm, BindingResult bindingResult
    ) {
		if (bindingResult.hasErrors()) {
            return "project_form";
        }
        // TODO 질문을 저장한다.
		this.projectService.create(
				projectForm.getPCate(),
				projectForm.getPName(),
				projectForm.getPDesc(), 
				projectForm.getPGoal(),
				projectForm.getPSdate(),
				projectForm.getPEdate(),
				projectForm.getPCreator()
		);
        return "redirect:/project/list"; // 질문 저장후 질문목록으로 이동
    }
	
	@GetMapping("/update")
	public String projectUpdate() {
		return "project_update";
	}
	
	@RequestMapping("/category")
	public String category() {
		return "category";
	}
	
	@RequestMapping("/liked")
	public String liked() {
		return "liked";
	}
	
	@RequestMapping("/notice")
	public String notice() {
		return "notice";
	}
	
}
