package jp.gr.java_conf.hungrywalker.web.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jp.gr.java_conf.hungrywalker.dxo.TaskDxo;
import jp.gr.java_conf.hungrywalker.entity.TaskEntity;
import jp.gr.java_conf.hungrywalker.service.TaskService;

@Controller
public class ModifyController
{
    public static final String PAGE = "/task/{taskId}/modify";
    private static final String HTML = "task/modify";

    @Autowired
    TaskService taskService;

    @Autowired
    TaskDxo taskDxo;

    @ModelAttribute
    public TaskModifyForm setupForm()
    {
        return new TaskModifyForm();
    }

    @GetMapping("/task/{taskId}/modify")
    public String get(@PathVariable Long taskId, Model model)
    {
        TaskEntity taskEntity = this.taskService.get(taskId);

        TaskModifyForm taskForm = setupForm();
        this.taskDxo.convertEntityToForm(taskEntity, taskForm);
        taskForm.setTaskId(taskEntity.getId());
        model.addAttribute("taskForm", taskForm);

        return ModifyController.HTML;
    }

    @PostMapping(ModifyController.PAGE)
    public String post(@Validated TaskModifyForm taskForm, BindingResult result, Model model)
    {
        if (result.hasErrors())
        {
            return ModifyController.HTML;
        }

        TaskEntity taskEntity = this.taskService.get(taskForm.getTaskId());
        this.taskDxo.convertFormToEntity(taskForm, taskEntity);
        this.taskService.add(taskEntity);

        return "redirect:" + ViewController.PAGE;
    }
}
