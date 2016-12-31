package jp.gr.java_conf.hungrywalker.web;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jp.gr.java_conf.hungrywalker.entity.MemberEntity;
import jp.gr.java_conf.hungrywalker.entity.TaskEntity;
import jp.gr.java_conf.hungrywalker.service.TaskService;

@Controller
public class HomeController
{
    public static final String PAGE = "/";
    private static final String HTML = "home";

    @Autowired
    TaskService taskService;

    @GetMapping(path = PAGE)
    public String home(Model model, Principal principal)
    {
        Authentication authentication = (Authentication) principal;
        MemberEntity memberEntity = (MemberEntity) authentication.getPrincipal();

        List<TaskEntity> taskList = this.taskService.getList(memberEntity.getId());
        model.addAttribute("taskList", taskList);

        return HomeController.HTML;
    }
}
