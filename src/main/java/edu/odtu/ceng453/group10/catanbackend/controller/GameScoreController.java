package edu.odtu.ceng453.group10.catanbackend.controller;

import edu.odtu.ceng453.group10.catanbackend.dto.GameScoreDto;
import edu.odtu.ceng453.group10.catanbackend.service.GameScoreService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/leaderboard")
@Tag(name = "GameScoreController", description = "Controller for managing game score leaderboards")
public class GameScoreController {

    private final GameScoreService gameScoreService;

    public GameScoreController(GameScoreService gameScoreService) {
        this.gameScoreService = gameScoreService;
    }
    @Operation(summary = "Get weekly leaderboard",
            description = "Retrieves a list of game scores for the last week",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved the weekly leaderboard",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GameScoreDto.class))),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            })
    @GetMapping("/weekly")
    public ResponseEntity<List<GameScoreDto>> getWeeklyLeaderboard() {
        return ResponseEntity.ok(gameScoreService.getLeaderboardForLastWeek());
    }

    @Operation(summary = "Get monthly leaderboard",
            description = "Retrieves a list of game scores for the last month",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved the monthly leaderboard",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GameScoreDto.class))),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            })
    @GetMapping("/monthly")
    public ResponseEntity<List<GameScoreDto>> getMonthlyLeaderboard() {
        return ResponseEntity.ok(gameScoreService.getLeaderboardForLastMonth());
    }

    @Operation(summary = "Get overall leaderboard",
            description = "Retrieves a list of game scores across all time",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved the overall leaderboard",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GameScoreDto.class))),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            })
    @GetMapping("/overall")
    public ResponseEntity<List<GameScoreDto>> getOverallLeaderboard() {
        return ResponseEntity.ok(gameScoreService.getOverallLeaderboard());
    }
}