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

    @Override
    public String getPrefix() {
        return null;
    }
}
