package edu.odtu.ceng453.group10.catanbackend.service;

import edu.odtu.ceng453.group10.catanbackend.model.Game;
import edu.odtu.ceng453.group10.catanbackend.model.GameState;
import edu.odtu.ceng453.group10.catanbackend.repository.GameStateRepository;
import org.springframework.stereotype.Service;

@Service
public class GameStateService {
  private final GameStateRepository repository;

  public GameStateService(GameStateRepository repository) {
    this.repository = repository;
  }

  public GameState getGameState(Game game) {
    return repository.findByGameId(game);
  }

  public GameState saveGameState(GameState gameState) {
    return repository.save(gameState);
  }
}
