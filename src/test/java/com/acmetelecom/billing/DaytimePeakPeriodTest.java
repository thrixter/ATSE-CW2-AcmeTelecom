package com.acmetelecom.billing;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * User: javad
 * Date: 07/12/2011
 */
public class DaytimePeakPeriodTest {

    DaytimePeakPeriod peakPeriod = new DaytimePeakPeriod();
    DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");

    DateTime aPeakTime = DateTime.parse("2011-11-29 14:00", format);
    DateTime anEveningOffPeakTime = DateTime.parse("2011-11-29 23:00", format);
    DateTime aMorningOffPeakTime = DateTime.parse("2011-11-29 05:00", format);

    @Test
    public void testNextPeakChangeFromPeakTime() {
        DateTime expectedPeakStart = DateTime.parse("2011-11-29 19:00", format);
        DateTime nextPeakChange = peakPeriod.nextPeakChange(aPeakTime);

        assertThat(nextPeakChange, is(equalTo(expectedPeakStart)));
    }

    @Test
    public void testNextPeakChangeFromMorningOffPeakTime() {
        DateTime expectedOffPeakStart = DateTime.parse("2011-11-29 07:00", format);
        DateTime nextPeakChange = peakPeriod.nextPeakChange(aMorningOffPeakTime);

        assertThat(nextPeakChange, is(equalTo(expectedOffPeakStart)));
    }

    @Test
    public void testNextPeakChangeFromEveningOffPeakTime() {
        DateTime expectedOffPeakStart = DateTime.parse("2011-11-30 07:00", format);
        DateTime nextPeakChange = peakPeriod.nextPeakChange(anEveningOffPeakTime);

        assertThat(nextPeakChange, is(equalTo(expectedOffPeakStart)));
    }

}
