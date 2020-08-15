package vip.codehome.springboot.tutorials.anno;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/***
 *@author zyw
 *@createTime 2020/8/15 10:03
 *@description
 *@version 1.0
 */
public class ImportTestServiceSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{"vip.codehome.springboot.tutorials.anno.ImportDemoService"};
    }
}
