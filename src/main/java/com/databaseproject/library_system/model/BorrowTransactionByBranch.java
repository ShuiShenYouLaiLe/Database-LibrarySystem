package com.databaseproject.library_system.model;

import java.math.BigInteger;
import java.sql.Timestamp;

public class BorrowTransactionByBranch {
    private BigInteger bid;
    private String name;
    private Timestamp bor_date_time;
    private Timestamp ret_date_time;

    public BorrowTransactionByBranch() {
    }

    public BorrowTransactionByBranch(BigInteger bid, String name, Timestamp bor_date_time, Timestamp ret_date_time) {
        this.bid = bid;
        this.name = name;
        this.bor_date_time = bor_date_time;
        this.ret_date_time = ret_date_time;
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

    public Timestamp getBor_date_time() {
        return bor_date_time;
    }

    public void setBor_date_time(Timestamp bor_date_time) {
        this.bor_date_time = bor_date_time;
    }

    public Timestamp getRet_date_time() {
        return ret_date_time;
    }

    public void setRet_date_time(Timestamp ret_date_time) {
        this.ret_date_time = ret_date_time;
    }
}
