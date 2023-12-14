package edu.odtu.ceng453.group10.catanbackend;

import edu.odtu.ceng453.group10.catanbackend.dto.LoginUserAccountRequest;
import edu.odtu.ceng453.group10.catanbackend.dto.UserAccountDto;
import edu.odtu.ceng453.group10.catanbackend.dto.UserAccountDtoConverter;
import edu.odtu.ceng453.group10.catanbackend.exception.UnauthorizedLoginException;
import edu.odtu.ceng453.group10.catanbackend.model.UserAccount;
import edu.odtu.ceng453.group10.catanbackend.repository.UserAccountRepository;
import edu.odtu.ceng453.group10.catanbackend.service.UserAccountService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
    public class UserAccountServiceTests {

        @Mock
        private UserAccountRepository repository;

        @Mock
        private UserAccountDtoConverter converter;

        @Mock
        private BCryptPasswordEncoder passwordEncoder;

        @InjectMocks
        private UserAccountService userAccountService;

        @Test
        public void testLoginUserAccount_successCase() {
            // Arrange
            LoginUserAccountRequest request = new LoginUserAccountRequest("test@example.com", "password");
            UserAccount userAccount = new UserAccount("id", "username", "password", LocalDateTime.now(), "test@example.com", Set.of());
            UserAccountService mockServiceMethods = Mockito.spy(userAccountService);

            Mockito.when(repository.findUserAccountByEmail(request.getEmail())).thenReturn(userAccount);
            Mockito.doReturn(true).when(mockServiceMethods).checkPassword(request.getPassword(), userAccount.getPassword());
            Mockito.when(converter.convert(userAccount)).thenReturn(new UserAccountDto("username", "test@example.com"));

            // Act
            UserAccountDto result = mockServiceMethods.loginUserAccount(request);

            // Assert
            Mockito.verify(repository, Mockito.times(1)).findUserAccountByEmail(request.getEmail());
            Mockito.verify(mockServiceMethods, Mockito.times(1)).checkPassword(request.getPassword(), userAccount.getPassword());
            Mockito.verify(converter, Mockito.times(1)).convert(userAccount);
            assertEquals(new UserAccountDto("username", "test@example.com"), result);
        }

        @Test
        public void testLoginUserAccount_userNotFound() {
            // Arrange
            LoginUserAccountRequest request = new LoginUserAccountRequest("nonexistent@example.com", "password123");

            Mockito.when(repository.findUserAccountByEmail(request.getEmail())).thenReturn(null);

            // Act
            assertThrows(UnauthorizedLoginException.class, () -> userAccountService.loginUserAccount(request));


            // Assert
            Mockito.verify(repository, Mockito.times(1)).findUserAccountByEmail(request.getEmail());
            Mockito.verify(passwordEncoder, Mockito.never()).matches(Mockito.any(), Mockito.any());
            Mockito.verify(converter, Mockito.never()).convert(Mockito.any());
        }

        @Test
        public void testLoginUserAccount_invalidPassword() {
            // Arrange
            LoginUserAccountRequest request = new LoginUserAccountRequest("test@example.com", "password");
            UserAccount userAccount = new UserAccount("id", "username", "password", LocalDateTime.now(), "test@example.com", Set.of());
            UserAccountService mockServiceMethods = Mockito.spy(userAccountService);

            Mockito.when(repository.findUserAccountByEmail(request.getEmail())).thenReturn(userAccount);
            Mockito.doReturn(false).when(mockServiceMethods).checkPassword(request.getPassword(), userAccount.getPassword());

            // Act
            assertThrows(UnauthorizedLoginException.class, () -> mockServiceMethods.loginUserAccount(request));

            // Assert
            Mockito.verify(repository, Mockito.times(1)).findUserAccountByEmail(request.getEmail());
            Mockito.verify(converter, Mockito.never()).convert(Mockito.any());
        }
    }
