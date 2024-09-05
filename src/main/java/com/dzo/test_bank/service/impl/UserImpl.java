package com.dzo.test_bank.service.impl;

import com.dzo.test_bank.model.repository.UserRepository;
import com.dzo.test_bank.model.dto.UserBasicDto;
import com.dzo.test_bank.model.dto.UserDto;
import com.dzo.test_bank.projection.UserProjection;
import com.dzo.test_bank.model.entity.User;
import com.dzo.test_bank.service.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserImpl implements IUser {

    @Autowired()
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserBasicDto save(User user) {
        return UserBasicDto.from(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto findById(Integer id) {
        UserProjection user = userRepository.findByIdUser(id);
        return UserDto.from(user);
    }

    public User getById(Integer id) {
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
    public void delete(User user) {
        userRepository.delete(user);
    }
}
