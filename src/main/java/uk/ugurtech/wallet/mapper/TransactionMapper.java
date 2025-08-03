package uk.ugurtech.wallet.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uk.ugurtech.wallet.model.constant.TransactionType;
import uk.ugurtech.wallet.model.dto.TransactionDto;
import uk.ugurtech.wallet.model.entity.TransactionEntity;
import uk.ugurtech.wallet.model.request.TransactionCreateRequest;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionMapper {

    public static TransactionEntity map(TransactionCreateRequest request, TransactionType transactionType) {
        TransactionEntity transaction = new TransactionEntity();
        transaction.setAmount(request.getAmount());
        transaction.setType(transactionType);
        transaction.setOppositeParty(request.getOppositeParty());
        transaction.setOppositePartyType(request.getOppositePartyType());
        return transaction;
    }

    public static TransactionDto  map(TransactionEntity transaction) {
        return TransactionDto.builder()
                .id(transaction.getId())
                .createdAt(transaction.getCreatedAt())
                .updatedAt(transaction.getUpdatedAt())
                .isActive(transaction.getIsActive())
                .isDeleted(transaction.getIsDeleted())
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .status(transaction.getStatus())
                .oppositeParty(transaction.getOppositeParty())
                .oppositePartyType(transaction.getOppositePartyType())
                .wallet(WalletMapper.map(transaction.getWallet()))
                .build();
    }
}
