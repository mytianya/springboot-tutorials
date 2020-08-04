package vip.codehome.springboot.tutorials.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@ApiModel
public class LoginDTO {
    @ApiModelProperty(value = "用户账号或者邮箱")
    @Size(min = 8,message = "账号长度大于8")
    String account;
    @ApiModelProperty(value = "用户密码")
    @NotBlank(message = "密码不能为空")
    String passwd;
    @ApiModelProperty(value = "用户密码")
    String verifyCode;
}
