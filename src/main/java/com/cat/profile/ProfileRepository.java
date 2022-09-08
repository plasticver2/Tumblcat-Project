package com.cat.profile;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cat.profile.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
