package edu.odtu.ceng453.group10.catanbackend.repository;

import edu.odtu.ceng453.group10.catanbackend.model.Building;
import edu.odtu.ceng453.group10.catanbackend.model.GameState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository<Building, String> {

  public void deleteBuildingsByGameStateId(GameState gameState);
}
