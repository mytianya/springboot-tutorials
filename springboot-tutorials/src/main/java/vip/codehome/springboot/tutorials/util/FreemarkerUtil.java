package vip.codehome.springboot.tutorials.util;


import freemarker.template.Template;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

/**
 * @author zyw
 * @mail dsyslove@163.com
 * @createtime 2021/5/19--15:26
 * @description
 **/
@Service
public class FreemarkerUtil {
  @Autowired
  private FreeMarkerConfigurer freeMarkerConfigurer;
  public String parse(String templateName, Map<String,Object> params){
    try {
      Template template= freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
      String text=FreeMarkerTemplateUtils.processTemplateIntoString(template,params);
      return text;
    } catch (Exception e) {
      return "";
    }
  }
}
