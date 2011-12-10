package com.acmetelecom.calling;

import org.joda.time.DateTime;
import org.joda.time.Duration;

/**
 * 
 * @author dc408, ra808, je08, jm308
 */
public class Call {
    private static String dateFormatPattern = "dd/MM/yy HH:mm";

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
        return (int) new Duration(start.time(), end.time()).getStandardSeconds();
//        return (int) (((end.time() - start.time()) / 1000));
    }

    /**
     * 
     * @return
     */
    public String date() {
        return start.time().toString(dateFormatPattern);
//        return new DateTime(start.time()).toString(dateFormatPattern);
    }

    /**
     * 
     * @return
     */
    public DateTime startTime() {
        return start.time();
    }

    /**
     * 
     * @return
     */
    public DateTime endTime() {
        return end.time();
    }

    public int hashCode() {
        return end.hashCode();
    }

    public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (other instanceof Call) {
			Call call = (Call) other;

            if (start == null && call.start != null
                    || end == null && call.end != null) {
                return false;
            }

			return start.equals(call.start) && end.equals(call.end);
		}
		return false;
	}

}
