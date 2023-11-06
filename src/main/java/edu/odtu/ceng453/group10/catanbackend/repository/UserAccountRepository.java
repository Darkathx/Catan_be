package edu.odtu.ceng453.group10.catanbackend.repository;

import edu.odtu.ceng453.group10.catanbackend.model.GameRecord;
import edu.odtu.ceng453.group10.catanbackend.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
    List<GameRecord> findGameRecordsByUserAccountId(String userAccountId);
    UserAccount findUserAccountByEmail(String email);
}
