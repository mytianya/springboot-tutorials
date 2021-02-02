package vip.codehome.springboot.tutorials.es;

import java.util.Date;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author dsyslove@163.com
 * @createtime 2021/2/2--14:06
 * @description
 **/
@Data
@Document(indexName = "msglog")
public class LogDO {
  @Id
  private  String id;
  private  String msgSeqn;
  private String msgReqn;
  private  String msgRaw;
  private  String msgStatus;
  private  String msgFrom;
  private  String msgTo;
  private  String msgStyp;
  private Date logTime;
  private  String logSystem;
  private  String logModule;
  private  String remark;
  private String msgSndr;
  private String msgRcvr;
  private Date msgDdtm;
}
