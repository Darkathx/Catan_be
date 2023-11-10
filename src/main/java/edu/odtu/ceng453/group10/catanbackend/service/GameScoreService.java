package edu.odtu.ceng453.group10.catanbackend.service;

import edu.odtu.ceng453.group10.catanbackend.model.GameScore;
import edu.odtu.ceng453.group10.catanbackend.repository.GameScoreRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GameScoreService {
    private final GameScoreRepository gameScoreRepository;

    public GameScoreService(GameScoreRepository gameScoreRepository) {
        this.gameScoreRepository = gameScoreRepository;
    }

    public List<GameScore> getLeaderboardForLastWeek() {
        LocalDateTime oneWeekAgo = LocalDateTime.now().minusWeeks(1);
        LocalDateTime now = LocalDateTime.now();
        return gameScoreRepository.findScoresBetweenDates(oneWeekAgo, now);
    }

    public List<GameScore> getLeaderboardForLastMonth() {
        LocalDateTime oneMonthAgo = LocalDateTime.now().minusMonths(1);
        LocalDateTime now = LocalDateTime.now();
        return gameScoreRepository.findScoresBetweenDates(oneMonthAgo, now);
    }

    public List<GameScore> getOverallLeaderboard() {
        return gameScoreRepository.findAllByOrderByScoreDesc();
    }
}
