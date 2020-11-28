package com.databaseproject.library_system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long doc_id;
    private String title;
    private Timestamp p_date;
    private Long doc_type;
    private Long isbn;
    private Long volume_num;
    private Timestamp c_date;
    private String c_location;
    private Long editor_id;
    @ManyToOne
    @JoinColumn(name = "publisher_id")
    //@JsonIgnoreProperties({"document"})
    @JsonIgnore
    private Publisher publisher;

    @OneToMany(mappedBy = "document", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonIgnore
    Set<JournalIssue> journalIssues;

    @OneToMany(mappedBy = "book", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonIgnore
    Set<Authors> authors;

    @OneToMany(mappedBy = "conferenceProceedings", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonIgnore
    Set<Chairs> chairs;

    @OneToMany(mappedBy = "document", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonIgnore
    Set<Copy> copies;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "editor_id", referencedColumnName = "pid",insertable = false, updatable = false)
    @JsonIgnore
    private Person editor;

    public Person getEditor() {
        return editor;
    }

    public void setEditor(Person editor) {
        this.editor = editor;
    }

    public Set<JournalIssue> getJournalIssues() {
        return journalIssues;
    }

    public void setJournalIssues(Set<JournalIssue> journalIssues) {
        this.journalIssues = journalIssues;
    }

    public long getDoc_id() {
        return doc_id;
    }

    public Document() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getP_date() {
        return p_date;
    }

    public void setP_date(Timestamp p_date) {
        this.p_date = p_date;
    }

    public Long getDoc_type() {
        return doc_type;
    }

    public void setDoc_type(Long doc_type) {
        this.doc_type = doc_type;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public Long getVolume_num() {
        return volume_num;
    }

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

    public void setVolume_num(Long volume_num) {
        this.volume_num = volume_num;
    }

    public Timestamp getC_date() {
        return c_date;
    }

    public void setC_date(Timestamp c_date) {
        this.c_date = c_date;
    }

    public String getC_location() {
        return c_location;
    }

    public void setC_location(String c_location) {
        this.c_location = c_location;
    }

    public Long getEditor_id() {
        return editor_id;
    }

    public void setEditor_id(Long editor_id) {
        this.editor_id = editor_id;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Set<Copy> getCopies() {
        return copies;
    }

    public void setCopies(Set<Copy> copies) {
        this.copies = copies;
    }
}
