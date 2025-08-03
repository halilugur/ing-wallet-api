package uk.ugurtech.wallet.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import uk.ugurtech.wallet.model.constant.Currency;

import java.math.BigDecimal;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class WalletDto extends BaseDto{
    private String name;
    private Currency currency;
    private BigDecimal balance;
    private BigDecimal usableBalance;
    private Boolean canShopping;
    private Boolean canWithdraw;
    private UserDto customer;
}
