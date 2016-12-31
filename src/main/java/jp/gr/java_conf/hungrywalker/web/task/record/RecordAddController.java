package jp.gr.java_conf.hungrywalker.web.task.record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jp.gr.java_conf.hungrywalker.dxo.TaskRecordDxo;
import jp.gr.java_conf.hungrywalker.entity.TaskEntity;
import jp.gr.java_conf.hungrywalker.entity.TaskRecordEntity;
import jp.gr.java_conf.hungrywalker.helper.CalendarHelper;
import jp.gr.java_conf.hungrywalker.service.TaskService;
import jp.gr.java_conf.hungrywalker.web.task.ViewController;

@Controller
public class RecordAddController
{
    public static final String PAGE = "/task/{taskId}/recordAdd";
    private static final String HTML = "task/record/add";

    @Autowired
    TaskService taskService;

    @Autowired
    TaskRecordDxo taskRecordDxo;

    @Autowired
    CalendarHelper calendarHelper;

    @ModelAttribute
    public TaskRecordForm setupForm()
    {
        return new TaskRecordForm();
    }

    @GetMapping("/task/{taskId}/recordAdd")
    public String get(@PathVariable("taskId") Long taskId, Model model,
            TaskRecordForm taskRecordForm)
    {
        TaskEntity taskEntity = this.taskService.get(taskId);
        model.addAttribute("task", taskEntity);

        taskRecordForm
                .setExecutedDate(this.calendarHelper.formatDate(this.calendarHelper.getCurrent()));

        return RecordAddController.HTML;
    }

    @PostMapping(value = PAGE)
    public String post(@PathVariable("taskId") Long taskId,
            @Validated TaskRecordForm taskRecordForm, BindingResult result, Model model)
    {
        if (result.hasErrors())
        {
            return RecordAddController.HTML;
        }

        TaskRecordEntity taskRecordEntity = this.taskRecordDxo.convertFormToEntity(taskRecordForm);
        this.taskService.addRecord(taskRecordEntity);

        return "redirect:" + ViewController.PAGE;
    }
}
