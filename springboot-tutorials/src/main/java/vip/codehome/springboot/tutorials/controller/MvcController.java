package vip.codehome.springboot.tutorials.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vip.codehome.springboot.tutorials.common.R;
import vip.codehome.springboot.tutorials.entity.UserDO;

import java.util.Map;

@RestController
public class MvcController {

  @GetMapping("/user/{id}")
  @ResponseBody
  public R userInfo(@PathVariable("id") String id) {
    return R.ok(id);
  }

  @GetMapping("/useragent")
  @ResponseBody
  public R getHeader(@RequestHeader("User-Agent") String userAgent) {
    return R.ok(userAgent);
  }

  @GetMapping("/cookie")
  @ResponseBody
  public R getCookie(@CookieValue("token") String token) {
    return R.ok(token);
  }

  @RequestMapping("/reqparam")
  @ResponseBody
  public R requsetParam(@RequestParam Map<String, Object> params) {
    return R.ok(params);
  }

  @RequestMapping("/upload")
  @ResponseBody
  public R requsetParam(@RequestParam("files") MultipartFile file,
      @RequestParam Map<String, Object> params) {
    params.put("files", file.getOriginalFilename());
    return R.ok(params);
  }

  @RequestMapping("/json")
  @ResponseBody
  public R json(@RequestBody UserDO userDO) {
    return R.ok(userDO);
  }

  @RequestMapping(value = "/xml", consumes = "application/xml", produces = "application/xml", method = RequestMethod.POST)
  @ResponseBody
  public UserDO xml(@RequestBody UserDO userDO) {
    return userDO;
  }
}
