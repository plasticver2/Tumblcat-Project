package com.cat.reward;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cat.project.ProjectService;
import com.cat.project.entity.Project;

import lombok.RequiredArgsConstructor;

@RequestMapping("/project")
@RequiredArgsConstructor
@Controller
public class RewardController {
	private final ProjectService projectService;
	private final RewardService rewardService;
	
	@RequestMapping(value="/reward/{pId}")
	public String reward(Model model, @PathVariable("pId") Long pId){
		Project project = this.projectService.getProject(pId);
		model.addAttribute("project", project);
		return "project_detail";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/reward/create/{pId}")
	public String reward(
			@Valid RewardForm rewardForm, BindingResult bindingResult,
			Model model, @PathVariable("pId") Long pId
	) {
			if (bindingResult.hasErrors()) {
				return "project_detail";
			}
			Project project = this.projectService.getProject(pId);
			
		    // TODO 질문을 저장한다.
			this.rewardService.create(
				rewardForm.getRwName(),
				rewardForm.getRwDesc(),
				rewardForm.getRwMin(), 
				rewardForm.getRwMax(),
				project
			);
			return String.format("redirect:/project/detail/%s", pId); // 질문 저장후 질문목록으로 이동
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "reward/delete/{rwId}")
	public String deleteReward(
			Principal principal, @PathVariable("rwId") Long rwId
	) {
			Reward reward = this.rewardService.getReward(rwId);
			Project project = reward.getProject();
		    // TODO 질문을 저장한다.
			this.rewardService.delete(reward);
			return String.format("redirect:/project/detail/%s", project.getPId()); // 질문 저장후 질문목록으로 이동
	}
}