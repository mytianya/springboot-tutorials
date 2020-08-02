package vip.codehome.springboot.tutorials.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class LoginDTO {
    @ApiModelProperty(value = "用户账号或者邮箱")
    String account;
    @ApiModelProperty(value = "用户密码")
    String passwd;
    @ApiModelProperty(value = "用户密码")
    String verifyCode;
}
