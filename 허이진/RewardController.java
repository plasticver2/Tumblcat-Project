package com.cat.reward;

import java.math.BigDecimal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cat.project.ProjectService;
import com.cat.project.entity.Project;

import lombok.RequiredArgsConstructor;

@RequestMapping("/reward")
@RequiredArgsConstructor
@Controller
public class RewardController {
	private final ProjectService projectService;
	private final RewardService rewardService;
	private Project project;
	private Long pId;
	
	@RequestMapping(value = "/create/{pId}")
    public String detail(Model model, @PathVariable("pId") Long pId) {
		project = this.projectService.getProject(pId);
		this.pId = pId;
        return "reward_form";
    }
	
	@GetMapping("/create/{pId}")
	public String createAnswer(RewardForm rewardForm) {
		return "reward_form";
	}
	
	@PostMapping("/create/{pId}")
    public String createAnswer(Model model,
    		@RequestParam String rwName, @RequestParam String rwDesc,
    		@RequestParam BigDecimal rwMin, @RequestParam BigDecimal rwMax
    ) {
        // TODO: 답변을 저장한다. 
        this.rewardService.create(rwName, rwDesc, rwMin, rwMax, project);
        return String.format("redirect:/reward/create/%s", pId);
    }
}
