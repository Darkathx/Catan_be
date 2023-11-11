package edu.odtu.ceng453.group10.catanbackend.repository;

import edu.odtu.ceng453.group10.catanbackend.dto.GameScoreDto;
import edu.odtu.ceng453.group10.catanbackend.model.GameScore;
import edu.odtu.ceng453.group10.catanbackend.model.ScoreTableKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface GameScoreRepository extends CrudRepository<GameScore, ScoreTableKey> {

    @Query("SELECT gs.userAccount.username, SUM(gs.score) FROM GameScore gs " +
            "WHERE gs.gameRecord.dateOfPlay >= :start AND gs.gameRecord.dateOfPlay <= :end " +
            "GROUP BY gs.userAccount.username ORDER BY SUM(gs.score) DESC")
    List<GameScoreDto> findTotalScoresBetweenDates(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT gs.userAccount.username, SUM(gs.score) FROM GameScore gs " +
            "GROUP BY gs.userAccount.username ORDER BY SUM(gs.score) DESC")
    List<GameScoreDto> findAllTotalScores();
}
