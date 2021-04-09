package vip.codehome.springboot.tutorials.transaction;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * @author dsyslove@163.com
 * @createtime 2021/4/6--18:24
 * @description
 **/
@Aspect
@Component //事务依然生效
@Configuration("__tx_advice_interceptor__")
@ConditionalOnBean(DataSource.class)
public class TxAdviceInterceptor {

  private Logger logger = LoggerFactory.getLogger(this.getClass());
  @Value("${tx.timeout:5}")
  private int TX_METHOD_TIMEOUT = 5;
  private String AOP_POINTCUT_EXPRESSION = "execution(* codehome.vip.*.service.*.*(..)) ";
  @Autowired
  private PlatformTransactionManager transactionManager;

  @Bean
  public TransactionInterceptor txAdvice() {
    NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
    /*只读事务，不做更新操作*/
    RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
    readOnlyTx.setReadOnly(true);
    readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);
    /*当前存在事务就使用当前事务，当前不存在事务就创建一个新的事务*/
    RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute();
    requiredTx.setRollbackRules(
        Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
    requiredTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    requiredTx.setTimeout(TX_METHOD_TIMEOUT);
    Map<String, TransactionAttribute> txMap = new HashMap<>();
    txMap.put("save*", requiredTx);
    txMap.put("update*", requiredTx);
    txMap.put("remove*", requiredTx);
    txMap.put("*", readOnlyTx);
    source.setNameMap(txMap);
    TransactionInterceptor txAdvice = new TransactionInterceptor(transactionManager, source);
    if (logger.isInfoEnabled()) {
      logger.info("事务管理器启动成功！");
    }
    return txAdvice;
  }

  @Bean
  public Advisor txAdviceAdvisor() {
    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
    return new DefaultPointcutAdvisor(pointcut, txAdvice());
  }
}
