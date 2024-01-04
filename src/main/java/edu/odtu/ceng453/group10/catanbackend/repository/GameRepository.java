package edu.odtu.ceng453.group10.catanbackend.repository;

import edu.odtu.ceng453.group10.catanbackend.model.Game;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Repository interface for Game entities. This extends the JpaRepository interface
 */

public interface GameRepository extends JpaRepository<Game, String> {

  /**
   * Custom query to find all games that are not full.
   *
   * @return A list of Game objects containing game ids.
   */

  @Query("SELECT g FROM Game g WHERE g.playerCount < 2")
  ArrayList<Game> findEmptyGames();

}
