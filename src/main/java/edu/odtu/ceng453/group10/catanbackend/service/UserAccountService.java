package edu.odtu.ceng453.group10.catanbackend.service;

import edu.odtu.ceng453.group10.catanbackend.dto.*;
import edu.odtu.ceng453.group10.catanbackend.model.UserAccount;
import edu.odtu.ceng453.group10.catanbackend.repository.UserAccountRepository;
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
        String email = request.getEmail();
        UserAccount userAccount = repository.findUserAccountByEmail(email);

        if(userAccount != null){
            String userPassword = userAccount.getPassword();
            if (checkPassword(request.getPassword(), userPassword)){
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

    private String hashPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    private boolean checkPassword(String plainPassword, String hashedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(plainPassword, hashedPassword);
    }
}
