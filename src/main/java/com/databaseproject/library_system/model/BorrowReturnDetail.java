package com.databaseproject.library_system.model;
import java.sql.Timestamp;

public class BorrowReturnDetail {

    private long bor_number;

    private Timestamp bor_date_time;

    private double fine;

    public long getBor_number() {
        return bor_number;
    }

    public void setBor_number(long bor_number) {
        this.bor_number = bor_number;
    }

    public Timestamp getBor_date_time() {
        return bor_date_time;
    }

    public void setBor_date_time(Timestamp bor_date_time) {
        this.bor_date_time = bor_date_time;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }
}
