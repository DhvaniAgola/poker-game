package com.logmein.poker.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "game")
public class GameDomain implements Serializable {

	protected static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Long id;

	@Column(name = "game_name",nullable = false)
	private String gameName;

	@Column(name = "active")
	private boolean active;

	@OneToMany(mappedBy="gameDomain", fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
	private Set<Game_PlayerDomain> games;

	@OneToMany(mappedBy="gameDomain", fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
	private Set<GameDeckDomain> gameDecks;


}
