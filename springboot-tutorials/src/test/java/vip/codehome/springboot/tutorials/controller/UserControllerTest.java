package vip.codehome.springboot.tutorials.controller;

import net.minidev.json.JSONUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import vip.codehome.springboot.tutorials.dto.LoginDTO;
import vip.codehome.springboot.tutorials.entity.UserDO;
import vip.codehome.springboot.tutorials.util.JsonUtil;

import javax.servlet.http.Cookie;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    MockMvc mockMvc;
    UserDO userDO;
    MultiValueMap<String,String> params;
    @BeforeEach
    public void setUp()throws Exception{
        userDO=new UserDO();
        userDO.setPasswd("123456");
        params=new LinkedMultiValueMap<>();
        params.add("name","codehome");
    }
    @Test
   public  void queryUser() throws Exception {
      String result= mockMvc.perform(MockMvcRequestBuilders.get("/user/query")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .params(params)
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
        .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse()
                .getContentAsString();
        Assert.assertEquals("调用成功","codehome",result);
    }

    @Test
    void addUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/add")
            .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(userDO))
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.passwd").value("123456"));
    }
    @Test
    void testCookie()throws Exception{
       String token= mockMvc.perform(MockMvcRequestBuilders.get("/user/cookie")
        .cookie(new Cookie("token","123456")))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse()
                .getContentAsString();
       Assert.assertEquals("token从cookie中获取成功","123456",token);
    }
}