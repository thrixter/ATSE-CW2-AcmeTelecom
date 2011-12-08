package com.acmetelecom.fixtures;

import fit.RowFixture;

/**
 * User: javad
 * Date: 06/12/2011
 */
public class TheBillShows extends RowFixture {

    @Override
    public Object[] query() throws Exception {
        return SystemUnderTest.printer.getRows().toArray();
    }

    @Override
    public Class<?> getTargetClass() {
        return BillRow.class;
    }

    public static class BillRow {
        public String CallerName;
        public String CallerNumber;
        public String PricePlan;
        public String CalledNumber;
        public String CallTime;
        public String Duration;
        public String Cost;
        public String Total;

        public BillRow(String CallerName, String CallerNumber, String PricePlan,
                String CalledNumber, String CallTime, String Duration,
                String Cost) {
            this.CallerName = CallerName;
            this.CallerNumber = CallerNumber;
            this.PricePlan = PricePlan;
            this.CalledNumber = CalledNumber;
            this.CallTime = CallTime;
            this.Duration = Duration;
            this.Cost = Cost;
        }
    }
}
