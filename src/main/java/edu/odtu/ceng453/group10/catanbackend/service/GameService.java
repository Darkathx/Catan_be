package edu.odtu.ceng453.group10.catanbackend.service;

import edu.odtu.ceng453.group10.catanbackend.model.Game;
import edu.odtu.ceng453.group10.catanbackend.model.GameState;
import edu.odtu.ceng453.group10.catanbackend.model.UserAccount;
import edu.odtu.ceng453.group10.catanbackend.repository.GameRepository;
import edu.odtu.ceng453.group10.catanbackend.repository.GameStateRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * This class implements the endpoints for game operations.
 */

@Service
public class GameService {
  private final GameRepository repository;
  private final UserAccountService userAccountService;
  private final GameStateService gameStateService;

  public GameService(GameRepository repository,
                      UserAccountService userAccountService,
                      GameStateService gameStateService) {
    this.repository = repository;
    this.userAccountService = userAccountService;
    this.gameStateService = gameStateService;
  }

  /**
   * Searches for an empty game and joins it with the given username.
   * @param username username of the player
   * @return the game that the player joined
   */

  public Game searchAndJoinGame(String username) {
    ArrayList<Game> gameList = repository.findEmptyGames();
    Game game;
    if (gameList.isEmpty()) {
      game = new Game();
    }
    else {
      game = gameList.getFirst();
    }
    UserAccount acc = userAccountService.getUserAccountByUsername(username);
    List<UserAccount> players = game.getPlayers();
    if (players.getFirst() == null) {
      players.set(0, acc);
    }
    else if (players.get(1) == null) {
      players.set(1, acc);
    }
    else if (players.get(2) == null) {
      players.set(2, acc);
    }
    else if (players.get(3) == null) {
      players.set(3, acc);
    }
    game.setPlayerCount(game.getPlayerCount() + 1);
    return repository.save(game);
  }

  public Game getGame(String gameId) {
    return repository.findById(gameId).orElseThrow();
  }

  /**
   * Returns the game state of the given game.
   * @param gameId id of the game
   * @return the game state of the given game
   */

  public GameState getGameState(String gameId) {
    Game game = repository.findById(gameId).orElseThrow();
    return gameStateService.getGameState(game);
  }

  /**
   * Sets the game state of the given game.
   * @param gameState game state to be set
   * @return the game state of the given game
   */

  public GameState setGameState(GameState gameState) {

    return gameStateService.saveGameState(gameState);
  }
}
