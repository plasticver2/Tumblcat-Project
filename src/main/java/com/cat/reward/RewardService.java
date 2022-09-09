package com.cat.reward;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.cat.project.entity.Project;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RewardService {
	private final RewardRepository rewardRepository;
	
	public void create(String rwName, String rwDesc, BigDecimal rwMin,
			BigDecimal rwMax, Project project)
	{
		
		Reward r = new Reward();
		r.setRwName(rwName);
		r.setRwDesc(rwDesc);
		r.setRwMin(rwMin);
		r.setRwMax(rwMax);
		r.setProject(project);
		this.rewardRepository.save(r);
	}
}
