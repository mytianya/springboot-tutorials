package vip.codehome.springboot.tutorials.dao;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import vip.codehome.springboot.tutorials.entity.UserDO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest  {
    @Autowired
    UserRepository userRepository;
    @Test
    @Ignore
    public void testFindAll(){
        Page<UserDO> userDOS= userRepository.findAll(PageRequest.of(1,10));
        Assert.assertNotNull(userDOS.getContent());
    }
    @Test(expected = RuntimeException.class)
    public void testNullPointerException(){
        throw new RuntimeException();
    }
}