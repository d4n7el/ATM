package com.dzo.test_bank.controller;

import com.dzo.test_bank.persistence.dto.UserBasicDto;
import com.dzo.test_bank.persistence.dto.UserDto;
import com.dzo.test_bank.persistence.model.UserJpa;
import com.dzo.test_bank.service.IUser;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserController extends BaseController{
    private final IUser userService;

    public UserController(IUser userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public List<UserDto> users() {
        return userService.findAll();
    }

    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(@PathVariable Integer userId) {
       return  userService.findById(userId);
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public UserBasicDto create(@RequestBody @Valid UserJpa userJpa) {
        return userService.save(userJpa);
    }

    @PutMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public UserBasicDto update(@RequestBody @Valid UserJpa userJpa) {
        return create(userJpa);
    }

    @DeleteMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer userId) {
        UserJpa userJpa = userService.getById(userId);
        userService.delete(userJpa);
    }
}
