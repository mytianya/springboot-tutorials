package vip.codehome.springboot.tutorials.anno;

import org.springframework.context.annotation.Import;

/***
 *@author zyw
 *@createTime 2020/8/15 10:01
 *@description
 *@version 1.0
 * 1.在类上使用，写类的全路径导入IOC容器
 */
//@Import({vip.codehome.springboot.tutorials.anno.ImportDemoService.class})
//@Import({ImportTestServiceSelector.class})
@Import({ImportTestServiceBeanDefinitionRegistrar.class})
public class ImportTestService {

}
