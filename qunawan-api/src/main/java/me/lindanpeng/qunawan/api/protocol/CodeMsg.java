package me.lindanpeng.qunawan.api.protocol;

public enum CodeMsg {
    SUCCESS(0,"success"),
    SYSTEM_ERROR(10001,"服务器内部错误"),
    PARAMETER_ERROR(10002,"参数错误"),
    VERIFICATION_CODE_ERROR(10003,"验证码错误"),
    VERIFICATION_CODE_EXPIRED(10004,"验证码过期"),
    ID_OR_PASSWD_ERROR(10005,"帐号或密码错误"),
    USER_EXISTS(10006,"该用户已经存在");
    private int code;
    private String message;

     CodeMsg(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
