package com.databaseproject.library_system.model;

import com.databaseproject.library_system.domain.Person;
import com.databaseproject.library_system.domain.Publisher;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.Set;

public class Book implements DocumentInterface{
    private long docId;
    private String title;
    private Timestamp pDate = Timestamp.valueOf("2019-01-31 14:12:01");
    private int docType;
    private Publisher publisher;

    public Publisher getPublisher() {
        return publisher;
    }

    private Long isbn;
    @JsonIgnoreProperties({"journal"})
    private Set<Person> authors;

    public Book() {
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public Set<Person> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Person> authors) {
        this.authors = authors;
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


    @Override
    public void setDocId(long docId) {
        this.docId = docId;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
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

}
