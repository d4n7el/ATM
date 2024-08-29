package com.dzo.test_bank.service;

import com.dzo.test_bank.model.dto.AccountsByUserDto;
import com.dzo.test_bank.model.entity.Account;
import java.util.List;

public interface IAccount {
    Account save(Account account);
    Account update(Account account);
    Account findByIdDetail(Integer accountId);
    List<Account> findAll();
    void delete(Account account);
    AccountsByUserDto getAccountsByUser(Integer userId);
    Account updateWhitAmount(Account account);
}
