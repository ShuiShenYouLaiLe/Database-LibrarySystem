package com.databaseproject.library_system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long pid;

    @OneToMany(mappedBy = "person", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonIgnore
    private Set<GuestEdits> guestEdits;

    @OneToMany(mappedBy = "author", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonIgnore
    private Set<Authors> authors;

    @OneToMany(mappedBy = "chairs", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonIgnore
    private Set<Chairs> chairs;


    @OneToOne(mappedBy = "editor")
    private Document journal;
    public Set<Authors> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Authors> authors) {
        this.authors = authors;
    }

    public Set<Chairs> getChairs() {
        return chairs;
    }

    public void setChairs(Set<Chairs> chairs) {
        this.chairs = chairs;
    }

    public Document getJournal() {
        return journal;
    }

    public void setJournal(Document journal) {
        this.journal = journal;
    }

    public Set<GuestEdits> getGuestEdits() {
        return guestEdits;
    }

    public void setGuestEdits(Set<GuestEdits> guestEdits) {
        this.guestEdits = guestEdits;
    }

    private String p_name;

    public long getPid() {
        return pid;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public Person() {
    }
}
