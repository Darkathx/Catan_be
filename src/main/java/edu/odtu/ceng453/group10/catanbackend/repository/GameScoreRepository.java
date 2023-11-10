package edu.odtu.ceng453.group10.catanbackend.repository;

import edu.odtu.ceng453.group10.catanbackend.model.GameScore;
import edu.odtu.ceng453.group10.catanbackend.model.ScoreTableKey;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface GameScoreRepository extends CrudRepository<GameScore, ScoreTableKey> {

    // Derived query methods
    List<GameScore> findScoresBetweenDates(LocalDateTime start, LocalDateTime end);
    List<GameScore> findAllByOrderByScoreDesc();
}
