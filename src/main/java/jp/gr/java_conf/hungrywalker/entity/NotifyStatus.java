package jp.gr.java_conf.hungrywalker.entity;

/**
 * 通知処理の状態
 * 
 * @author knamae
 *
 */
public enum NotifyStatus
{
    /** 未実施 */
    UNEXECUTED(1),
    /** 実施中 */
    EXECUTING(2),
    /** 完了 */
    EXECUTED(3);

    private final int id;

    private NotifyStatus(final int id)
    {
        this.id = id;
    }

    public int getValue()
    {
        return this.id;
    }

    public static NotifyStatus parse(int id)
    {
        for (NotifyStatus status_ : NotifyStatus.values())
        {
            if (status_.getValue() == id)
            {
                return status_;
            }
        }

        return null;
    }
}