package me.zonglun.seckilling.controller;

import me.zonglun.seckilling.domain.CodeMsg;
import me.zonglun.seckilling.domain.Result;
import me.zonglun.seckilling.redis.RedisService;
import me.zonglun.seckilling.service.SeckillUserService;
import me.zonglun.seckilling.utils.ValidatorUtil;
import me.zonglun.seckilling.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : Administrator
 * @create 2018-04-29 20:41
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    RedisService redisService;

    @Autowired
    SeckillUserService seckillUserService;

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * 路由---到登录界面
     * @return
     */
    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }

    /**
     * 校验登录的用户名和密码是否正确
     * @param loginVo
     * @return
     */
    @RequestMapping("/do_login")
    @ResponseBody
    public Result<CodeMsg> toLogin(@Validated LoginVo loginVo) {
        logger.info(loginVo.toString());
        // 参数校验
        String passInput = loginVo.getPassword();
        String moblie = loginVo.getMobile();
        if (StringUtils.isEmpty(passInput)) {
            return Result.error(CodeMsg.PASSWORD_EMPTY);
        }
        if (StringUtils.isEmpty(moblie)) {
            return Result.error(CodeMsg.MBOLE_EMPTY);
        }
        if (!ValidatorUtil.isMobile(moblie)) {
            return Result.error(CodeMsg.MBOLE_ERROR);
        }
        // 登录过程
        CodeMsg codeMsg =  seckillUserService.login(loginVo);
        if (codeMsg.getCode() == 0) {
            return Result.success(CodeMsg.SUCCESS);
        } else {
            return Result.error(codeMsg);
        }
    }

}
