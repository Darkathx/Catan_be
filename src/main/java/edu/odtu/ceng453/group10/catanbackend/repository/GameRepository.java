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
  @Query("SELECT g FROM Game g WHERE g.p1 = null OR g.p2 = null OR g.p3 = null OR g.p4 = null")
  ArrayList<Game> findEmptyGames();

}
