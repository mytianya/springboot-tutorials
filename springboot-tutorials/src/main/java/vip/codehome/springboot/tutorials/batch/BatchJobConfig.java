package vip.codehome.springboot.tutorials.batch;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import vip.codehome.springboot.tutorials.entity.UserDO;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * https://mrbird.cc/Spring-Batch%E5%85%A5%E9%97%A8.html
 * https://segmentfault.com/a/1190000024439130
 */
@Configuration
public class BatchJobConfig {

    @Bean
    public JdbcPagingItemReader<UserDO> jdbcPagingItemTbUserReader(DataSource dataSource) {
        JdbcPagingItemReader<UserDO> reader = new JdbcPagingItemReader<>();
        reader.setDataSource(dataSource);
        reader.setFetchSize(100);

        reader.setQueryProvider(new MySqlPagingQueryProvider() {{
            setSelectClause("select id,account,age,name,login_time,forbidden");
            setFromClause("from tb_user");
            setWhereClause("age>:age");
            setSortKeys(new HashMap<String, Order>() {{
                put("person_id", Order.ASCENDING);
            }});
        }});
        reader.setParameterValues(new HashMap<String, Object>() {{
            put("age", 20);
        }});
        reader.setRowMapper(new BeanPropertyRowMapper<>(UserDO.class));
        return reader;
    }

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job multiStepJob() {
        return jobBuilderFactory.get("multiStepJob2")
                .start(step1())
                .on(ExitStatus.COMPLETED.getExitCode()).to(step2())
                .from(step2())
                .on(ExitStatus.COMPLETED.getExitCode()).to(step3())
                .from(step3()).end()
                .build();
    }

    private Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("执行步骤一操作。。。");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    private Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("执行步骤二操作。。。");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    private Step step3() {
        return stepBuilderFactory.get("step3")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("执行步骤三操作。。。");
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
