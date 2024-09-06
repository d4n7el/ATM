package com.dzo.test_bank.controller;

import com.dzo.test_bank.persistence.dto.AccountDto;
import com.dzo.test_bank.persistence.dto.AccountsByUserDto;
import com.dzo.test_bank.persistence.model.AccountJpa;
import com.dzo.test_bank.service.IAccount;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class AccountController extends BaseController{
    private final IAccount accountService;

    public AccountController(IAccount accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/account/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public AccountsByUserDto getAccountByUserId(@PathVariable Integer userId) {
        return accountService.getAccountsByUser(userId);
    }

    @GetMapping("/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    public List<AccountJpa> accounts() {
        return accountService.findAll();
    }

    @GetMapping("/account/{accountId}")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountJpa account(@PathVariable Integer accountId) {
        return accountService.findByIdDetail(accountId);
    }

    @PostMapping("/account")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDto create(@RequestBody @Valid AccountJpa accountJpa) {
        AccountJpa newAccountJpa = accountService.save(accountJpa);
        return AccountDto.from(newAccountJpa);
    }

    @PutMapping("/account")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDto update(@RequestBody @Valid AccountJpa accountJpa) {
        AccountJpa newAccountJpa = accountService.update(accountJpa);
        return AccountDto.from(newAccountJpa);
    }

    @DeleteMapping("/account/{accountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer accountId) {
        accountService.delete(accountService.findByIdDetail(accountId));
    }
}
