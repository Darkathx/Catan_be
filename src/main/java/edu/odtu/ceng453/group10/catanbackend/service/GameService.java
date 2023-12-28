package edu.odtu.ceng453.group10.catanbackend.service;

import edu.odtu.ceng453.group10.catanbackend.model.Game;
import edu.odtu.ceng453.group10.catanbackend.model.GameState;
import edu.odtu.ceng453.group10.catanbackend.model.UserAccount;
import edu.odtu.ceng453.group10.catanbackend.repository.GameRepository;
import edu.odtu.ceng453.group10.catanbackend.repository.GameStateRepository;
import org.springframework.stereotype.Service;

/**
 * This class implements the endpoints for game operations.
 */

@Service
public class GameService {
  private final GameRepository repository;
  private final UserAccountService userAccountService;
  private final GameStateRepository stateRepository;

  public GameService(GameRepository repository,
                      UserAccountService userAccountService,
                      GameStateRepository stateRepository) {
    this.repository = repository;
    this.userAccountService = userAccountService;
    this.stateRepository = stateRepository;
  }

  /**
   * Searches for an empty game and joins it with the given username.
   * @param username username of the player
   * @return the game that the player joined
   */

  public Game searchAndJoinGame(String username) {
    Game game = repository.findEmptyGames().getFirst();
    UserAccount acc = userAccountService.getUserAccount(username);
    if (game.getP1() == null) {
      game.setP1(acc);
    }
    else if (game.getP2() == null) {
      game.setP2(acc);
    }
    else if (game.getP3() == null) {
      game.setP3(acc);
    }
    else if (game.getP4() == null) {
      game.setP4(acc);
    }
    else {
      game = new Game("", acc, null, null, null);
    }
    return repository.save(game);
  }

  /**
   * Returns the game state of the given game.
   * @param gameId id of the game
   * @return the game state of the given game
   */

  public GameState getGameState(String gameId) {
    Game game = repository.findById(gameId).orElseThrow();
    return stateRepository.findByGameId(game);
  }

  /**
   * Sets the game state of the given game.
   * @param gameState game state to be set
   * @return the game state of the given game
   */

  public GameState setGameState(GameState gameState) {
    return stateRepository.save(gameState);
  }
}
