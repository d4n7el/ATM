package com.dzo.test_bank.service.impl;

import com.dzo.test_bank.model.repository.AccountRepository;
import com.dzo.test_bank.model.dto.AccountsByUserDto;
import com.dzo.test_bank.model.dto.UserDto;
import com.dzo.test_bank.model.entity.Account;
import com.dzo.test_bank.projection.AccountProjection;
import com.dzo.test_bank.service.IAccount;
import com.dzo.test_bank.service.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountImpl implements IAccount {

    @Autowired()
    private AccountRepository accountRepository;

    @Autowired()
    private IUser userService;

    @Override
    public Account save(Account account) {
        account.setPreviousBalance(0.00);
        account.setCurrentBalance(0.00);
        return accountRepository.save(account);
    }

    @Override
    public Account update(Account account) {
        Account accountPersist = accountRepository.findById(account.getAccountId()).orElse(null);
        if (accountPersist != null) {
            account.setCurrentBalance(accountPersist.getCurrentBalance());
            account.setPreviousBalance(accountPersist.getPreviousBalance());
            return accountRepository.save(account);
        }
        return null;
    }

    public Account updateWhitAmount(Account account) {
        accountRepository.findById(account.getAccountId());
        return accountRepository.save(account);
    }

    @Override
    public Account findByIdDetail(Integer id) {
        return accountRepository.findByIdDetail(id);
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public void delete(Account account) {
        accountRepository.delete(account);
    }

    @Override
    public AccountsByUserDto getAccountsByUser(Integer userId) {
        UserDto user = userService.findById(userId);
        List<AccountProjection> accounts = accountRepository.findUsersAccountsDetails(userId);

        return AccountsByUserDto.from(accounts, user);
    }
}
