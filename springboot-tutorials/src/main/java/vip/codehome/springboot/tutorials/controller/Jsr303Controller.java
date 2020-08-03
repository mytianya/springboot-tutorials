package vip.codehome.springboot.tutorials.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.codehome.springboot.tutorials.dto.LoginDTO;
import vip.codehome.springboot.tutorials.util.ExUtil;

import javax.validation.Valid;

@RestController
public class Jsr303Controller {
    @PostMapping("/logon")
    public String logon(@Valid LoginDTO loginDTO, BindingResult result){
        check(result);
        return "ok";
    }
    public static void check(BindingResult result){
        StringBuffer sb=new StringBuffer();
        if(result.hasErrors()){
            for (ObjectError error : result.getAllErrors()) {
                sb.append(error.getDefaultMessage());
            }
            ExUtil.throwBusException(sb.toString());
        }
    }
}
