package com.cat.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/account")
@Controller
public class AccountController {

	@RequestMapping("/signin")
	public String login() {
		return "login";
	}
	
	@GetMapping("/signup")
	public String signup() {
		return "signup";
	}
	
	@RequestMapping("/resetpwd")
	public String resetpwd() {
		return "resetpassword";
	}
	
	@GetMapping("/findpwd")
	public String findpwd() {
		return "findpassword";
	}
	
	@RequestMapping("/myproject")
	public String myproject() {
		return "myproject";
	}
	
	@RequestMapping("/setaccount")
	public String setaccount() {
		return "setting_account_a";
	}
	
	@GetMapping("/setaccountForm")
	public String setaccount_form() {
		return "setting_account_b";
	}
	
	@RequestMapping("/setaddress")
	public String setaddress() {
		return "setting_address_a";
	}
	
	@GetMapping("/setaddressForm")
	public String setaddress_form() {
		return "setting_address_b";
	}
	
	@RequestMapping("/setnotice")
	public String setnotice() {
		return "setting_notice_a";
	}
	
	@GetMapping("/setnotice_form")
	public String setnotice_form() {
		return "setting_notice_b";
	}
	
	@GetMapping("/profile")
	public String profile() {
		return "profile";
	}
}
