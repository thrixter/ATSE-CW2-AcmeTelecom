package com.acmetelecom.billing;

import com.acmetelecom.calling.Call;
import com.acmetelecom.customer.Tariff;
import org.joda.time.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * User: javad
 * Date: 07/12/2011
 */
public class VariableRateBillCalculator implements BillCalculator {

    private final DaytimePeakPeriod period;

    public VariableRateBillCalculator(DaytimePeakPeriod period) {

        this.period = period;
    }

    public BigDecimal getCallCost(Call call, Tariff tariff) {

        BigDecimal peakCost = getPeakCost(call, tariff);
        BigDecimal offPeakCost = getOffPeakCost(call, tariff);

        BigDecimal totalCallCost = peakCost.add(offPeakCost);

        return totalCallCost.setScale(0, RoundingMode.HALF_UP);
    }

    private BigDecimal getPeakCost(Call call, Tariff tariff) {
        Duration peakDuration = getPeakDurationOf(call);
        return new BigDecimal(peakDuration.getStandardSeconds()).multiply(tariff.peakRate());
    }

    private BigDecimal getOffPeakCost(Call call, Tariff tariff) {
        Duration offPeakDuration = getOffPeakDurationOf(call);

        return new BigDecimal(offPeakDuration.getStandardSeconds()).multiply(tariff.offPeakRate());
    }

    private Duration getPeakDurationOf(Call call) {
        DateTime startTime = call.startTime();
        DateTime endTime = call.endTime();

        Duration peakDuration = Duration.ZERO;
        DateTime currentTime = new DateTime(startTime);
        while (currentTime.isBefore(endTime)) {
            DateTime nextPeakChange = period.nextPeakChange(currentTime);

            if (period.isPeak(currentTime)) {
                Interval callInterval = new Interval(currentTime, endTime);
                Interval periodInterval = new Interval(currentTime, nextPeakChange);

                Interval peakInterval = periodInterval.overlap(callInterval);

                peakDuration = peakDuration.plus(peakInterval.toDuration());
            }

            currentTime = new DateTime(nextPeakChange);
        }

        return peakDuration;
    }

    private Duration getOffPeakDurationOf(Call call) {
        Duration callDuration = new Duration(call.startTime(), call.endTime());
        Duration offPeakDuration = callDuration.minus(getPeakDurationOf(call));

        return offPeakDuration;
    }
}
