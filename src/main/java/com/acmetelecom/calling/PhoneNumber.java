package com.acmetelecom.calling;

/**
 * User: javad
 * Date: 20/12/2011
 */
public class PhoneNumber {

    private final String number;

    public PhoneNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhoneNumber that = (PhoneNumber) o;

        return number.equals(that.number);

    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }

    @Override
    public String toString() {
        return number;
    }
}
