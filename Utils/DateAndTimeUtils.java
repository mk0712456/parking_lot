package Utils;

import java.util.Date;

public class DateAndTimeUtils {
    public static int calculatehrs(Date startTime, Date endTime){
        long diff = endTime.getTime() - startTime.getTime();
        long diffInSecs = diff/1000;
        int hrs = (int) Math.ceil ((double) diffInSecs/360);
        return hrs;
    }
}
