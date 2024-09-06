package com.dzo.test_bank.persistence.repository;

import com.dzo.test_bank.persistence.model.OperationJpa;
import com.dzo.test_bank.persistence.types.TransactionType;
import com.dzo.test_bank.persistence.repository.projection.OperationProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<OperationJpa,Integer> {
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
            "FROM OperationJpa as op " +
            "LEFT JOIN AccountJpa AS sourceAccount ON op.sourceAccountId = sourceAccount.accountId " +
            "LEFT JOIN AccountJpa AS targetAccount ON op.targetAccountId = targetAccount.accountId " +
            "INNER JOIN UserJpa AS u ON op.createByUserId = u.userId " +
            "WHERE (:transactionType IS NULL OR op.transactionType LIKE :transactionType) " +
            "AND (:firstName IS NULL OR u.firstName LIKE %:firstName%) " +
            "AND (:sourceAccountNum IS NULL OR sourceAccount.accountNum LIKE %:sourceAccountNum%) " +
            "AND (:targetAccountNum IS NULL OR targetAccount.accountNum LIKE %:targetAccountNum%) ")
    List<OperationProjection> transactionsDetails(
            @Param("transactionType") TransactionType transactionType,
            @Param("firstName") String firstName,
            @Param("sourceAccountNum") String sourceAccountNum,
            @Param("targetAccountNum") String targetAccountNum);
}
