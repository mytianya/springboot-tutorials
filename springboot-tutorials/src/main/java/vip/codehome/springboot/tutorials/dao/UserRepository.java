package vip.codehome.springboot.tutorials.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vip.codehome.springboot.tutorials.entity.UserDO;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<UserDO,Long> {
    @Query("from tb_user u where u.userName like :userName")
    Page<UserDO> findUserDOByUserName(@Param("userName")String userName, Pageable pageable);
    Page<UserDO> findAll(Pageable pageable);
    List<UserDO> findUserDOByAccountAndAgeAndUserNameLike(String account,int age,String userName);
}
