package uk.ugurtech.wallet.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.ugurtech.wallet.model.constant.OppositePartyType;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCreateRequest {
    private Long walletId;
    private BigDecimal amount;
    private String oppositeParty;
    private OppositePartyType oppositePartyType;
}
