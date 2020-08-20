package com.logmein.poker.constants;

public class EndPoints {

	public static final String CREATE_GAME="game";
	public static final String DELETE_GAME="game";
	public static final String CREATE_PLAYER="player";
	public static final String ADD_PLAYER_TO_GAME="game/{gameId}/player/{playerId}";
	public static final String CREATE_GAME_DECK="game_deck/{gameId}/{noOfDeck}";
	public static final String DEAL_CARDS_TO_PLAYER="deal_cards/{gameId}/{playerId}";
	public static final String SHUFFLE_GAME_DECK="shuffle/{gameId}";
	public static final String GET_CARDS_OF_PLAYER="cards/{gameId}/{playerId}";
	public static final String GET_COUNT_OF_CARDS_IN_DECK="cards/count/{gameId}/{playerId}";
	public static final String GET_CARDS_IN_DECK_IN_SORTED_ORDER="cards/sorted/{gameId}";
}
