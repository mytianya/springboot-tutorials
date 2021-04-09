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
  public void save(){
    String insertSql="insert into `tb_user`(`id`,`name`)values(?,?);";
    jdbcTemplate.update(insertSql,"333","codehome");
    Integer num=jdbcTemplate.queryForObject("select  count(*) from tb_user",Integer.class);
  }
  //编程式事务
  public void save1(){
    Integer num=transactionTemplate.<Integer>execute((TransactionStatus status)->{
      jdbcTemplate.execute(String.format("insert into tb_user(id,name) values(%s,%s)",
          UUID.randomUUID().toString(),"codhome"));
      return jdbcTemplate.queryForObject("select  count(*) from tb_user",Integer.class);
    });
  }
  //编程式事务
  public void save2(){
    TransactionStatus transactionStatus=transactionManager.getTransaction(new DefaultTransactionDefinition());
    try{
      jdbcTemplate.execute(String.format("insert into tb_user(id,name) values(%s,%s)",
          UUID.randomUUID().toString(),"codhome"));
        transactionManager.commit(transactionStatus);
      }catch (Exception e){
        transactionManager.rollback(transactionStatus);

      }

  }
}
