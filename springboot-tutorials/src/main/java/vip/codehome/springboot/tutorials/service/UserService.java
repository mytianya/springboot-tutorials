package vip.codehome.springboot.tutorials.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import vip.codehome.springboot.tutorials.entity.UserDO;

import java.util.List;

/***
 *@author zyw
 *@createTime 2020/8/27 10:12
 *@description
 *@version 1.0
 */
public interface UserService {
    @Cacheable(value = "users",key = "#userDO.id")
    List<UserDO> queryUsers(UserDO userDO);
    @CachePut(value = "users",key ="#userDO.id" )
    void saveUser(UserDO userDO);
    @CacheEvict(value = "users",key = "#userDO.id")
    void removeUser(UserDO userDO);
}
