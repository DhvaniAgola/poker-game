package com.logmein.poker.Repository;

import com.logmein.poker.domain.Game_PlayerDomain;
import com.logmein.poker.domain.Game_Player_CardsDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Game_Player_CardsRepo extends JpaRepository<Game_Player_CardsDomain,Long> {

	@Query(value = "select u.card_id from game_player_cards u where (u.game_player_id=(select id from game_player l where l.player_id=:playerId and l.game_id=:gameId))",nativeQuery = true)
	List<Long> getCardsOfAPlayer(Long playerId, Long gameId);
}
