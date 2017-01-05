package jp.gr.java_conf.hungrywalker.web.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jp.gr.java_conf.hungrywalker.entity.TaskEntity;
import jp.gr.java_conf.hungrywalker.entity.TaskRecordEntity;
import jp.gr.java_conf.hungrywalker.helper.CipherHelper;
import jp.gr.java_conf.hungrywalker.service.TaskService;

@Controller
public class ViewController
{
    public static final String PAGE = "/task/{taskId}";
    private static final String HTML = "task/view";

    @Autowired
    private TaskService taskService;

    @Autowired
    private CipherHelper cipherHelper;

    @GetMapping(ViewController.PAGE)
    public String get(@PathVariable String taskId, Model model)
    {
        String _taskId = this.cipherHelper.decypt(taskId);
        Long id = Long.valueOf(_taskId);

        TaskEntity taskEntity = this.taskService.get(id);
        model.addAttribute("task", taskEntity);

        List<TaskRecordEntity> taskRecordEntityList = this.taskService.getRecordList(id);
        model.addAttribute("taskRecordList", taskRecordEntityList);

        return ViewController.HTML;
    }
}
