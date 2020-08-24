package vip.codehome.springboot.tutorials.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import vip.codehome.springboot.tutorials.entity.UserDO;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    int insert(UserDO userDO);
    List<UserDO> select(UserDO userDO);
    int update(UserDO userDO);
    int delete(UserDO userDO);
    int insertBatch(List<UserDO> list);
    int updateBatch(List<UserDO> list);
    int deleteBatch(Long[] array);
}
