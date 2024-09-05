package com.dzo.test_bank.service.impl;

import com.dzo.test_bank.model.repository.OperationRepository;
import com.dzo.test_bank.model.dto.AccountDto;
import com.dzo.test_bank.model.dto.OperationDetailDto;
import com.dzo.test_bank.model.dto.OperationDto;
import com.dzo.test_bank.model.entity.Account;
import com.dzo.test_bank.model.entity.Operation;
import com.dzo.test_bank.model.enums.TransactionType;
import com.dzo.test_bank.service.IAccount;
import com.dzo.test_bank.service.IOperation;
import com.dzo.test_bank.service.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OperationImpl implements IOperation {

    @Autowired
    IAccount accountService;
    @Autowired
    IUser userService;
    @Autowired
    OperationRepository operationRepository;

    @Transactional
    @Override
    public OperationDto save(Operation operation) {
        if(operation.getTransactionType().name().equals(TransactionType.DEPOSIT.name()) ){
            return createDeposit(operation);
        }
        if(operation.getTransactionType().name().equals(TransactionType.WITHDRAWAL.name()) ){
            return createWithdrawal(operation);
        }
        if(operation.getTransactionType().name().equals(TransactionType.TRANSFER.name()) ){
            return createTransfer(operation);
        }
        return null;
    }

    @Override
    public OperationDto createDeposit(Operation operation) {
        Account account = accountService.findByIdDetail(operation.getSourceAccountId());
        userService.getById(operation.getCreateByUserId());

        account.setPreviousBalance(account.getCurrentBalance());
        account.setCurrentBalance(account.getCurrentBalance() + operation.getTransactionAmount());

        operation.setFinalBalance(account.getCurrentBalance());
        operation.setPreviousBalance(account.getPreviousBalance());

        accountService.updateWhitAmount(account);
        operationRepository.save(operation);
        return OperationDto.from(operation, null,  AccountDto.from(account));
    }

    @Override
    public OperationDto createWithdrawal(Operation operation) {
        Account account = accountService.findByIdDetail(operation.getSourceAccountId());
        userService.getById(operation.getCreateByUserId());

        account.setPreviousBalance(account.getCurrentBalance());
        account.setCurrentBalance(account.getCurrentBalance() - operation.getTransactionAmount());

        operation.setFinalBalance(account.getCurrentBalance());
        operation.setPreviousBalance(account.getPreviousBalance());

        accountService.updateWhitAmount(account);
        operationRepository.save(operation);
        return OperationDto.from(operation, AccountDto.from(account), null);
    }

    @Override
    public OperationDto createTransfer(Operation operation) {
        Account sourceAccount = accountService.findByIdDetail(operation.getSourceAccountId());
        Account targetAccount = accountService.findByIdDetail(operation.getTargetAccountId());
        userService.getById(operation.getCreateByUserId());

        sourceAccount.setPreviousBalance(sourceAccount.getCurrentBalance());
        sourceAccount.setCurrentBalance(sourceAccount.getCurrentBalance() - operation.getTransactionAmount());

        targetAccount.setPreviousBalance(targetAccount.getCurrentBalance());
        targetAccount.setCurrentBalance(targetAccount.getCurrentBalance() + operation.getTransactionAmount());

        accountService.updateWhitAmount(sourceAccount);
        Operation debit = operationRepository.save(Operation.toDebit(operation, sourceAccount));

        accountService.updateWhitAmount(targetAccount);
        operationRepository.save(Operation.toCredit(operation, targetAccount));

        return OperationDto.from(
                debit,
                AccountDto.from(sourceAccount),
                AccountDto.from(targetAccount));
    }

    @Override
    public List<OperationDetailDto> transactionsDetails() {
        return OperationDetailDto.from(operationRepository.transactionsDetails());
    }
}
