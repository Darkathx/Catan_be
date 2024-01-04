package edu.odtu.ceng453.group10.catanbackend.controller;

import edu.odtu.ceng453.group10.catanbackend.dto.GameDto;
import edu.odtu.ceng453.group10.catanbackend.dto.GameStateDto;
import edu.odtu.ceng453.group10.catanbackend.model.Game;
import edu.odtu.ceng453.group10.catanbackend.model.GameState;
import edu.odtu.ceng453.group10.catanbackend.model.UserAccount;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.odtu.ceng453.group10.catanbackend.service.GameService;

/**
 * This class implements the endpoints for game operations.
 */

@RestController
@RequestMapping("/game")
@Tag(name = "Game Controller", description = "Controller for Game operations such as joining a game.")
public class GameController {
  private final GameService gameService;

  GameController(GameService gameService) {
    this.gameService = gameService;
  }

  @Operation(summary = "Join a game",
      description = "Joins a game with the given username.",
      responses = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Game joined successfully"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input")
      })
  @PostMapping(value = "/join")
  public ResponseEntity<GameDto> joinGame(String username) {
    Game game = gameService.searchAndJoinGame(username);
    ArrayList<String> names = new ArrayList<>();
    for (UserAccount account : game.getPlayers()) {
      names.add(account.getUsername());
    }
    String stateId = null;
    GameState state = game.getState();
    if(state != null) {
      stateId = state.getId();
    }

    GameDto dto = new GameDto(game.getId(), stateId, names, game.getPlayerCount());
    return ResponseEntity.ok(dto);
  }

  @Operation(summary = "Get game state",
      description = "Returns the game state of the given game.",
      responses = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Game state returned successfully"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input")
      })
  @GetMapping(value = "/gameState")
  public ResponseEntity<GameStateDto> getGameState(String gameId) {
    GameState gameState = gameService.getGameState(gameId);
    return ResponseEntity.ok(new GameStateDto(gameState.getId(), gameState.getGame().getId(), gameState.getPlayerTurn(), gameState.getDice1(),
        gameState.getDice2(), gameState.getResources(), gameState.getBuildings()));
  }

  @Operation(summary = "Set game state",
      description = "Sets the game state of the given game.",
      responses = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Game state set successfully"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input")
      })
  @PostMapping(value = "/gameState")
  public ResponseEntity<GameStateDto> setGameState(GameStateDto gameStateDto) {
    Game game = gameService.getGame(gameStateDto.getGameId());
    GameState gameState = new GameState(gameStateDto.getId(), game, gameStateDto.getPlayerTurn(),gameStateDto.getDice1(), gameStateDto.getDice2(),
        gameStateDto.getResources(), gameStateDto.getBuildings());
    gameState = gameService.setGameState(gameState);
    return ResponseEntity.ok(new GameStateDto(gameState.getId(), gameState.getGame().getId(), gameState.getPlayerTurn(), gameState.getDice1(),
        gameState.getDice2(), gameState.getResources(), gameState.getBuildings()));
  }
}
