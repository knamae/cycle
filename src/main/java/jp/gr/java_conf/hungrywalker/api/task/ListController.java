package jp.gr.java_conf.hungrywalker.api.task;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ListController
{
    @RequestMapping("/api/task/list")
    public Map<String, String> get()
    {
        Map<String, String> response = new HashMap<>();

        response.put("message", "hello!");

        return response;
    }
}
