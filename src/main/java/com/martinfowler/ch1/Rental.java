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
        Movie movie = getMovie();
        int daysRented = getDaysRented();
        double result = 0;
        switch (movie.getPriceCode()) {
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

    int getFrequentRenterPoints() {
        if (getMovie().getPriceCode() == Movie.NEW_RELEASE
            && getDaysRented() > 1) {
            return 2;
        } else {
            return 1;
        }
    }
}
