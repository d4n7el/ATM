package com.dzo.test_bank.service.impl;

import com.dzo.test_bank.persistence.repository.AccountRepository;
import com.dzo.test_bank.persistence.dto.AccountsByUserDto;
import com.dzo.test_bank.persistence.dto.UserDto;
import com.dzo.test_bank.persistence.model.AccountJpa;
import com.dzo.test_bank.persistence.repository.projection.AccountProjection;
import com.dzo.test_bank.service.IAccount;
import com.dzo.test_bank.service.IUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountImpl implements IAccount {
    private final AccountRepository accountRepository;
    private final IUser userService;

    public AccountImpl(AccountRepository accountRepository, IUser userService) {
        this.accountRepository = accountRepository;
        this.userService = userService;
    }

    @Override
    public AccountJpa save(AccountJpa accountJpa) {
        accountJpa.setPreviousBalance(0.00);
        accountJpa.setCurrentBalance(0.00);
        return accountRepository.save(accountJpa);
    }

    @Override
    public AccountJpa update(AccountJpa accountJpa) {
        AccountJpa accountJpaPersist = accountRepository.findById(accountJpa.getAccountId()).orElse(null);
        if (accountJpaPersist != null) {
            accountJpa.setCurrentBalance(accountJpaPersist.getCurrentBalance());
            accountJpa.setPreviousBalance(accountJpaPersist.getPreviousBalance());
            return accountRepository.save(accountJpa);
        }
        return null;
    }

    @Override
    public AccountJpa updateWithAmount(AccountJpa accountJpa) {
        accountRepository.findById(accountJpa.getAccountId());
        return accountRepository.save(accountJpa);
    }

    @Override
    public AccountJpa findByIdDetail(Integer id) {
        return accountRepository.findByIdDetail(id);
    }

    @Override
    public List<AccountJpa> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public void delete(AccountJpa accountJpa) {
        accountRepository.delete(accountJpa);
    }

    @Override
    public AccountsByUserDto getAccountsByUser(Integer userId) {
        UserDto user = userService.findById(userId);
        List<AccountProjection> accounts = accountRepository.findUsersAccountsDetails(userId);

        return AccountsByUserDto.from(accounts, user);
    }
}
