package vip.codehome.springboot.tutorials.common;

import lombok.Data;

@Data
public class R<T> {
    private int code;
    private T data;
    private String msg;
    public R() {
    }
    public static <T> R<T> ok(T data) {
        return fill(data,ApiCommonCodeEnum.OK);
    }

    public static <T> R<T> failed(String msg) {
        return fill( null, ApiCommonCodeEnum.FAIL);
    }
    public static <T> R<T> failed(ApiCommonCodeEnum apiEnum) {
        return fill( null, apiEnum);
    }
    public static <T> R<T> fill(T data, ApiCommonCodeEnum apiEnum) {
        return fill(apiEnum.getCode(),data,apiEnum.getMsg());
    }
    public static <T> R<T> fill(int code,T data,String msg) {
        R R = new R();
        R.setCode(code);
        R.setData(data);
        R.setMsg(msg);
        return R;
    }
}
