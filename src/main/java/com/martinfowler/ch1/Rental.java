package com.martinfowler.ch1;

public class Rental {
    private Movie _movie;     // 影片
    private int _daysRented;  // 租期

    public Rental(Movie movie, int daysRented) {
        _movie = movie;
        _daysRented = daysRented;
    }

    public int getDaysRented() {
        return _daysRented;
    }

    public Movie getMovie() {
        return _movie;
    }

    double getCharge() {     // 計算一筆租片費用
        return getMovie().getCharge(getDaysRented());
    }

    int getFrequentRenterPoints() {
        if (getMovie().getPriceCode() == Movie.NEW_RELEASE
            && getDaysRented() > 1) {
            return 2;
        } else {
            return 1;
        }
    }
}
