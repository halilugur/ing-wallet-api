package uk.ugurtech.wallet.model.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.ugurtech.wallet.model.constant.Currency;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WalletFilter {
    private Currency currency;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
}
