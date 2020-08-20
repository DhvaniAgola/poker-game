package com.logmein.poker.service.impl;

import com.logmein.poker.Repository.*;
import com.logmein.poker.constants.Deck;
import com.logmein.poker.constants.ResponseMessages;
import com.logmein.poker.domain.*;
import com.logmein.poker.response.Response;
import com.logmein.poker.service.IPokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class PokerService implements IPokerService {

	private final GameRepo gameRepo;
	private final PlayerRepo playerRepo;
	private final Game_PlayerRepo game_playerRepo;
	private final GameDeckRepo gameDeckRepo;
	private final Game_Player_CardsRepo game_player_cardsRepo;



	@Autowired
	public PokerService(GameRepo gameRepo, PlayerRepo playerRepo, Game_PlayerRepo game_playerRepo, GameDeckRepo gameDeckRepo, Game_Player_CardsRepo game_player_cardsRepo) {
		this.gameRepo = gameRepo;
		this.playerRepo = playerRepo;
		this.game_playerRepo = game_playerRepo;
		this.gameDeckRepo = gameDeckRepo;
		this.game_player_cardsRepo=game_player_cardsRepo;
	}

	@Override
	public Response createGame(GameDomain gameDomain) {
		Response response=new Response();
		gameRepo.save(gameDomain);
		response.setMessage(ResponseMessages.GAME_CREATED);
		response.setPayload(gameDomain);
		return response;
	}

	@Override
	public Response deleteGame(Long gameId) {
		Response response=new Response();
		gameRepo.deleteGameById(gameId);
		return response;
	}

	@Override
	public Response createPlayer(PlayerDomain playerDomain) {
		Response response=new Response();
		playerRepo.save(playerDomain);
		response.setMessage(ResponseMessages.PLAYER_CREATED);
		response.setPayload(playerDomain);
		return response;
	}

	@Override
	public Response deletePlayer(Long playerId) {
		Response response=new Response();
		playerRepo.deletePlayerById(playerId);
		return response;
	}

	@Override
	public Response addPlayerToGame(Long playerId, Long gameId) {
		Response response=new Response();
		Game_PlayerDomain game_playerDomain=new Game_PlayerDomain();
		game_playerDomain.setGameDomain(gameRepo.getOne(gameId));
		game_playerDomain.setPlayerDomain(playerRepo.getOne(playerId));
		game_playerRepo.save(game_playerDomain);
		response.setMessage(ResponseMessages.PLAYER_ADDED);
		response.setPayload(null);
		return response;
	}

	@Override
	public Response createGameDeck(Long noOfDeck, Long gameId) {
		Response response=new Response();
		GameDomain gameDomain=gameRepo.getOne(gameId);
		int shuffleId=1;
		for(int i=0;i<noOfDeck;i++){
			for(Long j : Deck.deck.keySet()){
				GameDeckDomain gameDeckDomain=new GameDeckDomain();
				gameDeckDomain.setGameDomain(gameDomain);
				gameDeckDomain.setCardId(j);
				gameDeckDomain.setShuffleId(shuffleId);
				gameDeckDomain.setInDeck(true);
				gameDeckRepo.save(gameDeckDomain);
				shuffleId++;
			}
		}
		response.setMessage(ResponseMessages.ADD_DECK_TO_GAME);
		response.setPayload(null);
		return response;
	}

	@Override
	public Response dealCardsToPlayer(Long playerId, Long gameId) {
		Response response=new Response();
		int deckSize = gameDeckRepo.getCardIdByGameId(gameId).size();
		System.out.println(deckSize);
		List<Game_PlayerDomain> gamePlayerId=game_playerRepo.getGamePlayerIds(playerId,gameId);
		if(gamePlayerId.size()>0) {
			for (int i = 0; i < 2; i++) {
				Long cardId=new Long(deckSize);
				System.out.println("cardid"+cardId);
				Game_Player_CardsDomain game_player_cardsDomain = new Game_Player_CardsDomain();
				System.out.println("1");
				game_player_cardsDomain.setGame_playerDomain(gamePlayerId.get(0));
				System.out.println("2");
				game_player_cardsDomain.setCardId(gameDeckRepo.getCardIdByGameIdAndShuffleId(gameId,cardId).get(0));
				System.out.println("3");
				game_player_cardsRepo.save(game_player_cardsDomain);
				System.out.println("5");
				gameDeckRepo.deleteCardFromDeck(gameId,cardId);
				System.out.println("4");
				deckSize--;
			}

			response.setMessage(ResponseMessages.DEAL_CARDS_TO_PLAYER);
		}
		else
			response.setMessage("Game_id or Player_id is not exist");
		response.setPayload(null);
		return response;
	}

	@Override
	public Response shuffleDeck(Long gameId) {
		List<Long> cardIds = gameDeckRepo.getCardIdByGameId(gameId);
		List<Integer> preDefinedIds=new ArrayList<>();
		System.out.println(cardIds.size());
		int noOfRowsUpdated=0;
		Response response=new Response();
		for(int i=0;i<cardIds.size();i++){
			preDefinedIds.add(i+1);
		}
		Random rand= new Random();
		int index;//=rand.nextInt(preDefinedIds.size());
		for(int i=0;i<cardIds.size();i++){
			index=rand.nextInt(preDefinedIds.size());
//		System.out.println("index : "+index);
			gameDeckRepo.updateShuffleId(cardIds.get(i),gameId,preDefinedIds.get(index));
			preDefinedIds.remove(index);
			noOfRowsUpdated++;
		}
		response.setMessage(ResponseMessages.DECK_SHUFFLED+" for "+noOfRowsUpdated+ " cards");
		response.setPayload(null);
		return response;
	}

	@Override
	public Response getCardsOfPlayer(Long gameId, Long playerId){
		List<Long> cards=game_player_cardsRepo.getCardsOfAPlayer(playerId,gameId);
		String s="";
		Response response=new Response();
		for(Long card : cards){
			s+=Deck.suit.get(Deck.deck.get(card).get("suit"))+" : "+Deck.valueFace.get(Deck.deck.get(card).get("value"))+"\n";
		}
		response.setMessage("Cards for a player retrieved");
		response.setPayload(s);
		return response;
	}

	@Override
	public Response getCountOfCardsInDeck(Long gameId){
		Response response=new Response();
		List<Long> cards=gameDeckRepo.getCardIdByGameId(gameId);
		int hearts=0;
		int spades=0;
		int clubs=0;
		int diamonds=0;
		for(Long card:cards){
			if(Deck.suit.get(Deck.deck.get(card).get("suit")).equals("Hearts"))
				hearts++;
			else if(Deck.suit.get(Deck.deck.get(card).get("suit")).equals("Spades"))
				spades++;
			else if(Deck.suit.get(Deck.deck.get(card).get("suit")).equals("Clubs"))
				clubs++;
			else if(Deck.suit.get(Deck.deck.get(card).get("suit")).equals("Diamonds"))
				diamonds++;
		}
		response.setMessage("Cards in a deck : ");
		response.setPayload("[ Hearts : "+hearts+" ] [ Spades : "+spades+" ] [ Clubs : "+clubs+" ] [ Diamonds : "+diamonds+" ]");
		return response;
	}

	@Override
	public Response getCardsInDeckInSortedOrder(Long gameId){
		Response response=new Response();
		List<Long> cards=gameDeckRepo.getCardIdByGameId(gameId);
		List<Integer> hearts = new ArrayList<>();
		List<Integer> spades = new ArrayList<>();
		List<Integer> clubs = new ArrayList<>();
		List<Integer> diamonds = new ArrayList<>();
		String s="";
		for(Long card:cards){
			if(Deck.suit.get(Deck.deck.get(card).get("suit")).equals("Hearts"))
				hearts.add(Deck.deck.get(card).get("value"));
			else if(Deck.suit.get(Deck.deck.get(card).get("suit")).equals("Spades"))
				spades.add(Deck.deck.get(card).get("value"));
			else if(Deck.suit.get(Deck.deck.get(card).get("suit")).equals("Clubs"))
				clubs.add(Deck.deck.get(card).get("value"));
			else if(Deck.suit.get(Deck.deck.get(card).get("suit")).equals("Diamonds"))
				diamonds.add(Deck.deck.get(card).get("value"));
		}
		Collections.sort(hearts);
		Collections.sort(spades);
		Collections.sort(clubs);
		Collections.sort(diamonds);
		for(int i=hearts.size();i>=0;i--){
			s+="Hearts : "+hearts.get(i)+"\n";
		}
		for(int i=spades.size();i>=0;i--){
			s+="Spades : "+spades.get(i)+"\n";
		}
		for(int i=clubs.size();i>=0;i--){
			s+="Clubs : "+clubs.get(i)+"\n";
		}
		for(int i=diamonds.size();i>=0;i--){
			s+="Diamonds : "+diamonds.get(i)+"\n";
		}

		response.setMessage("Cards in a deck in sorted order : ");
		response.setPayload(s);
		return response;
	}
}
