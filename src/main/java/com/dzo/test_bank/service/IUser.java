package com.dzo.test_bank.service;

import com.dzo.test_bank.model.dto.UserBasicDto;
import com.dzo.test_bank.model.dto.UserDto;
import com.dzo.test_bank.model.entity.User;

import java.util.List;

public interface IUser {
    UserBasicDto save(User user);
    UserDto findById(Integer id);
    User getById(Integer id);
    List<UserDto> findAll();
    void delete(User user);
}
