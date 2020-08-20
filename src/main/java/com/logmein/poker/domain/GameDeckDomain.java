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
@Table(name = "game_deck")
public class GameDeckDomain implements Serializable {

	protected static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Long id;

	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="game_id")
	private GameDomain gameDomain;
	@Column(name = "card_id")
	private Long cardId;

	@Column(name = "shuffle_id")
	private int shuffleId;

	@Column(name = "in_deck")
	private boolean inDeck;

	@OneToOne(mappedBy="cardId", fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
	private Game_Player_CardsDomain card_id;
}
