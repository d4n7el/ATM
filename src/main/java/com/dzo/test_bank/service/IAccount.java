package com.dzo.test_bank.service;

import com.dzo.test_bank.persistence.dto.AccountsByUserDto;
import com.dzo.test_bank.persistence.model.AccountJpa;
import java.util.List;

public interface IAccount {
    AccountJpa save(AccountJpa accountJpa);
    AccountJpa update(AccountJpa accountJpa);
    AccountJpa findByIdDetail(Integer accountId);
    List<AccountJpa> findAll();
    void delete(AccountJpa accountJpa);
    AccountsByUserDto getAccountsByUser(Integer userId);
    AccountJpa updateWithAmount(AccountJpa accountJpa);
}
