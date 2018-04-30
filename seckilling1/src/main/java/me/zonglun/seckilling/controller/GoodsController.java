package me.zonglun.seckilling.controller;

import me.zonglun.seckilling.domain.SeckillUser;
import me.zonglun.seckilling.redis.RedisService;
import me.zonglun.seckilling.service.SeckillUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author : Administrator
 * @create 2018-04-30 21:07
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    SeckillUserService userService;

    @Autowired
    RedisService redisService;

    @Autowired
    GoodsController goodsController;

    @RequestMapping("/to_list")
    public String list(Model model, SeckillUser user) {
        model.addAttribute("user", user);
        // 查询商品列表
        return "goods_list";
    }
}
