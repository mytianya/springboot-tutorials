package vip.codehome.springboot.tutorials.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RequestMappingController {
    @GetMapping
    public String handleAll(HttpServletRequest req){
        return "getMapping为空匹配："+req.getRequestURI();
    }
    @GetMapping("/")
    public String handleXie(HttpServletRequest req){
        return "getMapping /匹配："+req.getRequestURI();
    }
    @GetMapping("/**")
    public String handleStarStar(HttpServletRequest req){
        return "getMapping /**匹配："+req.getRequestURI();
    }
    @RequestMapping("{prefix}")
    public String handlePathV(HttpServletRequest req){
        return "{prefix}match:"+req.getRequestURI();
    }
    @RequestMapping("{prefix}/{slug}")
    public String handlePathVV(HttpServletRequest req){
        return "{prefix}match:"+req.getRequestURI();
    }
}
