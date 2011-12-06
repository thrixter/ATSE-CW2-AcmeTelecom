package com.acmetelecom;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author dc408, ra808, je08, jm308
 */
class DaytimePeakPeriod {
    /**
     *
     * @param time
     * @return
     */
    public boolean offPeak(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return hour < 7 || hour >= 19;
    }
}
