package vip.codehome.springboot.tutorials.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.codehome.springboot.tutorials.common.R;
import vip.codehome.springboot.tutorials.config.UserProperties;
import vip.codehome.springboot.tutorials.entity.UserDO;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
public class PropController {
    @Value("${version}")
    float version;
    @Value("${author}")
    String author;
    @Value("${flag:true}")
    boolean flag;
    @Value("#{'${random}'.split(',')}")
    int[] randoms;
    @Autowired
    UserProperties userProperties;
    @GetMapping("/simple")
    public R propsSimple(){
        return R.ok(version);
    }
    @GetMapping("/object")
    public R propsObject(){
        System.out.println(userProperties.toString());
        return R.ok(userProperties.toString());
    }
    @GetMapping("/array")
    public R propsArray(){
        return R.ok(Arrays.toString(randoms));
    }
}
