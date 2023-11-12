package edu.odtu.ceng453.group10.catanbackend.service;

import edu.odtu.ceng453.group10.catanbackend.config.SMTPConfig;
import edu.odtu.ceng453.group10.catanbackend.dto.*;
import edu.odtu.ceng453.group10.catanbackend.model.UserAccount;
import edu.odtu.ceng453.group10.catanbackend.repository.UserAccountRepository;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class UserAccountService {
    private final UserAccountRepository repository;
    private final UserAccountDtoConverter converter;


    public UserAccountService(UserAccountRepository repository,
                              UserAccountDtoConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }


    public UserAccountDto createUserAccount(CreateUserAccountRequest request) {
        String hashedPw = hashPassword(request.getPassword());

        UserAccount userAccount = new UserAccount(request.getUsername(), hashedPw, LocalDateTime.now(), request.getEmail());
        return converter.convert(repository.save(userAccount));
    }

    public UserAccountDto loginUserAccount(LoginUserAccountRequest request){
        UserAccount userAccount = repository.findUserAccountByEmail(request.getEmail());

        if(userAccount != null){
            if (checkPassword(request.getPassword(), userAccount.getPassword())){
                return converter.convert(userAccount);
            }
        }
        return null;
    }

    public UserAccountDto resetPassword(ResetUserAccountPasswordRequest request){
        String email = request.getEmail();
        String newPasswordHashed = hashPassword(request.getNewPassword());
        UserAccount userAccount = repository.findUserAccountByEmail(email);
        if(userAccount == null)
            return null;

        userAccount.setPassword(newPasswordHashed);
        return converter.convert(repository.save(userAccount));
    }

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

    public String hashPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    public boolean checkPassword(String plainPassword, String hashedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(plainPassword, hashedPassword);
    }
}
