package vip.codehome.springboot.tutorials.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vip.codehome.springboot.tutorials.common.R;

@Controller
public class MvcController {
    //testRequestParam?id=a&name=test
    //@RequestParam获取QueryPath 上面的参数
    @RequestMapping(value = "/testRequestParam",method = RequestMethod.GET)
    @ResponseBody
     public R testRequestParam(@RequestParam("id")String id,@RequestParam("name")String name){
      return R.ok(id+","+name);
    }
    //testRequestParamData
    //get form-data方式
    @RequestMapping(value = "/testRequestParamFormData",method = RequestMethod.GET)
    @ResponseBody
    public R testRequestParamFormData(@RequestParam("id")String id,@RequestParam("name")String name){
        return R.ok(id+","+name);
    }
}
