package com.acmetelecom.billing;

import org.joda.time.DateTime;

/**
 *
 * @author dc408, ra808, je08, jm308
 */
public class DaytimePeakPeriod {
    
    final short peakStart = 7;
    final short offPeakStart = 19;

    public boolean isOffPeak(DateTime time) {
        return time.getHourOfDay() < peakStart
                || time.getHourOfDay() >= offPeakStart;
    }

    public boolean isPeak(DateTime time) {
        return !this.isOffPeak(time);
    }

    public DateTime nextPeakChange(DateTime currentTime) {
        DateTime next = new DateTime(currentTime);
        int hour;

        if (isOffPeak(currentTime)) {
            hour = peakStart;
            if (currentTime.getHourOfDay() >= offPeakStart) {
                next = currentTime.plusDays(1);
            }
        } else {
            hour = offPeakStart;
        }

        return next.withTime(hour, 0, 0, 0);
    }
}
