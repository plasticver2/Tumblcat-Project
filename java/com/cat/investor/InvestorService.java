package com.cat.investor;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.cat.account.entity.Account;
import com.cat.project.entity.Project;
import com.cat.reward.Reward;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class InvestorService {
	private final InvestorRepository investorRepository;
	
	public void create(BigDecimal inPled, LocalDate inTs, String inPhone,
			String inAdd, String inPay, Project project, Account account,
			Reward reward)
	{
		Investor i = new Investor();
		i.setInPled(inPled);
		i.setInTs(inTs);
		i.setInPhone(inPhone);
		i.setInAdd(inAdd);
		i.setInPay(inPay);
		i.setProject(project);
		i.setAccount(account);
		i.setReward(reward);
		this.investorRepository.save(i);
	}
}