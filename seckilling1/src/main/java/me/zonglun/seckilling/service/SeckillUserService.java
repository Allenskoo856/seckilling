package me.zonglun.seckilling.service;

import me.zonglun.seckilling.dao.SeckillUserDao;
import me.zonglun.seckilling.domain.CodeMsg;
import me.zonglun.seckilling.domain.Result;
import me.zonglun.seckilling.domain.SeckillUser;
import me.zonglun.seckilling.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : Administrator
 * @create 2018-04-29 21:44
 */
@Service
public class SeckillUserService {

    @Autowired
    SeckillUserDao seckillUserDao;

    /**
     * 根据用户的手机号码查找用户
     * @param id
     * @return
     */
    public SeckillUser getById(Long id) {
        return seckillUserDao.getById(id);
    }

    public CodeMsg login(LoginVo loginVo) {
        if (loginVo == null) {
            return CodeMsg.SERVER_ERROR;
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        // 判断手机是否存在
        getById(Long.parseLong(mobile));
        // todo 第二天
        return null;
    }
}
