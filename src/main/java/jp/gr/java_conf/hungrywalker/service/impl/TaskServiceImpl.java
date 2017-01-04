package jp.gr.java_conf.hungrywalker.service.impl;

import java.util.List;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jp.gr.java_conf.hungrywalker.entity.TaskEntity;
import jp.gr.java_conf.hungrywalker.entity.TaskRecordEntity;
import jp.gr.java_conf.hungrywalker.helper.CalendarHelper;
import jp.gr.java_conf.hungrywalker.repository.TaskRecordRepository;
import jp.gr.java_conf.hungrywalker.repository.TaskRepository;
import jp.gr.java_conf.hungrywalker.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService
{
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TaskRecordRepository taskRecordRepository;

    @Autowired
    CalendarHelper calendarHelper;

    @Override
    public void add(TaskEntity taskEntity)
    {
        this.taskRepository.save(taskEntity);
    }

    @Override
    public TaskEntity get(Long taskId)
    {
        return this.taskRepository.findOne(taskId);
    }

    @Override
    public List<TaskEntity> getListOrderByLimitDateAsc(Long memberId)
    {
        return this.taskRepository.findByMemberId(memberId,
                new Sort(Sort.Direction.ASC, "nextLimitDate"));
    }

    @Override
    public void addRecord(TaskRecordEntity taskRecordEntity)
    {
        Long taskId = taskRecordEntity.getTaskId();

        TaskEntity task = this.get(taskId);
        List<TaskRecordEntity> taskRecordList = this.getRecordList(taskId);

        // 前回からの経過時間
        if (taskRecordList.size() > 0)
        {
            TaskRecordEntity lastTaskRecord = taskRecordList.get(taskRecordList.size() - 1);
            int interval = this.calendarHelper.getDateInterval(lastTaskRecord.getExecutedDate(),
                    taskRecordEntity.getExecutedDate());
            if (interval < 0)
            {
                // TODO 入力エラー
            }
            taskRecordEntity.setIntervalDay(Integer.valueOf(interval));
        } else
        {
            taskRecordEntity.setIntervalDay(Integer.valueOf(0));
        }

        this.taskRecordRepository.save(taskRecordEntity);
        taskRecordList.add(taskRecordEntity);

        // 実施回数と平均間隔
        int count = 0;
        int totalInterval = 0;
        for (int i = 0; i < taskRecordList.size(); i++)
        {
            TaskRecordEntity taskRecord = taskRecordList.get(i);
            if (BooleanUtils.isNotTrue(taskRecord.getDeleted()))
            {
                if (BooleanUtils.isTrue(taskRecord.getExecuted()))
                {
                    count++;
                }
                if (i > 0)
                {
                    totalInterval += taskRecord.getIntervalDay().intValue();
                }
            }
        }

        int average = (totalInterval == 0) ? 0 : totalInterval / (count - 1);
        task.setExecutedCount(Integer.valueOf(count));
        task.setAverageInterval(Integer.valueOf(average));
        task.setNextLimitDate(this.calendarHelper.getAddDay(taskRecordEntity.getExecutedDate(),
                task.getSettingInterval().intValue()));
        this.add(task);
    }

    public TaskRecordEntity getLatestRecord(Long taskId)
    {
        return this.taskRecordRepository.findLatestByTaskId(taskId);
    }

    @Override
    public List<TaskRecordEntity> getRecordList(Long taskId)
    {
        return this.taskRecordRepository.findByTaskId(taskId);
    }

    public List<TaskEntity> getNotificationList()
    {
        return null;
    }
}
