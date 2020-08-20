package com.logmein.poker.Repository;

import com.logmein.poker.domain.GameDomain;
import com.logmein.poker.domain.PlayerDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepo extends JpaRepository<PlayerDomain,Long> {
	@Modifying
	@Query(value = "UPDATE player u set u.active=false where u.id=:playerId", nativeQuery = true)
	int deletePlayerById(Long playerId);
}
