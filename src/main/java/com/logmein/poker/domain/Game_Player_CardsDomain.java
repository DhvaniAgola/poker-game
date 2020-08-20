package com.logmein.poker.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "game_player_cards")
public class Game_Player_CardsDomain implements Serializable {

	protected static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Long id;
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="game_player_id")
	private Game_PlayerDomain game_playerDomain;

	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="card_id")
	private GameDeckDomain cardId;

}

