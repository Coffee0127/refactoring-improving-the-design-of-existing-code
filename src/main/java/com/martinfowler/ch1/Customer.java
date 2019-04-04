package com.martinfowler.ch1;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {
    private String _name;                              // 姓名
    private Vector<Rental> _rentals = new Vector<>();  // 租借紀錄

    public Customer(String name) {
        _name = name;
    }

    public void addRental(Rental arg) {
        _rentals.addElement(arg);
    }

    public String getName() {
        return _name;
    }

    public String statement() {
        double totalAmount = 0;         // 總消費金額
        int frequentRenterPoints = 0;   // 常客積點
        Enumeration<Rental> rentals = _rentals.elements();
        String result = "Rental Record for " + getName() + "\n";

        while (rentals.hasMoreElements()) {
            double thisAmount = 0;
            Rental each = rentals.nextElement();    // 取得一筆租借紀錄

            thisAmount = amountFor(each);   // 計算一筆租片費用

            // add frequent renter points (累加 常客積點)
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            if (each.getMovie().getPriceCode() == Movie.NEW_RELEASE
                && each.getDaysRented() > 1) {
                frequentRenterPoints++;
            }

            // show figures for this rental (顯示此筆租借資料)
            result += "\t" + each.getMovie().getTitle() + "\t" + thisAmount + "\n";
            totalAmount += thisAmount;
        }

        // add footer lines (結尾列印)
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";
        return result;
    }

    private double amountFor(Rental aRental) {     // 計算一筆租片費用
        double thisAmount = 0;
        switch (aRental.getMovie().getPriceCode()) {
            case Movie.REGULAR:         // 普通片
                thisAmount += 2;
                if (aRental.getDaysRented() > 2) {
                    thisAmount += (aRental.getDaysRented() - 2) * 1.5;
                }
                break;
            case Movie.NEW_RELEASE:     // 新片
                thisAmount += aRental.getDaysRented() * 3;
                break;
            case Movie.CHILDRENS:       // 兒童片
                thisAmount += 1.5;
                if (aRental.getDaysRented() > 3) {
                    thisAmount += (aRental.getDaysRented() - 3) * 1.5;
                }
                break;
        }
        return thisAmount;
    }
}
