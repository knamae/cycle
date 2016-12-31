package jp.gr.java_conf.hungrywalker.web.task;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public abstract class AbstractTaskForm
{
    @NotBlank
    @Size(max = 100)
    private String title;

    private String description;

    private Integer settingInterval;

    private String nextLimitDate;

    private String doNotify;

    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Integer getSettingInterval()
    {
        return this.settingInterval;
    }

    public void setSettingInterval(Integer settingInterval)
    {
        this.settingInterval = settingInterval;
    }

    public String getNextLimitDate()
    {
        return this.nextLimitDate;
    }

    public void setNextLimitDate(String nextLimitDate)
    {
        this.nextLimitDate = nextLimitDate;
    }

    public String getDoNotify()
    {
        return this.doNotify;
    }

    public void setDoNotify(String doNotify)
    {
        this.doNotify = doNotify;
    }
}
