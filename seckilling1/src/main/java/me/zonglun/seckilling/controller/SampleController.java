package me.zonglun.seckilling.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : Administrator
 * @create 2018-04-19 5:02
 */
@Controller
@RequestMapping("/demo/")
public class SampleController {

    @RequestMapping("hello")
    public String thyeleaf(Model model) {
        model.addAttribute("name", "allen");
        return "hello";
    }
}
