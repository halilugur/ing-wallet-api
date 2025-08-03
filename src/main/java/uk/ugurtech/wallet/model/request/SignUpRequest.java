package uk.ugurtech.wallet.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    private String firstname;
    private String lastname;
    private String tckn;
    private String password;
}
