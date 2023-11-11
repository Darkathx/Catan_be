package edu.odtu.ceng453.group10.catanbackend.service;

import edu.odtu.ceng453.group10.catanbackend.dto.GameScoreDto;
import edu.odtu.ceng453.group10.catanbackend.repository.GameScoreRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service class for managing game score operations. This class handles the business logic
 * related to retrieving game scores and leaderboards.
 */
@Service
public class GameScoreService {
    private final GameScoreRepository gameScoreRepository;

    /**
     * Constructs a new GameScoreService with the given GameScoreRepository.
     *
     * @param gameScoreRepository The repository used for accessing game scores.
     */
    public GameScoreService(GameScoreRepository gameScoreRepository) {
        this.gameScoreRepository = gameScoreRepository;
    }

    /**
     * Retrieves the leaderboard for the last week. This calculates the scores from one week ago to now.
     *
     * @return A list of GameScoreDto representing the scores for the last week.
     */
    public List<GameScoreDto> getLeaderboardForLastWeek() {
        LocalDateTime oneWeekAgo = LocalDateTime.now().minusWeeks(1);
        return gameScoreRepository.findTotalScoresBetweenDates(oneWeekAgo, LocalDateTime.now());
    }

    /**
     * Retrieves the leaderboard for the last month. This calculates the scores from one month ago to now.
     *
     * @return A list of GameScoreDto representing the scores for the last month.
     */
    public List<GameScoreDto> getLeaderboardForLastMonth() {
        LocalDateTime oneMonthAgo = LocalDateTime.now().minusMonths(1);
        return gameScoreRepository.findTotalScoresBetweenDates(oneMonthAgo, LocalDateTime.now());
    }

    /**
     * Retrieves the overall leaderboard. This aggregates scores from all time.
     *
     * @return A list of GameScoreDto representing the overall scores.
     */
    public List<GameScoreDto> getOverallLeaderboard() {
        return gameScoreRepository.findAllTotalScores();
    }
}
