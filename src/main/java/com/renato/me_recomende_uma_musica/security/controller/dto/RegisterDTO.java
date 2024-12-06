package com.renato.me_recomende_uma_musica.security.controller.dto;

import com.renato.me_recomende_uma_musica.security.domain.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {

}