package edu.odtu.ceng453.group10.catanbackend.dto;

import edu.odtu.ceng453.group10.catanbackend.model.GameRecord;
import org.springframework.stereotype.Component;

@Component
public class GameRecordDtoConverter {

  public GameRecordDto convert(GameRecord from){
    return new GameRecordDto(from.getDateOfPlay());
  }
}
