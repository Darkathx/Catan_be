package edu.odtu.ceng453.group10.catanbackend.repository;

import edu.odtu.ceng453.group10.catanbackend.model.Game;
import edu.odtu.ceng453.group10.catanbackend.model.GameState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GameStateRepository extends JpaRepository<GameState, String> {
  /**
   * Custom query to find the game state of a game.
   *
   * @param game The game to find the state of.
   * @return The GameState object containing the state of the game.
   */

  GameState findByGameId(Game game);
}
