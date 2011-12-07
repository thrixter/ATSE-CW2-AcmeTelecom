package com.acmetelecom;

import java.math.BigDecimal;

/**
* User: javad
* Date: 07/12/2011
*/
class LineItem {
    private Call call;
    private BigDecimal callCost;

    /**
     *
     * @param call
     * @param callCost
     */
    public LineItem(Call call, BigDecimal callCost) {
        this.call = call;
        this.callCost = callCost;
    }

    /**
     *
     * @return
     */
    public String date() {
        return call.date();
    }

    /**
     *
     * @return
     */
    public String callee() {
        return call.callee();
    }

    /**
     *
     * @return
     */
    public String durationMinutes() {
        return "" + call.durationSeconds() / 60 + ":" + String.format("%02d", call.durationSeconds() % 60);
    }

    /**
     *
     * @return
     */
    public BigDecimal cost() {
        return callCost;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 7;
        hash = prime * hash + ((call == null) ? 0 : call.hashCode());
        hash = prime * hash + ((callCost == null) ? 0 : callCost.hashCode());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj instanceof LineItem) {
            // obj must be LineItem at this point
            LineItem other = (LineItem) obj;

            if (call == null && other.call != null
                    || callCost == null && other.callCost != null) {
                return false;
            }
            
            return call.equals(other.call) && callCost.equals(other.callCost);
        }

        return false;
    }
}
