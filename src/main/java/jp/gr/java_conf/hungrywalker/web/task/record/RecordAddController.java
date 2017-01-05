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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.gr.java_conf.hungrywalker.dxo.TaskRecordDxo;
import jp.gr.java_conf.hungrywalker.entity.TaskEntity;
import jp.gr.java_conf.hungrywalker.entity.TaskRecordEntity;
import jp.gr.java_conf.hungrywalker.helper.CalendarHelper;
import jp.gr.java_conf.hungrywalker.helper.CipherHelper;
import jp.gr.java_conf.hungrywalker.service.TaskService;
import jp.gr.java_conf.hungrywalker.web.task.ViewController;

@Controller
public class RecordAddController
{
    public static final String PAGE = "/task/{taskId}/recordAdd";
    private static final String HTML = "task/record/add";

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRecordDxo taskRecordDxo;

    @Autowired
    private CalendarHelper calendarHelper;

    @Autowired
    private CipherHelper cipherHelper;

    @ModelAttribute
    public TaskRecordForm setupForm()
    {
        return new TaskRecordForm();
    }

    @GetMapping(RecordAddController.PAGE)
    public String get(@PathVariable("taskId") String taskId, Model model,
            TaskRecordForm taskRecordForm)
    {
        String _taskId = this.cipherHelper.decypt(taskId);
        Long id = Long.valueOf(_taskId);

        TaskEntity taskEntity = this.taskService.get(id);
        model.addAttribute("task", taskEntity);

        taskRecordForm
                .setExecutedDate(this.calendarHelper.formatDate(this.calendarHelper.getCurrent()));

        return RecordAddController.HTML;
    }

    @PostMapping(RecordAddController.PAGE)
    public String post(@Validated TaskRecordForm taskRecordForm, BindingResult result, Model model,
            RedirectAttributes attributes)
    {
        if (result.hasErrors())
        {
            return RecordAddController.HTML;
        }

        TaskRecordEntity taskRecordEntity = this.taskRecordDxo.convertFormToEntity(taskRecordForm);
        this.taskService.addRecord(taskRecordEntity);

        attributes.addAttribute("taskId", taskRecordForm.getTaskId());
        return "redirect:" + ViewController.PAGE;
    }
}
