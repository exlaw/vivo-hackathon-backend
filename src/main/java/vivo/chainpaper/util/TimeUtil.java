package vivo.chainpaper.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TimeUtil {
    public static long getCurrentMonthStartTime() {
        Calendar calendar = Calendar.getInstance();// 获取当前日期
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTimeInMillis();
    }

    public static List<Long> getCurrentTwleveMonthStartTime() {
        LocalDateTime today = LocalDateTime.now();
        List<Long> times = new ArrayList<>();
        for (int i = 12; i > 0; i--) {
            LocalDateTime monthStart = today.minusMonths(i);
            times.add(Timestamp.valueOf(monthStart).getTime());
        }
        return times;
    }
}
