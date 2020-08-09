package vip.codehome.springboot.tutorials.controller;

import org.springframework.web.bind.annotation.*;
import vip.codehome.springboot.tutorials.common.R;
import vip.codehome.springboot.tutorials.dto.LoginDTO;
import vip.codehome.springboot.tutorials.entity.UserDO;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/query")
    public String queryUser(String name){
        return name;
    }
    @PostMapping("/add")
    public R addUser(@RequestBody UserDO userDO){
        return R.ok(userDO);
    }
    @GetMapping("/cookie")
    public String testCookie(@CookieValue("token") String token){
        return token;
    }
}
