package com.databaseproject.library_system.model;

import com.databaseproject.library_system.domain.Person;
import com.databaseproject.library_system.domain.Publisher;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.Set;

public class Proceedings implements DocumentInterface{
    private Timestamp cDate;
    private String cLocation;
    @JsonIgnoreProperties({"journal"})
    private Set<Person> chairs;

    public Proceedings() {
    }

    private long docId;
    private String title;
    private Timestamp pDate = Timestamp.valueOf("2019-01-31 14:12:01");
    private int docType;
    private Publisher publisher;

    @Override
    public void setDocId(long docId) {
        this.docId = docId;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    public long getDocId() {
        return docId;
    }

    public String getTitle() {
        return title;
    }

    public Timestamp getpDate() {
        return pDate;
    }

    public int getDocType() {
        return docType;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    @Override
    public void setPDate(Timestamp pDate) {
        this.pDate = pDate;
    }

    @Override
    public void setDocType(int docType) {
        this.docType = docType;
    }

    @Override
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Timestamp getcDate() {
        return cDate;
    }

    public void setcDate(Timestamp cDate) {
        this.cDate = cDate;
    }

    public String getcLocation() {
        return cLocation;
    }

    public void setcLocation(String cLocation) {
        this.cLocation = cLocation;
    }

    public Set<Person> getChairs() {
        return chairs;
    }

    public void setChairs(Set<Person> chairs) {
        this.chairs = chairs;
    }
}
