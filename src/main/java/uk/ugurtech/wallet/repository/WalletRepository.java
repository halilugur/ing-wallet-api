package uk.ugurtech.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uk.ugurtech.wallet.model.constant.Currency;
import uk.ugurtech.wallet.model.entity.WalletEntity;
import uk.ugurtech.wallet.model.filter.WalletFilter;

import java.util.List;
import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<WalletEntity, Long> {

    @Query("SELECT COUNT(w) > 0 FROM WalletEntity w " +
            "JOIN w.user u " +
            "WHERE u.tckn = :tckn AND w.currency = :currency")
    boolean existsByCurrency(String tckn, Currency currency);

    @Query("SELECT w FROM WalletEntity w " +
            "JOIN FETCH w.user u " +
            "WHERE u.tckn = :tckn " +
            "AND w.id = :walletId")
    Optional<WalletEntity> findByIdAndUser(String tckn, Long walletId);

    @Query("SELECT w FROM WalletEntity w " +
            "JOIN FETCH w.user u " +
            "WHERE u.tckn = :tckn " +
            "AND (:#{#filter.currency} IS NULL OR w.currency = :#{#filter.currency}) " +
            "AND ((:#{#filter.minAmount} IS NULL AND :#{#filter.maxAmount} IS NULL) " +
            "       OR w.balance BETWEEN :#{#filter.minAmount} AND :#{#filter.maxAmount}) ")
    List<WalletEntity> findAllByUserAndFilter(String tckn, WalletFilter filter);
}
