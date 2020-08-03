package vip.codehome.springboot.tutorials.config;

import com.google.common.base.Charsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import vip.codehome.springboot.tutorials.common.BusinessException;
import vip.codehome.springboot.tutorials.common.R;
import vip.codehome.springboot.tutorials.util.ExUtil;
import vip.codehome.springboot.tutorials.util.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
@Slf4j
public class ExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception ex) {
        if(ex instanceof BusinessException){
            R result= R.fill(((BusinessException) ex).getCode(),null,ex.getMessage());
            outputJSON(httpServletResponse, Charsets.UTF_8.toString(), JsonUtil.toJson(result));
            return null;
        }else {
            R result=R.failed(ex.getMessage());
            outputJSON(httpServletResponse, Charsets.UTF_8.toString(), JsonUtil.toJson(result));
            return null;
        }
    }
    private void outputJSON(HttpServletResponse response, String charset, String jsonStr) {
        PrintWriter out = null;
        try {
            if (response != null) {
                response.setCharacterEncoding(charset);
                response.setContentType("text/html;charset=" + charset);
                response.setHeader("Pragma", "No-cache");
                response.setHeader("Cache-Control", "no-cache");
                response.setDateHeader("Expires", 0);
                out = response.getWriter();
                out.print(jsonStr);
            }
        } catch (Exception e) {
            log.error(ExUtil.getSimpleMessage(e));
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
