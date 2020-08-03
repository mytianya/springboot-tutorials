package vip.codehome.springboot.tutorials.controller;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import vip.codehome.springboot.tutorials.common.R;
import vip.codehome.springboot.tutorials.dto.LoginDTO;
import vip.codehome.springboot.tutorials.vo.UserInfoVO;

@RestController
@Api(tags = "Swagger注解测试类")
public class SwaggerUserController {
    @ApiOperation(value = "这是一个echo接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "msg",value = "请求的msg参数",required = true,paramType = "query"),
            @ApiImplicitParam(name = "token",value = "请求的token",required = false,paramType ="header" )
    })
    @ApiResponses({
            @ApiResponse(code=200,message = "请求成功"),
            @ApiResponse(code=400,message="请求无权限")
    })
    @GetMapping("/echo")
    public R<String> echo(String msg,@RequestHeader(name = "token") String token){
        return R.ok("");
    }
    @ApiOperation(value = "登录接口说明")
    @PostMapping("/login")
    public R<UserInfoVO> login(@RequestBody LoginDTO loginDTO){
        UserInfoVO userInfoVO=new UserInfoVO();
        userInfoVO.setNickname("编程之家");
        userInfoVO.setToken("xxx");
        return R.ok(userInfoVO);
    }
}
