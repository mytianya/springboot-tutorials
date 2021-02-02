package vip.codehome.springboot.tutorials.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import vip.codehome.springboot.tutorials.asynctask.UserServiceSyncTask;
import vip.codehome.springboot.tutorials.common.R;
import vip.codehome.springboot.tutorials.dto.LoginDTO;
import vip.codehome.springboot.tutorials.entity.UserDO;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

  @Autowired
  UserServiceSyncTask userServiceSyncTask;

  @GetMapping("/query")
  public String queryUser(String name) {
    return name;
  }

  @PostMapping("/add")
  public R addUser(@RequestBody UserDO userDO) {
    return R.ok(userDO);
  }

  @GetMapping("/cookie")
  public String testCookie(@CookieValue("token") String token) {
    return token;
  }

  @GetMapping("/sync")
  public R sync() throws ExecutionException, InterruptedException {
    log.info("进入到发送邮件方法....");
    userServiceSyncTask.sendEmail();
    Future<String> res = userServiceSyncTask.echo("aa");
    return R.ok(res.get());
  }

}
