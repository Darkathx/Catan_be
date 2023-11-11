package edu.odtu.ceng453.group10.catanbackend.controller;

import edu.odtu.ceng453.group10.catanbackend.dto.CreateUserAccountRequest;
import edu.odtu.ceng453.group10.catanbackend.dto.UserAccountDto;
import edu.odtu.ceng453.group10.catanbackend.dto.LoginUserAccountRequest;
import edu.odtu.ceng453.group10.catanbackend.dto.ResetUserAccountPasswordRequest;
import edu.odtu.ceng453.group10.catanbackend.service.UserAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userAccount")
public class UserAccountController {

    private final UserAccountService userAccountService;

    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserAccountDto> createAccount(@RequestBody CreateUserAccountRequest request) {
        return ResponseEntity.ok(userAccountService.createUserAccount(request));
    }

    @PostMapping("/login")
    public ResponseEntity<UserAccountDto> login(@RequestBody LoginUserAccountRequest request) {
        UserAccountDto dto = userAccountService.loginUserAccount(request);
        if(dto == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return ResponseEntity.ok(userAccountService.loginUserAccount(request));
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<UserAccountDto> resetPasswordLink(@RequestBody LoginUserAccountRequest request) {
        boolean result =  userAccountService.sendResetMail(request);
        if(!result)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/resetPassword")
    public ResponseEntity<UserAccountDto> resetPassword(@RequestBody ResetUserAccountPasswordRequest request) {
        // when users clicks the link and sets a password this part will execute
        UserAccountDto dto = userAccountService.resetPassword(request);
        if(dto == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return ResponseEntity.ok(dto);
    }
}
