package me.zonglun.seckilling.redis;

/**
 * @author : Administrator
 * @create 2018-04-29 16:48
 */
public class UserKey extends BasePrefix {

    /**
     * 用0作为所有的私有缓存系统
     * @param prefix
     */
    private UserKey(String prefix) {
        super(prefix);
    }

    public static UserKey getById = new UserKey("id");
    public static UserKey getByName = new UserKey("name");
}
