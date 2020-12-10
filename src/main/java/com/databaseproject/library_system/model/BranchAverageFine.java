package com.databaseproject.library_system.model;

import java.math.BigInteger;

public class BranchAverageFine {
    BigInteger bid;
    String name;
    String average_fine;

    public BranchAverageFine() {
    }

    public BigInteger getBid() {
        return bid;
    }

    public void setBid(BigInteger bid) {
        this.bid = bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAverage_fine() {
        return average_fine;
    }

    public void setAverage_fine(String average_fine) {
        this.average_fine = average_fine;
    }
}
