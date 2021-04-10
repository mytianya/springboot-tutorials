package vip.codehome.springboot.tutorials.transaction;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author dsyslove@163.com
 * @createtime 2021/4/6--13:58
 * @description
 * https://studygolang.com/articles/19133
 * https://www.huaweicloud.com/articles/df26a826898e8f02999f3bd861db3d48.html
 **/
@Service
public class UserServiceTranasction {
  @Autowired
  JdbcTemplate jdbcTemplate;
  @Autowired
  TransactionTemplate transactionTemplate;
  @Autowired
  PlatformTransactionManager transactionManager;
  //声明式事务
  @Transactional(propagation = Propagation.REQUIRED,timeout = 3000,rollbackFor = Exception.class)
  public Integer save(Integer id,String name){
    String insertSql="insert into `tb_user`(`id`,`name`)values(?,?);";
    jdbcTemplate.update(insertSql,id,name);
    return jdbcTemplate.queryForObject("select  count(*) from tb_user",Integer.class);
  }
  //编程式事务
  public Integer save1(Integer id,String name){
    Integer num=transactionTemplate.<Integer>execute((TransactionStatus status)->{
      try{
        String insertSql="insert into `tb_user`(`id`,`name`)values(?,?);";
        jdbcTemplate.update(insertSql,id,name);
      }catch (Exception e){
        e.printStackTrace();
        //标记回滚
        status.setRollbackOnly();
      }
      return jdbcTemplate.queryForObject("select  count(*) from tb_user",Integer.class);
    });
    return num;
  }
  //编程式事务
  public Integer save2(Integer id,String name){
    TransactionStatus transactionStatus=transactionManager.getTransaction(new DefaultTransactionDefinition());
    try{
      String insertSql="insert into `tb_user`(`id`,`name`)values(?,?);";
      jdbcTemplate.update(insertSql,id,name);
        transactionManager.commit(transactionStatus);
      }catch (Exception e){
        e.printStackTrace();
        transactionManager.rollback(transactionStatus);
      }
    return jdbcTemplate.queryForObject("select  count(*) from tb_user",Integer.class);
  }
}
