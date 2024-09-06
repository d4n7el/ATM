package com.dzo.test_bank.service.impl;

import com.dzo.test_bank.persistence.dto.AccountDto;
import com.dzo.test_bank.persistence.dto.OperationDetailDto;
import com.dzo.test_bank.persistence.dto.OperationDto;
import com.dzo.test_bank.persistence.model.AccountJpa;
import com.dzo.test_bank.persistence.model.OperationJpa;
import com.dzo.test_bank.persistence.types.TransactionType;
import com.dzo.test_bank.persistence.repository.OperationRepository;
import com.dzo.test_bank.service.IAccount;
import com.dzo.test_bank.service.IOperation;
import com.dzo.test_bank.service.IUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OperationImpl implements IOperation {
    private final IAccount accountService;
    private final IUser userService;
    private final OperationRepository operationRepository;

    public OperationImpl(IAccount accountService, IUser userService, OperationRepository operationRepository) {
        this.accountService = accountService;
        this.userService = userService;
        this.operationRepository = operationRepository;
    }

    @Transactional
    @Override
    public OperationDto save(OperationJpa operationJpa) {
        return switch (operationJpa.getTransactionType()) {
            case DEPOSIT -> createDeposit(operationJpa);
            case WITHDRAWAL -> createWithdrawal(operationJpa);
            case TRANSFER -> createTransfer(operationJpa);
            default -> null;
        };
    }

    @Override
    public OperationDto createDeposit(OperationJpa operationJpa) {
        AccountJpa accountJpa = accountService.findByIdDetail(operationJpa.getSourceAccountId());
        userService.getById(operationJpa.getCreateByUserId());

        accountJpa.setPreviousBalance(accountJpa.getCurrentBalance());
        accountJpa.setCurrentBalance(accountJpa.getCurrentBalance() + operationJpa.getTransactionAmount());

        operationJpa.setFinalBalance(accountJpa.getCurrentBalance());
        operationJpa.setPreviousBalance(accountJpa.getPreviousBalance());

        accountService.updateWithAmount(accountJpa);
        operationRepository.save(operationJpa);
        return OperationDto.from(operationJpa, null,  AccountDto.from(accountJpa));
    }

    @Override
    public OperationDto createWithdrawal(OperationJpa operationJpa) {
        AccountJpa accountJpa = accountService.findByIdDetail(operationJpa.getSourceAccountId());
        userService.getById(operationJpa.getCreateByUserId());

        accountJpa.setPreviousBalance(accountJpa.getCurrentBalance());
        accountJpa.setCurrentBalance(accountJpa.getCurrentBalance() - operationJpa.getTransactionAmount());

        operationJpa.setFinalBalance(accountJpa.getCurrentBalance());
        operationJpa.setPreviousBalance(accountJpa.getPreviousBalance());

        accountService.updateWithAmount(accountJpa);
        operationRepository.save(operationJpa);
        return OperationDto.from(operationJpa, AccountDto.from(accountJpa), null);
    }

    @Override
    public OperationDto createTransfer(OperationJpa operationJpa) {
        AccountJpa sourceAccountJpa = accountService.findByIdDetail(operationJpa.getSourceAccountId());
        AccountJpa targetAccountJpa = accountService.findByIdDetail(operationJpa.getTargetAccountId());
        userService.getById(operationJpa.getCreateByUserId());

        sourceAccountJpa.setPreviousBalance(sourceAccountJpa.getCurrentBalance());
        sourceAccountJpa.setCurrentBalance(sourceAccountJpa.getCurrentBalance() - operationJpa.getTransactionAmount());

        targetAccountJpa.setPreviousBalance(targetAccountJpa.getCurrentBalance());
        targetAccountJpa.setCurrentBalance(targetAccountJpa.getCurrentBalance() + operationJpa.getTransactionAmount());

        accountService.updateWithAmount(sourceAccountJpa);
        OperationJpa debit = operationRepository.save(OperationJpa.toDebit(operationJpa, sourceAccountJpa));

        accountService.updateWithAmount(targetAccountJpa);
        operationRepository.save(OperationJpa.toCredit(operationJpa, targetAccountJpa));

        return OperationDto.from(
                debit,
                AccountDto.from(sourceAccountJpa),
                AccountDto.from(targetAccountJpa));
    }

    @Override
    public List<OperationDetailDto> transactionsDetails(
            TransactionType transactionType,
            String firstName,
            String sourceAccountNum,
            String targetAccountNum) {
        return OperationDetailDto.from(operationRepository.transactionsDetails(
                transactionType,
                firstName,
                sourceAccountNum,
                targetAccountNum));
    }
}
