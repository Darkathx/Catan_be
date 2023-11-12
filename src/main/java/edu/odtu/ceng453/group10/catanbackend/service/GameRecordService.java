package edu.odtu.ceng453.group10.catanbackend.service;

import edu.odtu.ceng453.group10.catanbackend.dto.CreateGameRecordRequest;
import edu.odtu.ceng453.group10.catanbackend.dto.GameRecordDto;
import edu.odtu.ceng453.group10.catanbackend.dto.GameRecordDtoConverter;
import edu.odtu.ceng453.group10.catanbackend.model.GameRecord;
import edu.odtu.ceng453.group10.catanbackend.repository.GameRecordRepository;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class GameRecordService {

  private final GameRecordRepository repository;
  private final GameRecordDtoConverter converter;
  private final GameScoreService gameScoreService;

  public GameRecordService(GameRecordRepository repository, GameRecordDtoConverter converter, GameScoreService gameScoreService) {
    this.repository = repository;
    this.converter = converter;
    this.gameScoreService = gameScoreService;
  }

  public GameRecordDto createRecord(CreateGameRecordRequest request) {
    GameRecord gameRecord = new GameRecord(LocalDateTime.now());
    gameRecord = repository.save(gameRecord);
    boolean isSuccessfullyCreated = gameScoreService.createGameScore(request, gameRecord);
    if(!isSuccessfullyCreated) {
      repository.deleteById(gameRecord.getId());
      return null;
    }
    return converter.convert(gameRecord);
  }


}
