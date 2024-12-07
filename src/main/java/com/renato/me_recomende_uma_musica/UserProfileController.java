package com.renato.me_recomende_uma_musica;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renato.me_recomende_uma_musica.domain.UserProfile;
import com.renato.me_recomende_uma_musica.repository.UserProfileRepository;
import com.renato.me_recomende_uma_musica.security.domain.User;
import com.renato.me_recomende_uma_musica.security.repository.UserRepository;

//aqui terá só o get, o posto pode ser realizado quando o usuário for cadastrado
@RestController
@RequestMapping("/user-musicas")
public class UserProfileController {

	private final UserProfileRepository userProfileRepository;
	private final UserRepository userRepository;
	
	public UserProfileController(UserProfileRepository userProfileRepository, UserRepository userRepository) {
		this.userProfileRepository = userProfileRepository;
		this.userRepository = userRepository;
	}
	
	@GetMapping
	public ResponseEntity<UserProfile> obterListaMusicas(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) userRepository.findByLogin(authentication.getName());
		//UserProfile userProfile = userProfileRepository.//criar o find by user id
		return null;
	}
}
