package me.zonglun.seckilling.domain;

/**
 * @author : Administrator
 * @create 2018-04-28 21:15
 */
public class CodeMsg {
    private int code;
    private String msg;

    //通用异常
    public static CodeMsg SUCCESS = new CodeMsg(0, "success");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");

    //登录模块 5002XX
    public static CodeMsg SESSION_ERROR = new CodeMsg(500210, "Session不存在或者失效！");
    public static CodeMsg PASSWORD_EMPTY = new CodeMsg(500211, "密码不能为空！");
    public static CodeMsg MBOLE_EMPTY = new CodeMsg(500211, "手机号不能为空！");
    public static CodeMsg MBOLE_ERROR = new CodeMsg(500211, "手机号格式错误或者不存在该号码！");


    //商品模块 5003XX

    //订单模块 5004XX

    //秒杀模块 5005XX


    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}
