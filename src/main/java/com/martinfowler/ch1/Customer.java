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
            Rental each = rentals.nextElement();    // 取得一筆租借紀錄
            frequentRenterPoints += each.getFrequentRenterPoints();

            // show figures for this rental (顯示此筆租借資料)
            result += "\t" + each.getMovie().getTitle() + "\t" + each.getCharge() + "\n";
            totalAmount += each.getCharge();
        }

        // add footer lines (結尾列印)
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";
        return result;
    }

}
