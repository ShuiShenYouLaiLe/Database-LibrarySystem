package com.databaseproject.library_system.model;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BorrowerWithCount {
    private String reader_name;
    private BigInteger reader_id;
    private BigInteger Borrowed_book_count;

    public BorrowerWithCount(String reader_name, BigInteger reader_id, BigInteger borrowed_book_count) {
        this.reader_name = reader_name;
        this.reader_id = reader_id;
        Borrowed_book_count = borrowed_book_count;
    }

    public BigInteger getReader_id() {
        return reader_id;
    }

    public void setReader_id(BigInteger reader_id) {
        this.reader_id = reader_id;
    }

    public BigInteger getBorrowed_book_count() {
        return Borrowed_book_count;
    }

    public void setBorrowed_book_count(BigInteger borrowed_book_count) {
        Borrowed_book_count = borrowed_book_count;
    }

    public BorrowerWithCount() {
    }

    public String getReader_name() {
        return reader_name;
    }

    public void setReader_name(String reader_name) {
        this.reader_name = reader_name;
    }

}
