package vip.codehome.springboot.tutorials.anno;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/***
 *@author zyw
 *@createTime 2020/8/15 10:07
 *@description
 *@version 1.0
 */

public class ImportTestServiceBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        RootBeanDefinition testServiceBeanDefinition=new RootBeanDefinition(TestService.class);
        registry.registerBeanDefinition("testService",testServiceBeanDefinition);
    }
}
