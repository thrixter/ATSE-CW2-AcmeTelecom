package com.acmetelecom.billing;

import com.acmetelecom.billing.BillCalculator;
import com.acmetelecom.billing.DaytimePeakPeriod;
import com.acmetelecom.calling.Call;
import com.acmetelecom.customer.Tariff;
import org.joda.time.DateTime;
import org.joda.time.Seconds;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * User: javad
 * Date: 07/12/2011
 */
public class VariableRateBillCalculator implements BillCalculator {

    private final DaytimePeakPeriod peakPeriod;

    public VariableRateBillCalculator(DaytimePeakPeriod peakPeriod) {

        this.peakPeriod = peakPeriod;
    }

    public BigDecimal getCallCost(Call call, Tariff tariff) {
        DateTime currentTime = call.startTime();
        DateTime endTime = call.endTime();
        int totalOffPeakDuration = 0;
        int totalPeakDuration = 0;
        while (currentTime.compareTo((call.endTime())) < 0) {
            DateTime nextPeakChange = this.peakPeriod.nextPeakChange(currentTime);
            int peakDuration = Math.min(Seconds.secondsBetween(currentTime, endTime).getSeconds(),
                    Seconds.secondsBetween(currentTime, nextPeakChange).getSeconds());
            if (this.peakPeriod.offPeak(currentTime)) {
                totalOffPeakDuration += peakDuration;
            } else {
                totalPeakDuration += peakDuration;
            }
            currentTime = currentTime.plusSeconds(peakDuration);
        }
        BigDecimal offPeakCost = new BigDecimal(totalOffPeakDuration).multiply(tariff.offPeakRate());
        BigDecimal peakCost = new BigDecimal(totalPeakDuration).multiply(tariff.peakRate());
        return offPeakCost.add(peakCost).setScale(0, RoundingMode.HALF_UP);
    }
}
