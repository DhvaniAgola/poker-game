package com.logmein.poker.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "player")
@DynamicUpdate
public class PlayerDomain implements Serializable {

	protected static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Long id;

	@Column(name = "game_name",nullable = false)
	private String playerName;

	@Column(name = "age")
	private int age;

	@Column(name = "country")
	private String country;

	@Column(name = "active")
	private boolean active;


	@OneToMany(mappedBy="playerDomain", fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
	private Set<Game_PlayerDomain> players;
}

