package com.martinfowler.ch1;

public class Movie {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;

    private String _title;    // 名稱
    private Price _price;     // 價格(代號)

    public Movie(String title, int priceCode) {
        _title = title;
        setPriceCode(priceCode);
    }

    public int getPriceCode() {
        return _price.getPriceCode();
    }

    public void setPriceCode(int arg) {
        switch (arg) {
            case REGULAR:         // 普通片
                _price = new RegularPrice();
                break;
            case CHILDRENS:       // 兒童片
                _price = new ChildrensPrice();
                break;
            case NEW_RELEASE:     // 新片
                _price = new NewReleasePrice();
                break;
            default:
                throw new IllegalArgumentException("Incorrect Price Code");
        }
    }

    public String getTitle() {
        return _title;
    }

    double getCharge(int daysRented) {
        Price price = _price;
        return price.getCharge(daysRented);
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
