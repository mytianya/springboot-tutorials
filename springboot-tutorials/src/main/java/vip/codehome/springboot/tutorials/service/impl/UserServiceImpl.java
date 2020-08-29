package vip.codehome.springboot.tutorials.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.codehome.springboot.tutorials.entity.UserDO;
import vip.codehome.springboot.tutorials.mapper.UserMapper;
import vip.codehome.springboot.tutorials.service.UserService;

import java.util.List;

/***
 *@author zyw
 *@createTime 2020/8/27 10:12
 *@description
 *@version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<UserDO> queryUsers(UserDO userDO) {
        return userMapper.select(userDO);
    }

    @Override
    public void saveUser(UserDO userDO) {
        userMapper.insert(userDO);
    }

    @Override
    public void removeUser(UserDO userDO) {
        userMapper.delete(userDO);
    }
}
