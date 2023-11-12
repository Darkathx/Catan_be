package edu.odtu.ceng453.group10.catanbackend.repository;

import edu.odtu.ceng453.group10.catanbackend.model.GameRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for {@link GameRecord} entities.
 * This repository provides CRUD operations and finder methods for accessing game records stored in the database.
 * By extending JpaRepository, it inherits several methods for working with GameRecord persistence,
 * including methods for saving, deleting, and finding game records.
 */
public interface GameRecordRepository extends JpaRepository<GameRecord, String> {

}
