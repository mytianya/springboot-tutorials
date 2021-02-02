package vip.codehome.springboot.tutorials.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vip.codehome.springboot.tutorials.entity.UserDO;

import java.util.Arrays;

@Controller
public class ContentController {

  @RequestMapping("/freemark/{demo}")
  public String demo(@PathVariable("demo") String demo, Model model) {
    model.addAttribute("userList", Arrays.asList(new UserDO()));
    model.addAttribute("flag", false);
    model.addAttribute("copyright", "编程之家：www.codehome.vip");
    return demo;
  }
}

