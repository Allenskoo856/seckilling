package me.zonglun.seckilling.redis;

/**
 * @author : Administrator
 * @create 2018-04-29 13:27
 */
public abstract class BasePrefix implements KeyPrefix {

    // 过期时间
    private int expireSeconds;
    // 前缀
    private String prefix;

    /**
     * 构造函数，0代表永不过期
     * @param prefix
     */
    public BasePrefix(String prefix) {
        this(0, prefix);
    }

    /**
     *
     * @param expireSeconds 过期时间
     * @param prefix   模块前缀
     */
    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    /**
     * 默认0代表永久不过期
     * @return
     */
    @Override
    public int expireSeconds() {
        return expireSeconds;
    }

    /**
     * 得到preFIX的过程
     * @return
     */
    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className + ":" + prefix;
    }
}
