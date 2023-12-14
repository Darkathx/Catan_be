package edu.odtu.ceng453.group10.catanbackend.repository;

import edu.odtu.ceng453.group10.catanbackend.dto.GameScoreDto;
import edu.odtu.ceng453.group10.catanbackend.model.GameScore;
import edu.odtu.ceng453.group10.catanbackend.model.ScoreTableKey;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.repository.query.Param;

/**
 * Repository interface for GameScore entities. This extends the CrudRepository interface
 * provided by Spring Data, which provides standard CRUD operations without the need for boilerplate code.
 */
public interface GameScoreRepository extends JpaRepository<GameScore, ScoreTableKey> {
  /**
   * Custom query to find total scores for all users within a specific date range, grouped by username.
   * The results are ordered by the sum of scores in descending order.
   *
   * @param start The start of the date range.
   * @param end   The end of the date range.
   * @return A list of GameScoreDto objects containing usernames and their corresponding total scores.
   */
  @Query("SELECT gs.userAccount.username, SUM(gs.score) FROM GameScore gs " +
      "WHERE gs.gameRecord.dateOfPlay >= :start AND gs.gameRecord.dateOfPlay <= :end " +
      "GROUP BY gs.userAccount.username ORDER BY SUM(gs.score) DESC")
  ArrayList<String> findTotalScoresBetweenDates(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

  /**
   * Custom query to find all total scores for all users, grouped by username.
   * The results are ordered by the sum of scores in descending order.
   *
   * @return A list of GameScoreDto objects containing usernames and their corresponding total scores.
   */
  @Query("SELECT gs.userAccount.username, SUM(gs.score) FROM GameScore gs " +
      "GROUP BY gs.userAccount.username ORDER BY SUM(gs.score) DESC")
  ArrayList<String> findAllTotalScores();
}
