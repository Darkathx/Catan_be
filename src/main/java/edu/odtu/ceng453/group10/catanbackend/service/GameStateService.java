package edu.odtu.ceng453.group10.catanbackend.service;

import edu.odtu.ceng453.group10.catanbackend.model.Game;
import edu.odtu.ceng453.group10.catanbackend.model.GameState;
import edu.odtu.ceng453.group10.catanbackend.repository.BuildingRepository;
import edu.odtu.ceng453.group10.catanbackend.repository.GameStateRepository;
import org.springframework.stereotype.Service;

@Service
public class GameStateService {
  private final GameStateRepository repository;
  private final BuildingRepository buildingRepository;

  public GameStateService(GameStateRepository repository,
              BuildingRepository buildingRepository) {
    this.repository = repository;
    this.buildingRepository = buildingRepository;
  }

  public GameState getGameState(Game game) {
    return repository.findByGameId(game);
  }

  public GameState saveGameState(GameState gameState) {
    buildingRepository.deleteBuildingsByGameStateId(gameState);
    return repository.save(gameState);
  }

}
