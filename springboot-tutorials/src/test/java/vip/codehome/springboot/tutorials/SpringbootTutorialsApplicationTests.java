package vip.codehome.springboot.tutorials;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import vip.codehome.springboot.tutorials.dao.UserRepository;
import vip.codehome.springboot.tutorials.entity.UserDO;

import java.util.List;


@SpringBootTest
class SpringbootTutorialsApplicationTests {
    @Autowired
    UserRepository userRepository;
    @Test
    void contextLoads() {
        Pageable pageable= PageRequest.of(0,10);
        Page<UserDO> pageUsers=userRepository.findAll(pageable
        );
        List<UserDO> users=pageUsers.getContent();
       int totalPages= pageUsers.getTotalPages();
    }

}
