package me.zonglun.seckilling.service;

import me.zonglun.seckilling.dao.SeckillUserDao;
import me.zonglun.seckilling.domain.CodeMsg;
import me.zonglun.seckilling.domain.Result;
import me.zonglun.seckilling.domain.SeckillUser;
import me.zonglun.seckilling.exception.GlobalException;
import me.zonglun.seckilling.redis.MiaoshaUserKey;
import me.zonglun.seckilling.redis.RedisService;
import me.zonglun.seckilling.utils.MD5Utils;
import me.zonglun.seckilling.utils.UUIDUtil;
import me.zonglun.seckilling.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author : Administrator
 * @create 2018-04-29 21:44
 */
@Service
public class SeckillUserService {

    public static final String COOKI_NAME_TOKEN = "token";

    @Autowired
    SeckillUserDao seckillUserDao;

    @Autowired
    RedisService redisService;

    /**
     * 根据用户的手机号码查找用户
     * @param id
     * @return
     */
    public SeckillUser getById(Long id) {
        return seckillUserDao.getById(id);
    }

    /**
     * 用户登录方法
     * @param response
     * @param loginVo
     * @return true or false
     */
    public Boolean login(HttpServletResponse response,  LoginVo loginVo) {
        if (loginVo == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        // 判断手机是否存在
        SeckillUser user = getById(Long.parseLong(mobile));
        if (user == null) {
            throw new GlobalException(CodeMsg.MBOLE_NOT_FOUND);
        }
        // 验证密码
        String daPass = user.getPassword();
        String slatDb = user.getSalt();
        String calcPass = MD5Utils.formPassToDBPass(formPass, slatDb);
        if (!calcPass.equals(daPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        // 生产cookie
        String token = UUIDUtil.uuid();
        addCookie(response, token, user);
        return true;
    }

    /**
     * 增加Cookie信息
     * @param response
     * @param token
     * @param user
     */
    private void addCookie(HttpServletResponse response, String token, SeckillUser user) {
        redisService.set(MiaoshaUserKey.token, token, user);
        Cookie cookie = new Cookie(COOKI_NAME_TOKEN, token);
        cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
