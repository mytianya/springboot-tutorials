package vip.codehome.springboot.tutorials.dao;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vip.codehome.springboot.tutorials.entity.UserDO;
import vip.codehome.springboot.tutorials.mapper.UserMapper;

import java.time.LocalDateTime;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    UserMapper userMapper;
    //插入
    @Test
    public void testAdd(){
        UserDO userDO=new UserDO();
        userDO.setPasswd("codehome");
        userDO.setAccount("codehome");
        userDO.setName("name");
        userDO.setForbidden(true);
        userDO.setLoginTime(LocalDateTime.now());
        userMapper.insert(userDO);
    }
    //分页
    @Test
    public void page(){
        PageHelper.startPage(0,10);
        UserDO userDO=new UserDO();
        userDO.setName("n");
       PageInfo<UserDO> userDOPage= new PageInfo<>(userMapper.select(userDO));
    }
    //更新
    @Test
    public void update(){
        UserDO userDO=new UserDO();
        userDO.setId(1L);
        userDO.setName("编程之家");
        userMapper.update(userDO);
    }
    //删除
    @Test
    public void delete(){
        UserDO userDO=new UserDO();
        userDO.setId(1L);
        userMapper.delete(userDO);
    }
    //批量插入
    @Test
    public void testAddBatch(){
        UserDO userDO=new UserDO();
        userDO.setPasswd("codehome");
        userDO.setAccount("codehome");
        userDO.setName("name");
        userDO.setForbidden(true);
        userDO.setLoginTime(LocalDateTime.now());
        UserDO userDO1=new UserDO();
        userDO1.setPasswd("codehome");
        userDO1.setAccount("codehome");
        userDO1.setName("name");
        userDO1.setForbidden(true);
        userDO1.setLoginTime(LocalDateTime.now());
        userMapper.insertBatch(Arrays.asList(userDO,userDO1));
    }
    //批量更新
    @Test
    public void testUpdateBatch(){
        UserDO userDO=new UserDO();
        userDO.setPasswd("codehome1");
        userDO.setAccount("codehome1");
        userDO.setName("name1");
        userDO.setId(1L);
        userDO.setForbidden(true);
        userDO.setLoginTime(LocalDateTime.now());
        UserDO userDO1=new UserDO();
        userDO.setId(2L);
        userDO1.setPasswd("codehome2");
        userDO1.setAccount("codehome2");
        userDO1.setName("name2");
        userDO1.setForbidden(true);
        userDO1.setLoginTime(LocalDateTime.now());
        userMapper.insertBatch(Arrays.asList(userDO,userDO1));
    }
    //批量删除
    @Test
    public void deleteBatch(){
        userMapper.deleteBatch(new Long[]{1L,2L});
    }
}
