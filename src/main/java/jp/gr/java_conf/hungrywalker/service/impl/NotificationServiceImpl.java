package jp.gr.java_conf.hungrywalker.service.impl;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jp.gr.java_conf.hungrywalker.service.TaskService;

@Service
public class NotificationServiceImpl
{
    TaskService taskService;

    @Scheduled(fixedDelay = 1000)
    public void execute()
    {
        // System.out.println("execute!!!");
    }
}
