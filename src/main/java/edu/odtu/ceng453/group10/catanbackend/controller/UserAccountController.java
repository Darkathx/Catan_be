package edu.odtu.ceng453.group10.catanbackend.controller;

import edu.odtu.ceng453.group10.catanbackend.dto.CreateUserAccountRequest;
import edu.odtu.ceng453.group10.catanbackend.dto.UserAccountDto;
import edu.odtu.ceng453.group10.catanbackend.dto.LoginUserAccountRequest;
import edu.odtu.ceng453.group10.catanbackend.dto.ResetUserAccountPasswordRequest;
import edu.odtu.ceng453.group10.catanbackend.service.UserAccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/userAccount")
@Tag(name = "User Account Controller", description = "Controller for User Account operations such as creating accounts, logging in, and resetting passwords.")
public class UserAccountController {

    private final UserAccountService userAccountService;

    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @Operation(summary = "Create a new user account",
            description = "Registers a new user with the given account details.",
            requestBody = @RequestBody(description = "User account creation details", required = true,
                    content = @Content(schema = @Schema(implementation = CreateUserAccountRequest.class))),
            responses = {
                    @ApiResponse(responseCode = "200", description = "User account successfully created",
                            content = @Content(schema = @Schema(implementation = UserAccountDto.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input")
            })
    @PostMapping("/register")
    public ResponseEntity<UserAccountDto> createAccount(@RequestBody CreateUserAccountRequest request) {
        return ResponseEntity.ok(userAccountService.createUserAccount(request));
    }

    @Operation(summary = "Login a user",
            description = "Authenticates a user with the provided login credentials.",
            requestBody = @RequestBody(description = "User login credentials", required = true,
                    content = @Content(schema = @Schema(implementation = LoginUserAccountRequest.class))),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully logged in",
                            content = @Content(schema = @Schema(implementation = UserAccountDto.class))),
                    @ApiResponse(responseCode = "404", description = "User not found")
            })
    @PostMapping("/login")
    public ResponseEntity<UserAccountDto> login(@RequestBody LoginUserAccountRequest request) {
        UserAccountDto dto = userAccountService.loginUserAccount(request);
        if(dto == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Send password reset link",
            description = "Sends a password reset link to the user's email address.",
            requestBody = @RequestBody(description = "Email details for password reset", required = true,
                    content = @Content(schema = @Schema(implementation = LoginUserAccountRequest.class))),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Reset link sent successfully"),
                    @ApiResponse(responseCode = "404", description = "User not found")
            })
    @PostMapping("/resetPassword")
    public ResponseEntity<Void> resetPasswordLink(@RequestBody LoginUserAccountRequest request) {
        boolean result = userAccountService.sendResetMail(request);
        if(!result)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Reset a user's password",
            description = "Allows a user to set a new password using the reset link.",
            requestBody = @RequestBody(description = "New password and reset token", required = true,
                    content = @Content(schema = @Schema(implementation = ResetUserAccountPasswordRequest.class))),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Password reset successfully",
                            content = @Content(schema = @Schema(implementation = UserAccountDto.class))),
                    @ApiResponse(responseCode = "404", description = "User not found or reset token invalid")
            })
    @PutMapping("/resetPassword")
    public ResponseEntity<UserAccountDto> resetPassword(@RequestBody ResetUserAccountPasswordRequest request) {
        UserAccountDto dto = userAccountService.resetPassword(request);
        if(dto == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return ResponseEntity.ok(dto);
    }
}