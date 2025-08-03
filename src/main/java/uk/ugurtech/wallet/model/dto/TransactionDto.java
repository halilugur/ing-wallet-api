package uk.ugurtech.wallet.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import uk.ugurtech.wallet.model.constant.OppositePartyType;
import uk.ugurtech.wallet.model.constant.TransactionStatus;
import uk.ugurtech.wallet.model.constant.TransactionType;

import java.math.BigDecimal;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto extends BaseDto {
    private BigDecimal amount;
    private TransactionType type;
    private TransactionStatus status;
    private OppositePartyType oppositePartyType;
    private String oppositeParty;
    private WalletDto wallet;
}
