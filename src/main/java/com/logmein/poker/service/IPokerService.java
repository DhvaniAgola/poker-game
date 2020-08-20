package com.logmein.poker.service;

import com.logmein.poker.domain.GameDomain;
import com.logmein.poker.domain.PlayerDomain;
import com.logmein.poker.response.Response;

public interface IPokerService {
	Response createGame(GameDomain gameDomain);
	Response deleteGame(Long gameId);

	Response createPlayer(PlayerDomain playerDomain);

	Response deletePlayer(Long playerId);

	Response addPlayerToGame(Long playerId, Long gameId);

	Response createGameDeck(Long noOfDeck, Long gameId);

	Response dealCardsToPlayer(Long playerId, Long gameId);

	Response shuffleDeck(Long gameId);

	Response getCardsOfPlayer(Long gameId, Long playerId);

	Response getCountOfCardsInDeck(Long gameId);

	Response getCardsInDeckInSortedOrder(Long gameId);

}
