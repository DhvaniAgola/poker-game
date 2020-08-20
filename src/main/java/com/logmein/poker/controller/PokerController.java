package com.logmein.poker.controller;

import com.logmein.poker.constants.EndPoints;
import com.logmein.poker.domain.GameDomain;
import com.logmein.poker.domain.PlayerDomain;
import com.logmein.poker.response.Response;
import com.logmein.poker.service.impl.PokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
public class PokerController {

	private final PokerService pokerService;

	@Autowired
	public PokerController(PokerService pokerService) {
		this.pokerService = pokerService;
	}

	@GetMapping(path="checkHealth")
	String checkHealth(){
		return "I am UP";
	}

	@PostMapping(value = EndPoints.CREATE_GAME, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	ResponseEntity createGame(@RequestBody @Valid GameDomain gameDomain) {
//		GameDomain gameDomain= GameMapper.INSTANCE.gameDtoToDomain(gameDto);
		Response response=pokerService.createGame(gameDomain);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PutMapping(value = EndPoints.DELETE_GAME, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	ResponseEntity deleteGame(@PathVariable(value = "gameId") Long gameId) {
		Response response=pokerService.deleteGame(gameId);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PostMapping(value = EndPoints.CREATE_PLAYER, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	ResponseEntity createPlayer(@RequestBody @Valid PlayerDomain playerDomain) {
//		GameDomain gameDomain= GameMapper.INSTANCE.gameDtoToDomain(gameDto);
		Response response=pokerService.createPlayer(playerDomain);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PostMapping(value = EndPoints.ADD_PLAYER_TO_GAME, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	ResponseEntity createPlayer(@PathVariable(value = "playerId") Long playerId,@PathVariable(value = "gameId") Long gameId) {
//		GameDomain gameDomain= GameMapper.INSTANCE.gameDtoToDomain(gameDto);
		Response response=pokerService.addPlayerToGame(playerId,gameId);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PostMapping(value = EndPoints.CREATE_GAME_DECK, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	ResponseEntity createGameDeck(@PathVariable(value = "noOfDeck") Long noOfDeck,@PathVariable(value = "gameId") Long gameId) {
		Response response=pokerService.createGameDeck(noOfDeck,gameId);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PutMapping(value = EndPoints.DEAL_CARDS_TO_PLAYER, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	ResponseEntity dealCardsToPlayer(@PathVariable(value = "playerId") Long playerId,@PathVariable(value = "gameId") Long gameId) {
		Response response=pokerService.dealCardsToPlayer(playerId,gameId);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PutMapping(value = EndPoints.SHUFFLE_GAME_DECK, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	ResponseEntity shuffleGameDeck(@PathVariable(value = "gameId") Long gameId) {
		Response response=pokerService.shuffleDeck(gameId);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}


	@GetMapping(value = EndPoints.GET_CARDS_OF_PLAYER, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	ResponseEntity shuffleGameDeck(@PathVariable(value = "playerId") Long playerId,@PathVariable(value = "gameId") Long gameId) {
		Response response=pokerService.getCardsOfPlayer(gameId,playerId);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping(value = EndPoints.GET_COUNT_OF_CARDS_IN_DECK, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	ResponseEntity getCountOfCardsInDeck(@PathVariable(value = "playerId") Long playerId,@PathVariable(value = "gameId") Long gameId) {
		Response response=pokerService.getCountOfCardsInDeck(gameId);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping(value = EndPoints.GET_CARDS_IN_DECK_IN_SORTED_ORDER, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	ResponseEntity getCardsInDeckSortedOrder(@PathVariable(value = "playerId") Long playerId,@PathVariable(value = "gameId") Long gameId) {
		Response response=pokerService.getCardsInDeckInSortedOrder(gameId);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
