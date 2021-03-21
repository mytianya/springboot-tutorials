package vip.codehome.springboot.tutorials.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import vip.codehome.springboot.tutorials.entity.UserDO;

public interface TkUserMapper extends Mapper<UserDO>, MySqlMapper<UserDO> {
}
