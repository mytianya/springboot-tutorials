package vip.codehome.springboot.tutorials.service;

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
    @Cacheable(value = "users")
    List<UserDO> queryUsers();
    @Cacheable(value = "users1")
    List<UserDO> queryUsers1();
}
