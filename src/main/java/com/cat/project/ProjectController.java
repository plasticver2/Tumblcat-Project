package com.cat.project;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
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
import com.cat.project.entity.ProjectStatus;
import com.cat.project.img.Image;
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
	public String list(Model model,@AuthenticationPrincipal User user,@RequestParam(value = "kw", defaultValue = "") String kw) {
		List<Project> projectList;
		if(kw != null) {
			projectList= this.projectService.searchkw(kw);
		}else {
			projectList = this.projectService.getList();
		}
		
    	if(user == null) {
        	model.addAttribute("user", "로그인");
    	}else {
        	Account a = this.accountService.getAccount(user.getUsername());
        	model.addAttribute("user", a);
    	}
		
		ArrayList<Project> list = this.projectService.comparedDate(projectList);
		List<Project> createdList = this.projectService.createdList(projectList);
    	 
		model.addAttribute("projectList", list);
		model.addAttribute("createdList", createdList);
		return "project_list";
	}
	
	
	@RequestMapping("/list/{pCate}")
	public String listCate(Model model, @PathVariable("pCate") String pCate) {
		List<Project> projectList = this.projectService.getCateList(pCate);
		
		ArrayList<Project> list = this.projectService.comparedDate(projectList);
		List<Project> createdList = this.projectService.createdList(projectList);
    	 
		model.addAttribute("projectList", list);
		model.addAttribute("createdList", createdList);
		return "project_list";
	}
	
	@RequestMapping("/prelaunching")
	public String prelaunching(Model model,@AuthenticationPrincipal User user,@RequestParam(value = "kw", defaultValue = "") String kw) {
		List<Project> projectList;
		if(!kw.isEmpty()) {
			projectList= this.projectService.searchkw(kw);
		}else {
			projectList = this.projectService.getList();
		}
		
    	if(user == null) {
        	model.addAttribute("user", "로그인");
    	}else {
        	Account a = this.accountService.getAccount(user.getUsername());
        	model.addAttribute("user", a);
    	}

		List<Project> createdList = this.projectService.createdList(projectList);

		model.addAttribute("createdList", createdList);
		return "project_prelaunch";
	}
	
	@RequestMapping(value = "/detail/{pId}")
	public String detail(Model model, @PathVariable("pId") Long pId,@RequestParam(value = "ref", defaultValue = "") String ref) {
		LocalDate now = LocalDate.now();
		
		Project project = this.projectService.getProject(pId);
		
		Long start = project.getPSdate().until(now, ChronoUnit.DAYS);
		Long end = now.until(project.getPEdate(), ChronoUnit.DAYS);
		LocalDate payDate = project.getPEdate().plusDays(1);
		
		model.addAttribute("project", project);
		
		if(!ref.isEmpty()) {
			model.addAttribute("start", start);
			
			return "project_prelaunch_detail";
		}else {
			model.addAttribute("end",end);
			model.addAttribute("payDate",payDate);
			
			return "project_detail";
		}
		

		
	}
	
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/create")
	public String projectCreate(ProjectForm projectForm) {
		return "project_form";
	}
	
	@PostMapping("/create")
    public String projectCreate(
    		@RequestPart MultipartFile file,
    		@Valid ProjectForm projectForm, 
    		BindingResult bindingResult,
    		Principal principal
    	    ) throws IOException{
		if (bindingResult.hasErrors()) {
            return "project_form";
		}
		
		String fileurl = imageService.uploadfile(file);
		String storefile = this.imageService.storedfile(file.getOriginalFilename());
		Account account = this.accountService.getAccount(principal.getName());
		
		this.imageService.filesave(file.getOriginalFilename(),storefile,fileurl, projectForm.getImgDesc());
		Image image = this.imageService.findImgid(storefile);
		
		ProjectStatus PsId =this.projectService.findPsId("created");
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
				account,
				PsId
		);
		//return "reward_form";
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
