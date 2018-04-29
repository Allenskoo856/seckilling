package me.zonglun.seckilling.controller;

import me.zonglun.seckilling.domain.CodeMsg;
import me.zonglun.seckilling.domain.Result;
import me.zonglun.seckilling.domain.User;
import me.zonglun.seckilling.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : Administrator
 * @create 2018-04-19 5:02
 */
@Controller
@RequestMapping("/demo/")
public class SampleController {

    @Autowired
    private UserService userService;

    @RequestMapping("hello")
    public String thyeleaf(Model model) {
        model.addAttribute("name", "allen");
        return "hello";
    }


    @RequestMapping("/db/get")
    @ResponseBody
    public Result<User> dbGet() {
        User user = userService.getById(1);
        return Result.success(user);
    }

    @RequestMapping("/db/tx")
    @ResponseBody
    public Result<Boolean> dbTx() {
        userService.tx();
        return Result.success(true);
    }
}
