package edu.odtu.ceng453.group10.catanbackend.service;

import edu.odtu.ceng453.group10.catanbackend.dto.CreateGameRecordRequest;
import edu.odtu.ceng453.group10.catanbackend.dto.GameScoreDto;
import edu.odtu.ceng453.group10.catanbackend.model.GameRecord;
import edu.odtu.ceng453.group10.catanbackend.model.GameScore;
import edu.odtu.ceng453.group10.catanbackend.model.ScoreTableKey;
import edu.odtu.ceng453.group10.catanbackend.model.UserAccount;
import edu.odtu.ceng453.group10.catanbackend.repository.GameScoreRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
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
        boolean returnValue = false;
        for(int i = 0; i < 4; i++) {
            String playerId;
            int playerScore;
            playerId = switch(i) {
                case 0:
                    yield request.getFirstPlayerId();
                case 1:
                    yield request.getSecondPlayerId();
                case 2:
                    yield request.getThirdPlayerId();
                case 3:
                    yield request.getFourthPlayerId();
                default:
                    yield null;
            };
            if(playerId != null) {
                playerScore = switch(i) {
                    case 0:
                        yield request.getFirstPlayerScore();
                    case 1:
                        yield request.getSecondPlayerScore();
                    case 2:
                        yield request.getThirdPlayerScore();
                    case 3:
                        yield request.getFourthPlayerScore();
                    default:
                        yield 0;
                };
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
    public ArrayList<GameScoreDto> getLeaderboardForLastWeek() {
        LocalDateTime oneWeekAgo = LocalDateTime.now().minusWeeks(1);
        ArrayList<String> leaderboardList = gameScoreRepository.findTotalScoresBetweenDates(oneWeekAgo, LocalDateTime.now());
        return createLeaderboard(leaderboardList);
    }

    /**
     * Retrieves the leaderboard for the last month. This calculates the scores from one month ago to now.
     *
     * @return A list of GameScoreDto representing the scores for the last month.
     */
    public ArrayList<GameScoreDto> getLeaderboardForLastMonth() {
        LocalDateTime oneMonthAgo = LocalDateTime.now().minusMonths(1);
        ArrayList<String> leaderboardList = gameScoreRepository.findTotalScoresBetweenDates(oneMonthAgo, LocalDateTime.now());
        return createLeaderboard(leaderboardList);
    }

    /**
     * Retrieves the overall leaderboard. This aggregates scores from all time.
     *
     * @return A list of GameScoreDto representing the overall scores.
     */
    public ArrayList<GameScoreDto> getOverallLeaderboard() {
        ArrayList<String> list = gameScoreRepository.findAllTotalScores();
        return createLeaderboard(list);
    }

    private ArrayList<GameScoreDto> createLeaderboard(ArrayList<String> leaderboardList) {
        ArrayList<GameScoreDto> leaderboard = new ArrayList<>();
        for(int len = leaderboardList.size(), i = 0; i < 10; i++) {
            if(i >= len) {
                leaderboard.add(new GameScoreDto("N/A", 0));
            }
            else {
                String[] split = leaderboardList.get(i).split(",");
                leaderboard.add(new GameScoreDto(split[0], Integer.parseInt(split[1])));
            }
        }
        return leaderboard;
    }
}
