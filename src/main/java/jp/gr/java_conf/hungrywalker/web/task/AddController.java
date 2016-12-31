package jp.gr.java_conf.hungrywalker.web.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jp.gr.java_conf.hungrywalker.dxo.TaskDxo;
import jp.gr.java_conf.hungrywalker.entity.TaskEntity;
import jp.gr.java_conf.hungrywalker.service.TaskService;
import jp.gr.java_conf.hungrywalker.web.HomeController;

@Controller
public class AddController
{
    public static final String PAGE = "/task/add";
    private static final String HTML = "task/add";

    @Autowired
    TaskService taskService;

    @Autowired
    TaskDxo taskDxo;

    @ModelAttribute
    public TaskAddForm setupForm()
    {
        return new TaskAddForm();
    }

    @GetMapping(AddController.PAGE)
    public String get(TaskAddForm taskForm, Model model)
    {
        model.addAttribute("taskForm", taskForm);

        return AddController.HTML;
    }

    @PostMapping(AddController.PAGE)
    public String post(@Validated TaskAddForm taskForm, BindingResult result, Model model)
    {
        if (result.hasErrors())
        {
            return AddController.HTML;
        }

        TaskEntity taskEntity = this.taskDxo.convertFormToEntity(taskForm);
        this.taskService.add(taskEntity);

        return "redirect:" + HomeController.PAGE;
    }
}
