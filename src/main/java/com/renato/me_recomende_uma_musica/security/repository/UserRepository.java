package com.renato.me_recomende_uma_musica.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.renato.me_recomende_uma_musica.security.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{

	UserDetails findByLogin(String login);
}