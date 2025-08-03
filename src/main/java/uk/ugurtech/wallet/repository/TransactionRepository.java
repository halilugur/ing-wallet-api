package uk.ugurtech.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uk.ugurtech.wallet.model.entity.TransactionEntity;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    @Query("SELECT t FROM TransactionEntity t " +
            "JOIN t.wallet w " +
            "JOIN w.user u " +
            "WHERE w.id = :walletId " +
            "AND u.tckn = :tckn")
    List<TransactionEntity> findAllByWalletAndUser(String tckn, Long walletId);


}
