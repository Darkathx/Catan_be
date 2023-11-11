package edu.odtu.ceng453.group10.catanbackend.controller;

import edu.odtu.ceng453.group10.catanbackend.dto.GameScoreDto;
import edu.odtu.ceng453.group10.catanbackend.model.GameScore;
import edu.odtu.ceng453.group10.catanbackend.service.GameScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leaderboard")
public class GameScoreController {

    private final GameScoreService gameScoreService;

    public GameScoreController(GameScoreService gameScoreService) {
        this.gameScoreService = gameScoreService;
    }

    @GetMapping("/weekly")
    public ResponseEntity<List<GameScoreDto>> getWeeklyLeaderboard() {
        return ResponseEntity.ok(gameScoreService.getLeaderboardForLastWeek());
    }

    @GetMapping("/monthly")
    public ResponseEntity<List<GameScoreDto>> getMonthlyLeaderboard() {
        return ResponseEntity.ok(gameScoreService.getLeaderboardForLastMonth());
    }

    @GetMapping("/overall")
    public ResponseEntity<List<GameScoreDto>> getOverallLeaderboard() {
        return ResponseEntity.ok(gameScoreService.getOverallLeaderboard());
    }
}
