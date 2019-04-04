package com.martinfowler.ch1;

public class Movie {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;

    private String _title;    // 名稱
    private int _priceCode;   // 價格(代號)

    public Movie(String title, int priceCode) {
        _title = title;
        setPriceCode(priceCode);
    }

    public int getPriceCode() {
        return _priceCode;
    }

    public void setPriceCode(int arg) {
        _priceCode = arg;
    }

    public String getTitle() {
        return _title;
    }

    double getCharge(int daysRented) {
        double result = 0;
        switch (getPriceCode()) {
            case REGULAR:         // 普通片
                result += 2;
                if (daysRented > 2) {
                    result += (daysRented - 2) * 1.5;
                }
                break;
            case NEW_RELEASE:     // 新片
                result += daysRented * 3;
                break;
            case CHILDRENS:       // 兒童片
                result += 1.5;
                if (daysRented > 3) {
                    result += (daysRented - 3) * 1.5;
                }
                break;
        }
        return result;
    }

    int getFrequentRenterPoints(int daysRented) {
        if (getPriceCode() == NEW_RELEASE
            && daysRented > 1) {
            return 2;
        } else {
            return 1;
        }
    }
}
