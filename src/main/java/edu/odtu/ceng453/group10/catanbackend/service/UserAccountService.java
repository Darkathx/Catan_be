package edu.odtu.ceng453.group10.catanbackend.service;

import edu.odtu.ceng453.group10.catanbackend.config.SMTPConfig;
import edu.odtu.ceng453.group10.catanbackend.dto.*;
import edu.odtu.ceng453.group10.catanbackend.exception.UnauthorizedLoginException;
import edu.odtu.ceng453.group10.catanbackend.model.UserAccount;
import edu.odtu.ceng453.group10.catanbackend.repository.UserAccountRepository;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

/**
 * Service class for user account related operations.
 * It offers functionality to create, authenticate, and manage user accounts,
 * including resetting passwords and sending password reset emails.
 */
@Service
public class UserAccountService {
    private final UserAccountRepository repository;
    private final UserAccountDtoConverter converter;

    /**
     * Constructs a UserAccountService with the necessary UserAccountRepository and UserAccountDtoConverter.
     *
     * @param repository The repository for user account data operations.
     * @param converter  The converter to translate between UserAccount entities and DTOs.
     */
    public UserAccountService(UserAccountRepository repository,
                              UserAccountDtoConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public UserAccount getUserAccount(String userAccountId) {
      return repository.findUserAccountByEmail(userAccountId);
    }

    /**
     * Creates a new user account based on the provided request data.
     *
     * @param request Data transfer object containing new user account information.
     * @return A DTO representing the newly created user account.
     */
    public UserAccountDto createUserAccount(CreateUserAccountRequest request) {
        String hashedPw = hashPassword(request.getPassword());

        UserAccount userAccount = new UserAccount(request.getUsername(), hashedPw, LocalDateTime.now(), request.getEmail());
        try {
            return converter.convert(repository.save(userAccount));
        }
        catch (Exception e) {
            throw new UnauthorizedLoginException("Email/username already exists.");
        }

    }

    /**
     * Authenticates a user based on the provided login credentials.
     *
     * @param request Data transfer object containing login credentials.
     * @return A DTO representing the authenticated user account, or null if authentication fails.
     */
    public UserAccountDto loginUserAccount(LoginUserAccountRequest request){
        UserAccount userAccount = repository.findUserAccountByEmail(request.getEmail());

        if(userAccount != null){
            if (checkPassword(request.getPassword(), userAccount.getPassword())){
                return converter.convert(userAccount);
            }
        }
        throw new UnauthorizedLoginException("Invalid email or password.");
    }

    /**
     * Resets the password for the user account associated with the given email address.
     *
     * @param request Data transfer object containing the email and new password.
     * @return A DTO representing the user account with the password reset, or null if the user is not found.
     */
    public UserAccountDto resetPassword(ResetUserAccountPasswordRequest request){
        String email = request.getEmail();
        String newPasswordHashed = hashPassword(request.getNewPassword());
        UserAccount userAccount = repository.findUserAccountByEmail(email);
        if(userAccount == null)
            return null;

        userAccount.setPassword(newPasswordHashed);
        return converter.convert(repository.save(userAccount));
    }

    /**
     * Sends a password reset email to the user with the given login credentials.
     * It constructs the email content and uses SMTP settings to send the email.
     *
     * @param request Data transfer object containing the user's login credentials.
     * @return true if the email was sent successfully, false otherwise.
     */
    public boolean sendResetMail(LoginUserAccountRequest request) {
        UserAccountDto login = loginUserAccount(request);
        if(login == null) return false;

        Session session = SMTPConfig.getSession();
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("group10@test.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(request.getEmail()));
            message.setSubject("CENG453 Project Password Reset Link");
            message.setText("Here is your reset link: " + SMTPConfig.URL + ". Please send a put request with your email and new password.");
            Transport.send(message);
            return true;
        }
        catch (MessagingException ex) {
            return false;
        }
    }

    /**
     * Hashes a plaintext password using BCrypt.
     *
     * @param password The plaintext password to hash.
     * @return The hashed password.
     */
    public String hashPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * Checks a plaintext password against a stored hashed password to determine if they match.
     *
     * @param plainPassword The plaintext password to check.
     * @param hashedPassword The stored hashed password to check against.
     * @return true if the passwords match, false otherwise.
     */
    public boolean checkPassword(String plainPassword, String hashedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(plainPassword, hashedPassword);
    }
}
