package edu.odtu.ceng453.group10.catanbackend.service;

import edu.odtu.ceng453.group10.catanbackend.dto.CreateGameRecordRequest;
import edu.odtu.ceng453.group10.catanbackend.dto.GameScoreDto;
import edu.odtu.ceng453.group10.catanbackend.model.GameRecord;
import edu.odtu.ceng453.group10.catanbackend.model.GameScore;
import edu.odtu.ceng453.group10.catanbackend.model.ScoreTableKey;
import edu.odtu.ceng453.group10.catanbackend.model.UserAccount;
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
    private final UserAccountService userAccountService;


    /**
     * Constructs a new GameScoreService with the given GameScoreRepository.
     *
     * @param gameScoreRepository The repository used for accessing game scores.
     */
    public GameScoreService(GameScoreRepository gameScoreRepository, UserAccountService userAccountService) {
        this.gameScoreRepository = gameScoreRepository;
        this.userAccountService = userAccountService;
    }

    /**
     * Creates a new GameScore with gameRecord and necessary information about game.
     * @param request Request contains information about game.
     * @param gameRecord Record including this game.
     * @return true if successful, false otherwise.
     */
    public boolean createGameScore(CreateGameRecordRequest request, GameRecord gameRecord) {
        String playerId;
        int playerScore = 0, i = 0;
        boolean returnValue = false;
        for(; i < 4; i++) {
            switch(i) {
                case 0:
                    playerId = request.getFirstPlayerId();
                    playerScore = request.getFirstPlayerScore();
                case 1:
                    playerId = request.getSecondPlayerId();
                    playerScore = request.getSecondPlayerScore();
                case 2:
                    playerId = request.getThirdPlayerId();
                    playerScore = request.getThirdPlayerScore();
                case 3:
                    playerId = request.getFourthPlayerId();
                    playerScore = request.getFourthPlayerScore();
                default:
                    playerId = null;
            }
            if(playerId != null) {
                UserAccount userAccount = userAccountService.getUserAccount(playerId);
                ScoreTableKey key = new ScoreTableKey(gameRecord.getId(), userAccount.getId());
                GameScore gameScore = new GameScore(key, userAccount, gameRecord, playerScore);
                gameScoreRepository.save(gameScore);
                returnValue = true;
            }
        }
        return returnValue;
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
