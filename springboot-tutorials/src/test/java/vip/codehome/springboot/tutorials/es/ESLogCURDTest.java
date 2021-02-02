package vip.codehome.springboot.tutorials.es;

import java.util.Date;
import java.util.UUID;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author dsyslove@163.com
 * @createtime 2021/2/2--14:26
 * @description
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class ESLogCURDTest {
  @Autowired
  private ElasticsearchRestTemplate template;
  @Autowired
  LogRepository logRepository;
  @Test
  public void createIndex(){
    LogDO logDO=new LogDO();
    logDO.setId(UUID.randomUUID().toString());
    logDO.setMsgStatus("success");
    logDO.setLogTime(new Date());
    logRepository.save(logDO);
  }
  @Test
  public void query(){
    IndexQuery indexQuery= new IndexQueryBuilder().withId("03946074-d9c2-45c5-a6ef-cb7153a13a14").build();
    template.index(indexQuery, IndexCoordinates.of("msglog"));
  }
}
