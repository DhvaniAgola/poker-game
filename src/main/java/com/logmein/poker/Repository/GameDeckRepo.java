package com.logmein.poker.Repository;

import com.logmein.poker.domain.GameDeckDomain;
import com.logmein.poker.domain.GameDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameDeckRepo extends JpaRepository<GameDeckDomain,Long> {

	@Query(value = "select u.card_id from game_deck u where u.game_id=:gameId and u.in_deck=true", nativeQuery = true)
	List<Long> getCardIdByGameId(Long gameId);

	@Query(value = "select * from game_deck u where u.game_id=:gameId and u.card_id=:cardId", nativeQuery = true)
	List<GameDeckDomain> getCardByGameIdAndCardId(Long gameId, Long cardId);

	@Query(value = "select * from game_deck u where u.game_id=:gameId and u.shuffle_id=:shuffleId", nativeQuery = true)
	List<GameDeckDomain> getCardIdByGameIdAndShuffleId(Long gameId, Long shuffleId);

	@Modifying
	@Query(value = "UPDATE game_deck u set u.shuffle_id=:shuffleId where u.card_id=:cardId and u.game_id=:gameId", nativeQuery = true)
	int updateShuffleId(Long cardId, Long gameId, int shuffleId);

	@Modifying
	@Query(value = "UPDATE game_deck u set u.in_deck=false where u.game_id=:gameId and u.shuffle_id=:shuffleId", nativeQuery = true)
	int deleteCardFromDeck(Long gameId, Long shuffleId);

}
