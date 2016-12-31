package jp.gr.java_conf.hungrywalker.entity;

/**
 * タスクの状態
 *
 * @author knamae
 */
public enum TaskStatus
{
    /** 有効 */
    ENABLED(1),

    /** 無効 */
    DISABLED(2),

    /** 削除 */
    DELETED(9);

    private final int id;

    private TaskStatus(final int id)
    {
        this.id = id;
    }

    public int getValue()
    {
        return this.id;
    }

    public static TaskStatus parse(int id)
    {
        for (TaskStatus status_ : TaskStatus.values())
        {
            if (status_.getValue() == id)
            {
                return status_;
            }
        }

        return null;
    }
}