package vip.codehome.springboot.tutorials.common;

public enum  ApiCommonCodeEnum {
    FAIL(1,"调用出错"),
    OK(0,"调用成功");
    int code;
    String msg;
    ApiCommonCodeEnum(int code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public int getCode() {
        return code;
    }

    private void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    private void setMsg(String msg) {
        this.msg = msg;
    }
}
