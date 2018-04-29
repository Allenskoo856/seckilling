package me.zonglun.seckilling.redis;

/**
 * @author : Administrator
 * @create 2018-04-29 9:44
 */
public interface KeyPrefix {
    public int expireSeconds();

    public String getPrefix();
}
