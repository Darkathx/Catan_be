package edu.odtu.ceng453.group10.catanbackend.controller;

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
    public ResponseEntity<List<GameScore>> getWeeklyLeaderboard() {
        List<GameScore> weeklyScores = gameScoreService.getLeaderboardForLastWeek();
        return ResponseEntity.ok(weeklyScores);
    }

    @GetMapping("/monthly")
    public ResponseEntity<List<GameScore>> getMonthlyLeaderboard() {
        List<GameScore> monthlyScores = gameScoreService.getLeaderboardForLastMonth();
        return ResponseEntity.ok(monthlyScores);
    }

    @GetMapping("/overall")
    public ResponseEntity<List<GameScore>> getOverallLeaderboard() {
        List<GameScore> overallScores = gameScoreService.getOverallLeaderboard();
        return ResponseEntity.ok(overallScores);
    }
}
