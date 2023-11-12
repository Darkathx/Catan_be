package edu.odtu.ceng453.group10.catanbackend.controller;

import edu.odtu.ceng453.group10.catanbackend.dto.CreateGameRecordRequest;
import edu.odtu.ceng453.group10.catanbackend.dto.GameRecordDto;
import edu.odtu.ceng453.group10.catanbackend.dto.GameRecordDtoConverter;
import edu.odtu.ceng453.group10.catanbackend.service.GameRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gameRecord")
public class GameRecordController {

  private final GameRecordService gameRecordService;

  GameRecordController(GameRecordService gameRecordService) {
    this.gameRecordService = gameRecordService;
  }

  @PostMapping("/create")
  public ResponseEntity<GameRecordDto> createGameRecord(@RequestBody CreateGameRecordRequest request) {
    GameRecordDto dto = gameRecordService.createRecord(request);
    if(dto == null)
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    return ResponseEntity.ok(dto);
  }



}
