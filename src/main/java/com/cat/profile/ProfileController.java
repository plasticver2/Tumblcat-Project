package com.cat.profile;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cat.account.AccountService;
import com.cat.profile.entity.Profile;

import lombok.RequiredArgsConstructor;

@RequestMapping("/account/profile")
@RequiredArgsConstructor
@Controller
public class ProfileController {

	private final AccountService accountService;
	private final ProfileService profileService;
	
	@RequestMapping(value = "/{pfId}")
	private String profile(Model model, @PathVariable("pfId") long pfId) {
		return "profile";
	}
}
