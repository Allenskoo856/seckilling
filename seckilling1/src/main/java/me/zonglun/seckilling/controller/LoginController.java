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

import javax.servlet.http.HttpServletResponse;

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
     *
     * @return
     */
    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }

    /**
     * 校验登录的用户名和密码是否正确
     *
     * @param loginVo
     * @return
     */
    @RequestMapping("/do_login")
    @ResponseBody
    public Result<CodeMsg> toLogin(HttpServletResponse response, @Validated LoginVo loginVo) {
        logger.info(loginVo.toString());
        // 登录过程
        Boolean flag = seckillUserService.login(response, loginVo);
        if (flag) {
            return Result.success(CodeMsg.SUCCESS);
        } else {
            return Result.error(CodeMsg.PASSWORD_ERROR);
        }
    }

}
