package com.renato.me_recomende_uma_musica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renato.me_recomende_uma_musica.domain.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long>{

}
