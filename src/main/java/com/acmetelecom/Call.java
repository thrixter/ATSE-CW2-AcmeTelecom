package com.acmetelecom;

import javax.swing.text.DateFormatter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author dc408, ra808, je08, jm308
 */
public class Call {
    private CallEvent start;
    private CallEvent end;

    /**
     * 
     * @param start
     * @param end
     */
    public Call(CallEvent start, CallEvent end) {
        this.start = start;
        this.end = end;
    }

    /**
     * 
     * @return
     */
    public String callee() {
        return start.getCallee();
    }

    /**
     * 
     * @return
     */
    public int durationSeconds() {
        return (int) (((end.time() - start.time()) / 1000));
    }

    /**
     * 
     * @return
     */
    public String date() {
        return SimpleDateFormat.getInstance().format(new Date(start.time()));
    }

    /**
     * 
     * @return
     */
    public Date startTime() {
        return new Date(start.time());
    }

    /**
     * 
     * @return
     */
    public Date endTime() {
        return new Date(end.time());
    }
}
