package uk.ugurtech.wallet.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.ugurtech.wallet.model.constant.Currency;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WalletCreateRequest {
    private String name;
    private Currency currency;
    private Boolean canShopping;
    private Boolean canWithdraw;
}
