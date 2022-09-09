package com.cat.reward;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cat.project.ProjectService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/reward")
@RequiredArgsConstructor
@Controller
public class RewardController {
	private final RewardService rewardService;
	private final ProjectService projectService;
	
	@GetMapping(value = "/create/{pId}")
	public String reward(RewardForm rewardForm) {
		return "reward_form";
	}
	
	@PostMapping(value = "/create/{pId}")
	public String reward(Model model, @PathVariable("pId") Long pId, 
			@Valid RewardForm rewardForm, BindingResult bindingResult
	) {
			if (bindingResult.hasErrors()) {
				return "reward_form";
			}

			this.rewardService.create(
				rewardForm.getRwName(),
				rewardForm.getRwDesc(),
				rewardForm.getRwMin(), 
				rewardForm.getRwMax(),
				this.projectService.getProject((Long) pId)
			);
		return "redirect:/project/list"; // 질문 저장후 질문목록으로 이동
	}
}
