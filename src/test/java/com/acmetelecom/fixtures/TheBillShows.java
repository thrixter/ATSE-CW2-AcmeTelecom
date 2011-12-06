package com.acmetelecom.fixtures;

import com.acmetelecom.SystemUnderTest;
import fit.ColumnFixture;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * User: javad
 * Date: 06/12/2011
 */
public class TheBillShows extends ColumnFixture {

    public String output() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        SystemUnderTest.billingSystem.createCustomerBills();

        String bill = out.toString();

        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));

        return bill.replaceAll("\\n", "");
    }

}
