package com.renato.me_recomende_uma_musica.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renato.me_recomende_uma_musica.domain.UserProfile;
import com.renato.me_recomende_uma_musica.repository.UserProfileRepository;
import com.renato.me_recomende_uma_musica.security.controller.dto.AuthenticationDTO;
import com.renato.me_recomende_uma_musica.security.controller.dto.LoginResponseDTO;
import com.renato.me_recomende_uma_musica.security.controller.dto.RegisterDTO;
import com.renato.me_recomende_uma_musica.security.domain.User;
import com.renato.me_recomende_uma_musica.security.repository.UserRepository;
import com.renato.me_recomende_uma_musica.security.service.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private UserProfileRepository userProfileRepository;
	
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody AuthenticationDTO data) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		var token = tokenService.generateToken((User) auth.getPrincipal());
		return ResponseEntity.ok(new LoginResponseDTO(token));
	}
	
	@PostMapping("/register")
	public ResponseEntity register(@RequestBody RegisterDTO data) {
		if(this.userRepository.findByLogin(data.login()) != null)
			return ResponseEntity.badRequest().build();
		String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
		User newUser = new User(data.login(), encryptedPassword, data.role());
		this.userRepository.save(newUser);
		
		UserProfile userProfile = new UserProfile();
		userProfile.setUser(newUser);
		this.userProfileRepository.save(userProfile);
		
		return ResponseEntity.ok().build();
	}
	//eu estava criando o novo endpoint
}