package vip.codehome.springboot.tutorials.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 *@author zyw
 *@createTime 2020/3/16 13:29
 *@description
 *@version 1.0
 */
public class JsonUtil {


    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        mapper.setDateFormat(dateFormat);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 将json转换成
     *
     * @param json
     * @param valueType
     * @return
     * @author ZhiBing
     */
    public static <T> T toObject(String json, Class<T> valueType) {
        try {
            return (T) mapper.readValue(json, valueType);
        } catch (Exception e) {
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> toMap(String json) {
        try {
            return mapper.readValue(json, Map.class);
        } catch (Exception e) {
        }
        return new HashMap<String, Object>();
    }

    @SuppressWarnings("rawtypes")
    public static List toList(String json) {
        try {
            return mapper.readValue(json, List.class);
        } catch (Exception e) {
        }
        return new ArrayList();
    }


    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
        }
        return "{}";
    }






}
