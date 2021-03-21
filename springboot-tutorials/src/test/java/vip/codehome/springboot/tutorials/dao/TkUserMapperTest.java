package vip.codehome.springboot.tutorials.dao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vip.codehome.springboot.tutorials.entity.UserDO;
import vip.codehome.springboot.tutorials.mapper.TkUserMapper;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TkUserMapperTest {
    @Autowired
    TkUserMapper tkUserMapper;
    @Test
    public void add(){
        UserDO userDO=new UserDO();
        userDO.setId(1111L);
        userDO.setAge(11);
        userDO.setName("codehome");
        tkUserMapper.insert(userDO);
    }
    @Test
    public void pageTest(){
        PageHelper.startPage(0,10);
        List<UserDO> userDOList=tkUserMapper.selectAll();
        PageInfo<UserDO> userDOPageInfo=new PageInfo<>(userDOList);
        System.out.println(userDOPageInfo.getTotal());
    }
    @Test
    public void updateTest(){
        UserDO userDO=new UserDO();
        userDO.setId(1111L);
        userDO.setAge(22);
        userDO.setName("codehome");
        tkUserMapper.updateByPrimaryKey(userDO);
    }
}
