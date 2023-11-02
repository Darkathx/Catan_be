package edu.odtu.ceng453.group10.catanbackend.dto;

import edu.odtu.ceng453.group10.catanbackend.model.UserAccount;
import org.springframework.stereotype.Component;

@Component
public class UserAccountDtoConverter {


    public UserAccountDto convert(UserAccount from) {
        return new UserAccountDto(
                from.getUsername(),
                from.getEmail()
        );
    }
}
