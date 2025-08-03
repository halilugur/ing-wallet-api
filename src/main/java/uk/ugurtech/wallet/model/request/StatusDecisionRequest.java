package uk.ugurtech.wallet.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.ugurtech.wallet.model.constant.TransactionStatus;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StatusDecisionRequest {
    private TransactionStatus status;
}
