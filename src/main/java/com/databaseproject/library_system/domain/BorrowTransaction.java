package com.databaseproject.library_system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
public class BorrowTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bor_number;

    private Timestamp bor_date_time;

    private Timestamp ret_date_time;

    private int reservationStatus;

    @ManyToOne
    @JoinColumn(name = "reader_id", insertable = true, updatable = true)
    @JsonIgnore
    private Reader reader;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "doc_id", referencedColumnName = "doc_id"),
            @JoinColumn(name = "bid", referencedColumnName = "bid"),
            @JoinColumn(name = "copy_num", referencedColumnName = "copy_num")
    })
    @JsonIgnore
    private Copy copy;

    public BorrowTransaction() {
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

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Copy getCopy() {
        return copy;
    }

    public void setCopy(Copy copy) {
        this.copy = copy;
    }

    public long getBor_number() {
        return bor_number;
    }

    public int getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(int reservationStatus) {
        this.reservationStatus = reservationStatus;
    }
}
