package uk.ugurtech.wallet.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import uk.ugurtech.wallet.model.constant.UserRole;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseDto {
    private String firstname;
    private String lastname;
    private String tckn;
    private UserRole role;
}
