package vip.codehome.springboot.tutorials.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class UserInfoVO {
    @ApiModelProperty(value = "用户昵称")
    String nickname;
    @ApiModelProperty(value = "登录后生产的token")
    String token;
}
