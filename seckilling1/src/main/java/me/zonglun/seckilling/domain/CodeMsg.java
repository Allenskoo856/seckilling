package me.zonglun.seckilling.domain;

/**
 * @author : Administrator
 * @create 2018-04-28 21:15
 */
public class CodeMsg {
    private int code;
    private String msg;

    /**
     * 通用异常
     */
    public static CodeMsg SUCCESS = new CodeMsg(0, "success");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
    public static CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常:%s");

    /**
     * 登录模块 5002XX
     */
    public static CodeMsg SESSION_ERROR = new CodeMsg(500210, "Session不存在或者失效！");
    public static CodeMsg PASSWORD_EMPTY = new CodeMsg(500211, "密码不能为空！");
    public static CodeMsg MBOLE_EMPTY = new CodeMsg(500212, "手机号不能为空！");
    public static CodeMsg MBOLE_ERROR = new CodeMsg(500213, "手机号格式错误,请检查手机号码是否正确！");
    public static CodeMsg MBOLE_NOT_FOUND = new CodeMsg(500214, "该手机号尚未注册!");
    public static CodeMsg PASSWORD_ERROR = new CodeMsg(500215, "输入的密码错误，请检查输入密码");

    //商品模块 5003XX

    //订单模块 5004XX
    public static CodeMsg ORDER_NOT_EXIST = new CodeMsg(500400, "订单不存在");

    //秒杀模块 5005XX
    public static CodeMsg MIAO_SHA_OVER = new CodeMsg(500500, "商品已经秒杀完毕");
    public static CodeMsg REPEATE_MIAOSHA = new CodeMsg(500501, "不能重复秒杀");

    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
