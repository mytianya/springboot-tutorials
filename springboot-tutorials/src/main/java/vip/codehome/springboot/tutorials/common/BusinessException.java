package vip.codehome.springboot.tutorials.common;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private int code = ApiCommonCodeEnum.FAIL.getCode();
    /**
     * 业务层异常 构造函数
     */
    public BusinessException() {
    }

    /**
     * 业务层异常 构造函数
     *
     * @param message 异常信息
     */
    public BusinessException(String message) {
        super(message);
    }

    /**
     * 业务层异常 构造函数
     *
     * @param cause Throwable
     */
    public BusinessException(Throwable cause) {
        super(cause);
    }

    /**
     * 业务层异常 构造函数
     *
     * @param message 异常信息
     * @param cause   Throwable
     */
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 业务层异常 构造函数
     *
     * @param code    异常代码
     * @param message 异常信息
     */
    public BusinessException(int code, String message) {
        super(message);
        this.code = (code == ApiCommonCodeEnum.OK.getCode() ? ApiCommonCodeEnum.FAIL.getCode() : code);
    }

    /**
     * 业务层异常 构造函数
     *
     * @param code    异常代码
     * @param message 异常信息
     * @param cause   Throwable
     */
    public BusinessException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = (code == ApiCommonCodeEnum.OK.getCode() ? ApiCommonCodeEnum.FAIL.getCode() : code);
    }

    public int getCode() {
        return code;
    }

}

