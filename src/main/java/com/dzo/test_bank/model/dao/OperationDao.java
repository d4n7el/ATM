package com.dzo.test_bank.model.dao;

import com.dzo.test_bank.model.entity.Operation;
import com.dzo.test_bank.projection.OperationProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationDao extends JpaRepository<Operation,Integer> {
    @Query("SELECT op.operationId AS operationId, " +
            "op.sourceAccountId AS sourceAccountId, " +
            "op.targetAccountId AS targetAccountId, " +
            "op.transactionType AS transactionType, " +
            "op.createdAt AS createdAt, " +
            "op.transactionAmount AS transactionAmount, " +
            "op.previousBalance AS previousBalance, " +
            "op.finalBalance AS finalBalance, " +
            "sourceAccount.accountName AS sourceAccountName, " +
            "sourceAccount.accountNum AS sourceAccountNum, " +
            "targetAccount.accountName AS targetAccountName, " +
            "targetAccount.accountNum AS targetAccountNum, " +
            "u.firstName as firstName, " +
            "u.lastName as lastName, " +
            "u.email as email, " +
            "u.userId as userId " +
            "FROM Operation as op " +
            "LEFT JOIN Account AS sourceAccount ON op.sourceAccountId = sourceAccount.accountId " +
            "LEFT JOIN Account AS targetAccount ON op.targetAccountId = targetAccount.accountId " +
            "INNER JOIN User AS u ON op.createByUserId = u.userId")
    List<OperationProjection> transactionsDetails();
}
