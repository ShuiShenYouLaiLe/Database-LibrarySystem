package com.databaseproject.library_system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bid;
    private String name;
    private String location;

    public Set<Copy> getCopies() {
        return copies;
    }

    public void setCopies(Set<Copy> copies) {
        this.copies = copies;
    }

    @OneToMany(mappedBy = "branch", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonIgnore
    Set<Copy> copies;


    public Branch() {
    }

    public long getBid() {
        return bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
