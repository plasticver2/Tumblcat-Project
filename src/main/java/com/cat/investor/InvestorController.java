package com.cat.investor;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cat.account.AccountService;
import com.cat.account.entity.Account;
import com.cat.project.ProjectService;
import com.cat.project.entity.Project;
import com.cat.reward.Reward;
import com.cat.reward.RewardService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/project/invest")
@RequiredArgsConstructor
@Controller
public class InvestorController {
	private final RewardService rewardService;
	private final AccountService accountService;
	private final ProjectService projectService;
	private final InvestorService investorSerivce;
	
	@RequestMapping(value = "/{rwId}")
	public String invest(Model model, @PathVariable("rwId") Long rwId) {
		Reward reward = this.rewardService.getReward(rwId);
		model.addAttribute("reward", reward);
		return "project_invest";
	}
	
	@PostMapping("/{rwId}")
	public String invest(
			@Valid InvestorForm investorForm, BindingResult bindingResult,
			Model model, @PathVariable("rwId") Long rwId, Principal principal
	) {
		if (bindingResult.hasErrors()) {
			return "project_detail";
		}
			Account account = this.accountService.getAccount(principal.getName());
			Reward reward = this.rewardService.getReward(rwId);
			Project project = this.projectService.getProject(reward.getProject().getPId());
			
			BigDecimal inPled = reward.getRwMin();
			LocalDate inTs = LocalDate.now();
			
		    // TODO 질문을 저장한다.
			this.investorSerivce.create(
					inPled,
					inTs,
					investorForm.getInPhone(),
					investorForm.getInAdd(),
					investorForm.getInPay(),
					project,
					account,
					reward
			);
			return "complete";
			//return String.format("redirect:/project/detail/%s", rwId); // 질문 저장후 질문목록으로 이동
	}
	
	@RequestMapping("/pledged")
	public String pledged() {
		return "pledged";
	}
	
	@RequestMapping("/complete")
	public String complete() {
		return "complete";
	}
	
}