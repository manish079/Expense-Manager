package com.manishprajapat.example.expensemanager;

public class POJO {
    String supplierName;
    String category;
    String expenseAmount;
    String PendingAmount;
    String date;
    String paidAmt;
    String day_of_month;
    String month_of_year;
    String year;

    public POJO(String supplierName, String category, String expenseAmount, String pendingAmount, String date, String paidAmt, String day_of_month, String month_of_year, String year) {
        this.supplierName = supplierName;
        this.category = category;
        this.expenseAmount = expenseAmount;
        PendingAmount = pendingAmount;
        this.date = date;
        this.paidAmt = paidAmt;
        this.day_of_month = day_of_month;
        this.month_of_year = month_of_year;
        this.year = year;
    }

    public POJO() {

    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(String expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public String getPendingAmount() {
        return PendingAmount;
    }

    public void setPendingAmount(String pendingAmount) {
        PendingAmount = pendingAmount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPaidAmt() {
        return paidAmt;
    }

    public void setPaidAmt(String paidAmt) {
        this.paidAmt = paidAmt;
    }

    public String getDay_of_month() {
        return day_of_month;
    }

    public void setDay_of_month(String day_of_month) {
        this.day_of_month = day_of_month;
    }

    public String getMonth_of_year() {
        return month_of_year;
    }

    public void setMonth_of_year(String month_of_year) {
        this.month_of_year = month_of_year;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
