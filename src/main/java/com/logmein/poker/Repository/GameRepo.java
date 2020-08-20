package com.logmein.poker.Repository;

import com.logmein.poker.domain.GameDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepo extends JpaRepository<GameDomain,Long> {

	@Modifying
	@Query(value = "UPDATE game u set u.active=false where u.id=:gameId", nativeQuery = true)
	int deleteGameById(Long gameId);
}
