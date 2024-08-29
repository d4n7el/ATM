package com.dzo.test_bank.service;

import com.dzo.test_bank.model.dto.OperationDetailDto;
import com.dzo.test_bank.model.dto.OperationDto;
import com.dzo.test_bank.model.entity.Operation;

import java.util.List;

public interface IOperation {
    OperationDto save(Operation operation);
    OperationDto createDeposit(Operation operation);
    OperationDto createWithdrawal(Operation operation);
    OperationDto createTransfer(Operation operation);
    List<OperationDetailDto> transactionsDetails();
}