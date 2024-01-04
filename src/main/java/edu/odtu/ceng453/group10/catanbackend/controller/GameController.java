package edu.odtu.ceng453.group10.catanbackend.controller;

import edu.odtu.ceng453.group10.catanbackend.model.Game;
import edu.odtu.ceng453.group10.catanbackend.model.GameState;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
  public ResponseEntity<Game> joinGame(String username) {
    return ResponseEntity.ok(gameService.searchAndJoinGame(username));
  }

  @Operation(summary = "Get game state",
      description = "Returns the game state of the given game.",
      responses = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Game state returned successfully"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input")
      })
  @GetMapping(value = "/gameState")
  public ResponseEntity<GameState> getGameState(String gameId) {
    GameState gameState = gameService.getGameState(gameId);
    return ResponseEntity.ok(gameState);
  }

  @Operation(summary = "Set game state",
      description = "Sets the game state of the given game.",
      responses = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Game state set successfully"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input")
      })
  @PostMapping(value = "/gameState")
  public ResponseEntity<GameState> setGameState(GameState gameState) {
    return ResponseEntity.ok(gameService.setGameState(gameState));
  }
}
