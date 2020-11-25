package com.databaseproject.library_system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Publisher {
    public long getPublisher_id() {
        return publisher_id;
    }

    public String getPub_name() {
        return pub_name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long publisher_id;
    private String pub_name;
    private String address;

    @OneToMany(mappedBy = "publisher", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonIgnore
    private Set<Document> documents = new HashSet<>();

    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public Publisher() {
    }

    public String getPubName() {
        return pub_name;
    }

    public void setPubName(String pub_name) {
        this.pub_name = pub_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
