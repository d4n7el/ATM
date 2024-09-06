package com.dzo.test_bank.service.impl;

import com.dzo.test_bank.persistence.model.UserJpa;
import com.dzo.test_bank.persistence.repository.UserRepository;
import com.dzo.test_bank.persistence.dto.UserBasicDto;
import com.dzo.test_bank.persistence.dto.UserDto;
import com.dzo.test_bank.persistence.repository.projection.UserProjection;
import com.dzo.test_bank.service.IUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserImpl implements IUser {
    private final  UserRepository userRepository;

    public UserImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserBasicDto save(UserJpa userJpa) {
        return UserBasicDto.from(userRepository.save(userJpa));
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto findById(Integer id) {
        UserProjection user = userRepository.findByIdUser(id);
        return UserDto.from(user);
    }

    public UserJpa getById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDto> findAll() {
        List<UserProjection> users = userRepository.findAllUsers();
        return UserDto.from(users);
    }

    @Transactional
    @Override
    public void delete(UserJpa userJpa) {
        userRepository.delete(userJpa);
    }
}
