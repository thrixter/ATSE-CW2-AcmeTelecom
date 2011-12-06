package com.acmetelecom.fixtures;

import fit.ColumnFixture;

import java.util.Date;

public class BillingSystemFixture extends ColumnFixture {

    public Date time;
    public String callFrom;
    public String callTo;
    public String duration;

    public double cost() {
        return 0.0d;
    }

}