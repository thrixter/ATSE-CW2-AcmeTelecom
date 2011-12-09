package com.acmetelecom.billing;

import com.acmetelecom.calling.Call;
import com.acmetelecom.customer.Tariff;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * User: javad
 * Date: 07/12/2011
 */
public class FixedRateBillCalculator implements BillCalculator {

    private final DaytimePeakPeriod peakPeriod;

    public FixedRateBillCalculator(DaytimePeakPeriod peakPeriod) {
        this.peakPeriod = peakPeriod;
    }

    public BigDecimal getCallCost(Call call, Tariff tariff) {
        BigDecimal callCost;

        if (this.peakPeriod.isOffPeak(call.startTime()) && this.peakPeriod.isOffPeak(call.endTime()) &&
                call.durationSeconds() < 12 * 60 * 60) {
            callCost = new BigDecimal(call.durationSeconds()).multiply(tariff.offPeakRate());
        } else {
            callCost = new BigDecimal(call.durationSeconds()).multiply(tariff.peakRate());
        }

        return callCost.setScale(0, RoundingMode.HALF_UP);
    }

}
