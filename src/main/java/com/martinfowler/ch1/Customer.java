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
        Enumeration<Rental> rentals = _rentals.elements();
        String result = "Rental Record for " + getName() + "\n";

        while (rentals.hasMoreElements()) {
            Rental each = rentals.nextElement();    // 取得一筆租借紀錄

            // show figures for this rental (顯示此筆租借資料)
            result += "\t" + each.getMovie().getTitle() + "\t" + each.getCharge() + "\n";
        }

        // add footer lines (結尾列印)
        result += "Amount owed is " + getTotalCharge() + "\n";
        result += "You earned " + getTotalFrequentRenterPoints() + " frequent renter points";
        return result;
    }

    public String htmlStatement() {
        Enumeration<Rental> rentals = _rentals.elements();
        String result = "<h1>Rentals for <em>" + getName() + "</em></h1>\n";

        while (rentals.hasMoreElements()) {
            Rental each = rentals.nextElement();

            // show figures for each rental
            result += each.getMovie().getTitle() + ": " + each.getCharge() + "<br>\n";
        }

        // add footer lines
        result += "<p>You owe <em>" + getTotalCharge() + "</em></p>\n";
        result += "<p>On this rental you earned <em>" + getTotalFrequentRenterPoints() + "</em> frequent renter " +
            "points</p>";
        return result;
    }

    // query method
    private double getTotalCharge() {
        double totalAmount = 0;
        Enumeration<Rental> rentals = _rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental each = rentals.nextElement();
            totalAmount += each.getCharge();
        }
        return totalAmount;
    }

    // query method
    private int getTotalFrequentRenterPoints() {
        int result = 0;
        Enumeration<Rental> rentals = _rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental each = rentals.nextElement();
            result += each.getFrequentRenterPoints();
        }
        return result;
    }
}
