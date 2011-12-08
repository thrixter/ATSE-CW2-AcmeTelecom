package com.acmetelecom.billing;

import org.joda.time.DateTime;

/**
 *
 * @author dc408, ra808, je08, jm308
 */
public class DaytimePeakPeriod {
    
    final short peakStart = 7;
    final short offPeakStart = 19;

    public boolean offPeak(DateTime time) {
        int hour = time.getHourOfDay();
        return hour < peakStart || hour >= offPeakStart;
    }

    public DateTime nextPeakChange(DateTime currentTime) {
        DateTime next = new DateTime(currentTime);
        int hour;

        if (offPeak(currentTime)) {
            hour = peakStart;
            if (currentTime.getHourOfDay() > offPeakStart) {
                next = currentTime.plusDays(1);
            }
        } else {
            hour = offPeakStart;
        }
        return next.withTime(hour, 0, 0, 0);
    }
}
