package com.databaseproject.library_system.model;

import java.math.BigInteger;

public class BorrowedBookWithCount {
    private String title;
    private BigInteger Borrowed_times;

    public BorrowedBookWithCount(String title, BigInteger borrowed_times) {
        this.title = title;
        Borrowed_times = borrowed_times;
    }

    public BorrowedBookWithCount() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigInteger getBorrowed_times() {
        return Borrowed_times;
    }

    public void setBorrowed_times(BigInteger borrowed_times) {
        Borrowed_times = borrowed_times;
    }
}
