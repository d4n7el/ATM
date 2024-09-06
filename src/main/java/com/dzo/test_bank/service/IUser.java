package com.dzo.test_bank.service;

import com.dzo.test_bank.persistence.dto.UserBasicDto;
import com.dzo.test_bank.persistence.dto.UserDto;
import com.dzo.test_bank.persistence.model.UserJpa;

import java.util.List;

public interface IUser {
    UserBasicDto save(UserJpa userJpa);
    UserDto findById(Integer id);
    UserJpa getById(Integer id);
    List<UserDto> findAll();
    void delete(UserJpa userJpa);
}
