package com.martinfowler.ch1;

public abstract class Price {
    abstract int getPriceCode();    // 取得價格代號

    double getCharge(int daysRented) {
        double result = 0;
        switch (getPriceCode()) {
            case Movie.REGULAR:         // 普通片
                result += 2;
                if (daysRented > 2) {
                    result += (daysRented - 2) * 1.5;
                }
                break;
            case Movie.NEW_RELEASE:     // 新片
                result += daysRented * 3;
                break;
            case Movie.CHILDRENS:       // 兒童片
                result += 1.5;
                if (daysRented > 3) {
                    result += (daysRented - 3) * 1.5;
                }
                break;
        }
        return result;
    }
}
