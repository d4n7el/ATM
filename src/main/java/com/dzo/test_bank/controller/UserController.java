package com.dzo.test_bank.controller;

import com.dzo.test_bank.model.dto.UserBasicDto;
import com.dzo.test_bank.model.dto.UserDto;
import com.dzo.test_bank.model.entity.User;
import com.dzo.test_bank.service.IUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class UserController extends BaseController{
    @Autowired
    private IUser userService;

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
    public UserBasicDto create(@RequestBody @Valid User user) {
        return userService.save(user);
    }

    @PutMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public UserBasicDto update(@RequestBody @Valid User user) {
        return create(user);
    }

    @DeleteMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer userId) {
        User user = userService.getById(userId);
        userService.delete(user);
    }
}
