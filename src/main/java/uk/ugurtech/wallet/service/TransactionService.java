package uk.ugurtech.wallet.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.ugurtech.wallet.exception.TransactionException;
import uk.ugurtech.wallet.exception.WalletException;
import uk.ugurtech.wallet.mapper.TransactionMapper;
import uk.ugurtech.wallet.model.constant.TransactionStatus;
import uk.ugurtech.wallet.model.constant.TransactionType;
import uk.ugurtech.wallet.model.dto.TransactionDto;
import uk.ugurtech.wallet.model.entity.TransactionEntity;
import uk.ugurtech.wallet.model.entity.WalletEntity;
import uk.ugurtech.wallet.model.request.TransactionCreateRequest;
import uk.ugurtech.wallet.repository.TransactionRepository;
import uk.ugurtech.wallet.repository.WalletRepository;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;

    public TransactionDto deposit(String tckn, TransactionCreateRequest request) {
        WalletEntity wallet = getWallet(tckn, request);

        TransactionEntity transaction = TransactionMapper.map(request, TransactionType.DEPOSIT);

        if (transaction.getAmount().compareTo(BigDecimal.valueOf(1000)) > 0) {
            transaction.setStatus(TransactionStatus.PENDING);
            wallet.setBalance(wallet.getBalance().add(transaction.getAmount()));
        } else {
            transaction.setStatus(TransactionStatus.APPROVED);
            wallet.setBalance(wallet.getBalance().add(transaction.getAmount()));
            wallet.setUsableBalance(wallet.getUsableBalance().add(transaction.getAmount()));
        }

        transaction.setWallet(wallet);
        walletRepository.save(wallet);
        TransactionEntity saved = transactionRepository.save(transaction);
        return TransactionMapper.map(saved);
    }

    public TransactionDto withdraw(String tckn, TransactionCreateRequest request) {
        WalletEntity wallet = getWallet(tckn, request);

        if (!wallet.getCanWithdraw()) {
            throw new TransactionException("Withdraw is not allowed on this wallet");
        }

        TransactionEntity transaction = TransactionMapper.map(request, TransactionType.DEPOSIT);

        if (transaction.getAmount().compareTo(BigDecimal.valueOf(1000)) > 0) {
            transaction.setStatus(TransactionStatus.PENDING);
            wallet.setUsableBalance(wallet.getUsableBalance().subtract(transaction.getAmount()));
        } else {
            transaction.setStatus(TransactionStatus.APPROVED);
            wallet.setUsableBalance(wallet.getUsableBalance().subtract(transaction.getAmount()));
            wallet.setBalance(wallet.getBalance().subtract(transaction.getAmount()));
        }

        transaction.setWallet(wallet);
        walletRepository.save(wallet);
        TransactionEntity saved = transactionRepository.save(transaction);
        return TransactionMapper.map(saved);
    }

    public List<TransactionDto> getTransactions(String tckn, Long walletId) {
        return transactionRepository.findAllByWalletAndUser(tckn, walletId).stream()
                .map(TransactionMapper::map)
                .toList();
    }

    public TransactionDto statusDecision(Long transactionId, TransactionStatus status) {
        TransactionEntity transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionException("Transaction not found"));

        WalletEntity wallet = transaction.getWallet();

        if (transaction.getStatus() != TransactionStatus.PENDING) {
            throw new TransactionException("Transaction is not pending");
        }

        if (status == TransactionStatus.APPROVED) {
            if (transaction.getType() == TransactionType.DEPOSIT) {
                wallet.setUsableBalance(wallet.getUsableBalance().add(transaction.getAmount()));
            } else if (transaction.getType() == TransactionType.WITHDRAW) {
                wallet.setBalance(wallet.getBalance().subtract(transaction.getAmount()));
            }
        } else if (status == TransactionStatus.DENIED) {
            if (transaction.getType() == TransactionType.DEPOSIT) {
                wallet.setBalance(wallet.getBalance().subtract(transaction.getAmount()));
            } else if (transaction.getType() == TransactionType.WITHDRAW) {
                wallet.setUsableBalance(wallet.getUsableBalance().add(transaction.getAmount()));
            }
        }

        transaction.setStatus(status);
        walletRepository.save(wallet);
        TransactionEntity saved = transactionRepository.save(transaction);
        return TransactionMapper.map(saved);
    }

    private WalletEntity getWallet(String tckn, TransactionCreateRequest request) {
        return walletRepository.findByIdAndUser(tckn, request.getWalletId())
                .orElseThrow(() -> new WalletException("Wallet not found"));
    }
}
