package edu.odtu.ceng453.group10.catanbackend.dto;

import edu.odtu.ceng453.group10.catanbackend.model.UserAccount;
import org.springframework.stereotype.Component;

/**
 * Component for converting UserAccount entities to UserAccountDto data transfer objects.
 */
@Component
public class UserAccountDtoConverter {

    /**
     * Converts a UserAccount entity to a UserAccountDto.
     *
     * @param from The UserAccount entity to convert.
     * @return The corresponding UserAccountDto.
     */
    public UserAccountDto convert(UserAccount from) {
        return new UserAccountDto(
                from.getUsername(),
                from.getEmail()
        );
    }
}
