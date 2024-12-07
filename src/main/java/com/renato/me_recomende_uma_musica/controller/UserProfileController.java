package com.renato.me_recomende_uma_musica.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renato.me_recomende_uma_musica.controller.dto.Musica;
import com.renato.me_recomende_uma_musica.domain.UserProfile;
import com.renato.me_recomende_uma_musica.repository.UserProfileRepository;
import com.renato.me_recomende_uma_musica.security.domain.User;
import com.renato.me_recomende_uma_musica.security.repository.UserRepository;

import jakarta.transaction.Transactional;

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
	public ResponseEntity<List<String>> obterListaMusicas(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) userRepository.findByLogin(authentication.getName());
		UserProfile userProfile = userProfileRepository.findByUser(user);
		return ResponseEntity.ok(userProfile.getMusicas());
	}
	@PostMapping("/recomendar/{userId}")
	@Transactional
	public ResponseEntity<String> recomendarMusica(@RequestBody Musica musica, @PathVariable Long userId){
		Optional<UserProfile> optionalUserProfile = userProfileRepository.findById(userId);
		if(optionalUserProfile.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		UserProfile userProfile = optionalUserProfile.get();
		userProfile.recomendarMusica(musica.musica());
		return ResponseEntity.ok().build();
	}
}
