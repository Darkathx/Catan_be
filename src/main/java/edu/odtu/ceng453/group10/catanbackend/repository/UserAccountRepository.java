package edu.odtu.ceng453.group10.catanbackend.repository;

import edu.odtu.ceng453.group10.catanbackend.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for UserAccount entities. It extends JpaRepository,
 * leveraging Spring Data JPA to provide a range of standard database operations for UserAccount entities.
 */
public interface UserAccountRepository extends JpaRepository<UserAccount, String> {

    /**
     * Custom query method to find a user account by its email address.
     * If no user is found, this method returns null.
     *
     * @param email The email address to search for in the database.
     * @return The UserAccount associated with the given email if it exists; null otherwise.
     */
    UserAccount findUserAccountByEmail(String email);
    UserAccount findUserAccountByUsername(String username);
}
