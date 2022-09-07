package com.cat.reward;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RewardController {

	@GetMapping("/reward/create")
	public String reward() {
		return "reward_form";
	}
}
