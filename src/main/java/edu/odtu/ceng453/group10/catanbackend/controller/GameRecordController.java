package edu.odtu.ceng453.group10.catanbackend.controller;

import edu.odtu.ceng453.group10.catanbackend.dto.CreateGameRecordRequest;
import edu.odtu.ceng453.group10.catanbackend.dto.GameRecordDto;
import edu.odtu.ceng453.group10.catanbackend.service.GameRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gameRecord")
@Tag(name = "Game Record Controller", description = "Controller for Game Record operations such as saving a game record to database.")
public class GameRecordController {

  private final GameRecordService gameRecordService;

  GameRecordController(GameRecordService gameRecordService) {
    this.gameRecordService = gameRecordService;
  }

  @Operation(summary = "Create a new Game Record",
      description = "Creates a new Game Record, also adds to Game Score table to record all obtained scores.",
      requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
          description = "Game Record and Game Score creation details.",
          required = true,
          content = @Content(schema = @Schema(implementation = CreateGameRecordRequest.class))
      ),
      responses = {
        @ApiResponse(responseCode = "200", description = "Game record successfully created",
          content = @Content(schema = @Schema(implementation = GameRecordDto.class))),
          @ApiResponse(responseCode = "400", description = "Invalid input")
      })
  @PostMapping(value = "/create", consumes = "application/json")
  public ResponseEntity<GameRecordDto> createGameRecord(@RequestBody CreateGameRecordRequest request) {
    GameRecordDto dto = gameRecordService.createRecord(request);
    if(dto == null)
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    return ResponseEntity.ok(dto);
  }



}
