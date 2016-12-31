package jp.gr.java_conf.hungrywalker.web.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jp.gr.java_conf.hungrywalker.entity.TaskEntity;
import jp.gr.java_conf.hungrywalker.entity.TaskRecordEntity;
import jp.gr.java_conf.hungrywalker.service.TaskService;

@Controller
public class ViewController
{
    public static final String PAGE = "/task/{taskId}";
    private static final String HTML = "task/view";

    @Autowired
    TaskService taskService;

    @GetMapping("/task/{taskId}")
    public String get(@PathVariable Long taskId, Model model)
    {
        TaskEntity taskEntity = this.taskService.get(taskId);
        model.addAttribute("task", taskEntity);

        List<TaskRecordEntity> taskRecordEntityList = this.taskService.getRecordList(taskId);
        model.addAttribute("taskRecordList", taskRecordEntityList);

        return ViewController.HTML;
    }
}
