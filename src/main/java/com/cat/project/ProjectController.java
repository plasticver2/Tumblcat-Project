package com.cat.project;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.cat.account.AccountService;
import com.cat.account.entity.Account;
import com.cat.project.entity.Project;
import com.cat.project.img.ImageService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/project")
@RequiredArgsConstructor
@Controller
public class ProjectController {
	private final ProjectService projectService;
	private final ImageService imageService;
	private final AccountService accountService;
	
	//db와 연결해주는 레퍼지토리를 가져와서 list를 조회
	//중간에 service 클래스를 추가해서 레퍼지토리에 직접 접근할 수 없도록 막아줌
	//model 클래스를 이용해서 가져온 list를 템플릿(html)에 전달 
	@RequestMapping("/list")
	public String list(Model model, @RequestParam(value = "kw", defaultValue = "") String kw) {
		List<Project> projectList;
		if(kw != null) {
			projectList = this.projectService.searchkw(kw);
		}else {
			projectList = this.projectService.getList();
		}

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
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/create")
	public String projectCreate(ProjectForm projectForm) {
		return "project_form";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create")
    public String projectCreate(
    		@RequestPart MultipartFile file,@Valid ProjectForm projectForm, BindingResult bindingResult, Principal principal
    	    ) throws IOException{
		if (bindingResult.hasErrors()) {
            return "project_form";
		}
		
		String fileurl = imageService.uploadfile(file);
		String storefile = this.imageService.storedfile(file.getOriginalFilename());
		
		Account account = this.accountService.getAccount(principal.getName());

		this.imageService.filesave(file.getOriginalFilename(),storefile,fileurl, projectForm.getImgDesc());
		com.cat.project.img.Image image = this.imageService.findImgid(storefile);
		
        // TODO 질문을 저장한다.
		this.projectService.create(
				projectForm.getPCate(),
				projectForm.getPName(),
				projectForm.getPDesc(), 
				projectForm.getPGoal(),
				projectForm.getPSdate(),
				projectForm.getPEdate(),
				projectForm.getPCreator(),
				image,
				account
		);
		return "reward_form";
        //return "redirect:/project/list"; // 질문 저장후 질문목록으로 이동
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
