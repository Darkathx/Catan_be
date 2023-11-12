package edu.odtu.ceng453.group10.catanbackend;

import edu.odtu.ceng453.group10.catanbackend.model.UserAccount;
import edu.odtu.ceng453.group10.catanbackend.repository.UserAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@ActiveProfiles("test")
public class UserAccountRepositoryTests {

    @Autowired
    private UserAccountRepository repository;

    @Test
    public void registerAndFindAndDeleteUserAccount() {
        UserAccount newUser = new UserAccount("username", "password", LocalDateTime.now(), "example@example.com");

        UserAccount savedUser = repository.save(newUser);

        UserAccount foundUser = repository.findUserAccountByEmail("example@example.com");

        assertEquals("username", foundUser.getUsername());

        repository.deleteById(savedUser.getId());

        Optional<UserAccount> checkDeletedUser = repository.findById(savedUser.getId());

        assertEquals(Optional.empty(), checkDeletedUser);
    }


}
