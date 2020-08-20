package com.logmein.poker.Repository;

import com.logmein.poker.domain.Game_PlayerDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Game_PlayerRepo extends JpaRepository<Game_PlayerDomain,Long> {

	@Query(value = "select * from game_player u where u.game_id=:gameId and u.player_id=:playerId", nativeQuery = true)
	List<Game_PlayerDomain> getGamePlayerIds(Long playerId, Long gameId);
}
