package vip.codehome.springboot.tutorials.transaction;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TransactionTest {
    @Autowired
    UserServiceTranasction userServiceTranasction;
    @Test
    public void testInsert(){
        Assert.assertEquals((long)userServiceTranasction.save(100,"codehome"),1);
    }
    @Test
    public void testInsert1(){
        Assert.assertEquals((long)userServiceTranasction.save1(100,"codehome"),1);
    }
    @Test
    public void testInsert2(){
        Assert.assertEquals((long)userServiceTranasction.save2(1000,"codehome"),1);
    }
}
