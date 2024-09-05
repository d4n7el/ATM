package com.dzo.test_bank.model.repository;

import com.dzo.test_bank.model.entity.Account;
import com.dzo.test_bank.projection.AccountProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query("SELECT " +
            "a.accountId AS accountId, " +
            "a.accountName AS accountName, " +
            "a.accountNum AS accountNum, " +
            "a.currentBalance AS currentBalance, " +
            "a.previousBalance AS previousBalance " +
            "FROM Account a " +
            "INNER JOIN User u ON a.userId = u.userId " +
            "WHERE a.userId = :userId")
    List<AccountProjection> findUsersAccountsDetails(@Param("userId") Integer userId);

    @Query(value = "SELECT a.account_id, a.user_id, a.account_name, a.account_num, a.created_at, " +
            "a.updated_at, a.current_balance, a.previous_balance, u.first_name, u.last_name, u.email " +
            "FROM accounts a " +
            "JOIN users u ON a.user_id = u.user_id " +
            "WHERE a.account_id = :accountId", nativeQuery = true)
    Account findByIdDetail(@Param("accountId") Integer accountId);

    @Query(value = "SELECT a.account_id, a.user_id, a.account_name, a.account_num, a.created_at, " +
            "a.updated_at, a.current_balance, a.previous_balance, u.first_name, u.last_name, u.email " +
            "FROM accounts a " +
            "JOIN users u ON a.user_id = u.user_id " ,nativeQuery = true)
    List<Account> accountsDetail();

}
