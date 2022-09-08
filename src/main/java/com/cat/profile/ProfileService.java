package com.cat.profile;

import org.springframework.stereotype.Service;

import com.cat.profile.entity.Profile;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProfileService {

	private final ProfileRepository profileRepository;
	
	public void create(String pfDesc, String pfName) {
		Profile pf = new Profile();
		pf.setPfDesc(pfDesc);
		pf.setPfName(pfName);
		this.profileRepository.save(pf);
	}
}
