package com.pluralsight;

// This class stores information about bakery transactions
public class Transaction {

    // Store the transaction information
    private String date;
    private String time;
    private String description;
    private String vendor;
    private double amount;

    // Constructor to create a transaction
    public Transaction(String date, String time, String description,
                       String vendor, double amount) {

        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    // Get the transaction date
    public String getDate() {
        return date;
    }

    // Set the transaction date
    public void setDate(String date) {
        this.date = date;
    }

    // Get the transaction time
    public String getTime() {
        return time;
    }

    // Set the transaction time
    public void setTime(String time) {
        this.time = time;
    }

    // Get the transaction description
    public String getDescription() {
        return description;
    }

    // Set the transaction description
    public void setDescription(String description) {
        this.description = description;
    }

    // Get the transaction vendor
    public String getVendor() {
        return vendor;
    }

    // Set the transaction vendor
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    // Get the transaction amount
    public double getAmount() {
        return amount;
    }

    // Set the transaction amount
    public void setAmount(double amount) {
        this.amount = amount;
    }
}