package com.dzo.test_bank.service;

import com.dzo.test_bank.persistence.dto.OperationDetailDto;
import com.dzo.test_bank.persistence.dto.OperationDto;
import com.dzo.test_bank.persistence.model.OperationJpa;
import com.dzo.test_bank.persistence.types.TransactionType;

import java.util.List;

public interface IOperation {
    OperationDto save(OperationJpa operationJpa);
    OperationDto createDeposit(OperationJpa operationJpa);
    OperationDto createWithdrawal(OperationJpa operationJpa);
    OperationDto createTransfer(OperationJpa operationJpa);
    List<OperationDetailDto> transactionsDetails(
            TransactionType transactionType,
            String firstName,
            String sourceAccountNum,
            String targetAccountNum);
}
