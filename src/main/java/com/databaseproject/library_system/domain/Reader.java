package com.databaseproject.library_system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long reader_id;
    private int type;
    private String reader_name;
    private long phone_no;
    private String address;

    @OneToMany(mappedBy = "reader", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonIgnore
    Set<BorrowTransaction> borrowTransactions;

    @OneToMany(mappedBy = "reader", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonIgnore
    Set<ReservationTransaction> reservationTransactions;

    public Reader() {
    }

    public Set<ReservationTransaction> getReservationTransactions() {
        return reservationTransactions;
    }

    public void setReservationTransactions(Set<ReservationTransaction> reservationTransactions) {
        this.reservationTransactions = reservationTransactions;
    }

    public Set<BorrowTransaction> getBorrowTransactions() {
        return borrowTransactions;
    }

    public void setBorrowTransactions(Set<BorrowTransaction> borrowTransactions) {
        this.borrowTransactions = borrowTransactions;
    }

    public long getReader_id() {
        return reader_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getReader_name() {
        return reader_name;
    }

    public void setReader_name(String reader_name) {
        this.reader_name = reader_name;
    }

    public long getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(long phone_no) {
        this.phone_no = phone_no;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
