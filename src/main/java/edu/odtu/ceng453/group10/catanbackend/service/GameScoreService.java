package edu.odtu.ceng453.group10.catanbackend.service;

import edu.odtu.ceng453.group10.catanbackend.dto.GameScoreDto;
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

    public List<GameScoreDto> getLeaderboardForLastWeek() {
        LocalDateTime oneWeekAgo = LocalDateTime.now().minusWeeks(1);
        return gameScoreRepository.findTotalScoresBetweenDates(oneWeekAgo, LocalDateTime.now());
    }

    public List<GameScoreDto> getLeaderboardForLastMonth() {
        LocalDateTime oneMonthAgo = LocalDateTime.now().minusMonths(1);
        return gameScoreRepository.findTotalScoresBetweenDates(oneMonthAgo, LocalDateTime.now());
    }

    public List<GameScoreDto> getOverallLeaderboard() {
        return gameScoreRepository.findAllTotalScores();
    }
}
