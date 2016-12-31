package jp.gr.java_conf.hungrywalker.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.stereotype.Component;

@Component
public class CalendarHelper
{
    private static final TimeZone TIME_ZONE = TimeZone.getTimeZone("Asia/Tokyo");

    /**
     * 現在日時を取得
     *
     * @return
     */
    public Date getCurrent()
    {
        Calendar calendar = Calendar.getInstance(TIME_ZONE);
        calendar.setTime(new Date());
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * Date オブジェクトから 日付以外の情報ををマスクする
     *
     * @param date
     * @return
     */
    public Date getDateOnly(Date date)
    {
        Calendar calendar = Calendar.getInstance(TIME_ZONE);
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * 指定日時にN日を追加
     *
     * @param date
     * @param hour
     * @return
     */
    public Date getAddDay(Date date, int hour)
    {
        Calendar calendar = Calendar.getInstance(TIME_ZONE);
        calendar.setTime(date);
        calendar.add(Calendar.DATE, hour);

        return calendar.getTime();
    }

    /**
     * yyyy-MM-dd 形式を Date 型に変換
     *
     * @param dateParameter
     * @return
     */
    public Date convertDate(String dateParameter)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        try
        {
            return df.parse(dateParameter);
        } catch (ParseException e)
        {
            return null;
        }
    }

    /**
     * Date を yyyy-MM-dd 形式で返却
     * 
     * @param date
     * @return
     */
    public String formatDate(Date date)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    /**
     * 日付の間隔を取得する
     *
     * @param beforeDate
     * @param afterDate
     * @return
     */
    public int getDateInterval(Date beforeDate, Date afterDate)
    {
        Date beforeDate_ = this.getDateOnly(beforeDate);
        Date afterDate_ = this.getDateOnly(afterDate);

        long diff = afterDate_.getTime() - beforeDate_.getTime();

        return (int) (diff / (24 * 60 * 60 * 1000));
    }
}
