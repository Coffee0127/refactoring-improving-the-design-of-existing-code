package com.martinfowler.ch1;

public abstract class Price {
    abstract int getPriceCode();    // 取得價格代號

    abstract double getCharge(int daysRented);
}
