package com.dzo.test_bank.service.impl;

import com.dzo.test_bank.model.dao.UserDao;
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
    private UserDao userDao;

    @Override
    @Transactional
    public UserBasicDto save(User user) {
        return UserBasicDto.from(userDao.save(user));
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto findById(Integer id) {
        UserProjection user = userDao.findByIdUser(id);
        return UserDto.from(user);
    }

    public User getById(Integer id) {
        return userDao.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDto> findAll() {
        List<UserProjection> users = userDao.findAllUsers();
        return UserDto.from(users);
    }

    @Transactional
    @Override
    public void delete(User user) {
        userDao.delete(user);
    }
}
