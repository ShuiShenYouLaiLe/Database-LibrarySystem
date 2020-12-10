package com.databaseproject.library_system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
public class ReservationTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long res_number;

    private Timestamp res_date_time;

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


    public Timestamp getRes_date_time() {
        return res_date_time;
    }

    public void setRes_date_time(Timestamp res_date_time) {
        this.res_date_time = res_date_time;
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

    public long getRes_number() {
        return res_number;
    }

    public int getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(int reservationStatus) {
        this.reservationStatus = reservationStatus;
    }
}
