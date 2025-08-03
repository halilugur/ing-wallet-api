package uk.ugurtech.wallet.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.ugurtech.wallet.model.constant.Currency;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wallets")
public class WalletEntity extends BaseEntity {
    private String name;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private BigDecimal balance = BigDecimal.ZERO;
    private BigDecimal usableBalance = BigDecimal.ZERO;

    private Boolean canShopping;
    private Boolean canWithdraw;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;
}
