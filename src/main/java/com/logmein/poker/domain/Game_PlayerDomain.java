package com.logmein.poker.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "game_player")
public class Game_PlayerDomain implements Serializable {

	protected static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Long id;

	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="player_id")
	private PlayerDomain playerDomain;

	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="game_id")
	private GameDomain gameDomain;

	@OneToMany(mappedBy="game_playerDomain", fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
	private Set<Game_Player_CardsDomain> game_player_cardsDomains;
}

