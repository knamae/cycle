package jp.gr.java_conf.hungrywalker.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController
{
    public static final String PAGE = "/login";
    private static final String HTML = "login";

    @GetMapping(LoginController.PAGE)
    public String get()
    {
        return LoginController.HTML;
    }

    @PostMapping(LoginController.PAGE)
    public String post()
    {
        return "redirect:" + HomeController.PAGE;
    }
}
