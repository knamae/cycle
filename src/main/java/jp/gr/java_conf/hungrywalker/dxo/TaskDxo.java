package jp.gr.java_conf.hungrywalker.dxo;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.gr.java_conf.hungrywalker.entity.TaskEntity;
import jp.gr.java_conf.hungrywalker.entity.TaskStatus;
import jp.gr.java_conf.hungrywalker.helper.CalendarHelper;
import jp.gr.java_conf.hungrywalker.web.task.AbstractTaskForm;

@Component
public class TaskDxo
{
    @Autowired
    CalendarHelper calendarHelper;

    /**
     * フォーム情報からエンティティを作成
     *
     * @param taskForm
     * @return
     */
    public TaskEntity convertFormToEntity(AbstractTaskForm taskForm)
    {
        TaskEntity taskEntity = new TaskEntity();
        convertFormToEntity(taskForm, taskEntity);

        return taskEntity;
    }

    /**
     * フォーム情報からエンティティを作成
     *
     * @param taskForm
     * @param taskEntity
     */
    public void convertFormToEntity(AbstractTaskForm taskForm, TaskEntity taskEntity)
    {
        BeanUtils.copyProperties(taskForm, taskEntity);

        Integer settingInterval = taskForm.getSettingInterval();
        if (settingInterval != null)
        {
            taskEntity.setStatus(TaskStatus.ENABLED);
            taskEntity.setNextLimitDate(
                    this.calendarHelper.getAddDay(new Date(), settingInterval.intValue()));
        } else
        {
            taskEntity.setStatus(TaskStatus.DISABLED);
        }

        if (!StringUtils.isEmpty(taskForm.getNextLimitDate()))
        {
            taskEntity
                    .setNextLimitDate(this.calendarHelper.convertDate(taskForm.getNextLimitDate()));
        }

        taskEntity.setDoNotify(Boolean.valueOf(StringUtils.equals(taskForm.getDoNotify(), "on")));

        taskEntity.setExecutedCount(Integer.valueOf(0));
    }

    public void convertEntityToForm(TaskEntity taskEntity, AbstractTaskForm taskForm)
    {
        BeanUtils.copyProperties(taskEntity, taskForm);
        taskForm.setNextLimitDate(this.calendarHelper.formatDate(taskEntity.getNextLimitDate()));
    }
}
