package com.acmetelecom.billing;

import org.joda.time.DateTime;

/**
 *
 * @author dc408, ra808, je08, jm308
 */
class DaytimePeakPeriod {
    
    final short peakStart = 7;
    final short offPeakStart = 19;

    public boolean offPeak(DateTime time) {
        int hour = time.getHourOfDay();
        return hour < peakStart || hour >= offPeakStart;
    }

    public DateTime nextPeakChange(DateTime currentTime) {
        int delta = 0;
        int hour = peakStart;
        if (offPeak(currentTime)) {
            if(currentTime.getHourOfDay() > peakStart) {
                delta = 1;
            }
        } else {
            hour = offPeakStart;
        }
        DateTime next = new DateTime(currentTime).plus(delta);
        next = next.hourOfDay().setCopy(hour);
        next = next.minuteOfHour().setCopy(0);
        next = next.secondOfMinute().setCopy(0);
        return next;
    }
}
