package com.dhcc.search.page.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author zhangqi
 * @date 2020/6/5
 */
@Controller
public class LoginController {

    @GetMapping("/hello")
    public String hello() {
        return "search";
    }

    @GetMapping("/top")
    public String top() {
        return "top";
    }
}
