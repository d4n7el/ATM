package com.dzo.test_bank.controller;

import com.dzo.test_bank.model.dto.AccountDto;
import com.dzo.test_bank.model.dto.AccountsByUserDto;
import com.dzo.test_bank.model.entity.Account;
import com.dzo.test_bank.service.IAccount;
import com.dzo.test_bank.service.IUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("api/v1")
public class AccountController extends BaseController{

    @Autowired
    private IAccount accountService;

    @Autowired
    private IUser userService;

    @GetMapping("/account/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public AccountsByUserDto getAccountByUserId(@PathVariable Integer userId) {
        return accountService.getAccountsByUser(userId);
    }

    @GetMapping("/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Account> accounts() {
        return accountService.findAll();
    }

    @GetMapping("/account/{accountId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Account account(@PathVariable Integer accountId) {
        return accountService.findByIdDetail(accountId);
    }

    @PostMapping("/account")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDto create(@RequestBody @Valid Account account) {
        Account newAccount = accountService.save(account);
        return AccountDto.from(newAccount);
    }

    @PutMapping("/account")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDto update(@RequestBody @Valid Account account) {
        Account newAccount = accountService.update(account);
        return AccountDto.from(newAccount);
    }

    @DeleteMapping("/account/{accountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer accountId) {
        accountService.delete(accountService.findByIdDetail(accountId));
    }
}
