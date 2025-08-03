package uk.ugurtech.wallet.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uk.ugurtech.wallet.model.dto.WalletDto;
import uk.ugurtech.wallet.model.entity.WalletEntity;
import uk.ugurtech.wallet.model.request.WalletCreateRequest;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class WalletMapper {
    public static WalletDto map(WalletEntity walletEntity) {
        return WalletDto.builder()
                .id(walletEntity.getId())
                .createdAt(walletEntity.getCreatedAt())
                .updatedAt(walletEntity.getUpdatedAt())
                .isActive(walletEntity.getIsActive())
                .isDeleted(walletEntity.getIsDeleted())
                .name(walletEntity.getName())
                .currency(walletEntity.getCurrency())
                .balance(walletEntity.getBalance())
                .usableBalance(walletEntity.getUsableBalance())
                .canWithdraw(walletEntity.getCanWithdraw())
                .canShopping(walletEntity.getCanShopping())
                .customer(UserMapper.map(walletEntity.getUser()))
                .build();
    }

    public static WalletEntity map(WalletCreateRequest request) {
        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setName(request.getName());
        walletEntity.setCurrency(request.getCurrency());
        walletEntity.setCanShopping(request.getCanShopping());
        walletEntity.setCanWithdraw(request.getCanWithdraw());
        return walletEntity;
    }
}
