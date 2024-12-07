package com.renato.me_recomende_uma_musica.domain;

import java.util.List;

import com.renato.me_recomende_uma_musica.security.domain.User;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ElementCollection
	private List <String> musicas;
	@OneToOne
	private User user;
	
	public void recomendarMusica(String musica) {
		this.musicas.add(musica);
	}
}
