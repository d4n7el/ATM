package com.dzo.test_bank.persistence.repository;
import com.dzo.test_bank.persistence.model.AccountJpa;
import com.dzo.test_bank.persistence.repository.projection.AccountProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountJpa, Integer> {

    @Query("SELECT " +
            "a.accountId AS accountId, " +
            "a.accountName AS accountName, " +
            "a.accountNum AS accountNum, " +
            "a.currentBalance AS currentBalance, " +
            "a.previousBalance AS previousBalance " +
            "FROM AccountJpa a " +
            "INNER JOIN UserJpa u ON a.userId = u.userId " +
            "WHERE a.userId = :userId")
    List<AccountProjection> findUsersAccountsDetails(@Param("userId") Integer userId);

    @Query("SELECT a.accountId, a.userId, a.accountName, a.accountNum, a.createdAt, " +
            "a.updatedAt, a.currentBalance, a.previousBalance, u.firstName, u.lastName, u.email " +
            "FROM AccountJpa a " +
            "JOIN UserJpa u ON a.userId = u.userId " +
            "WHERE a.accountId = :accountId")
    AccountJpa findByIdDetail(@Param("accountId") Integer accountId);

    @Query("SELECT a.accountId, a.userId, a.accountName, a.accountNum, a.createdAt, " +
            "a.updatedAt, a.currentBalance, a.previousBalance, u.firstName, u.lastName, u.email " +
            "FROM AccountJpa a " +
            "JOIN UserJpa u ON a.userId = u.userId ")
    List<AccountJpa> accountsDetail();
}
