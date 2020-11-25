package com.databaseproject.library_system.model;

import com.databaseproject.library_system.domain.JournalIssue;
import com.databaseproject.library_system.domain.Person;
import com.databaseproject.library_system.domain.Publisher;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public class JournalVolume implements DocumentInterface{
    private Long volume_num;
    private List<JournalIssue> journalIssues;
    @JsonIgnoreProperties({"journal"})
    private Person chiefEditor;
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

    public Publisher getPublisher() {
        return publisher;
    }

    public JournalVolume() {
    }

    public Long getVolume_num() {
        return volume_num;
    }

    public void setVolume_num(Long volume_num) {
        this.volume_num = volume_num;
    }

    public List<JournalIssue> getJournalIssues() {
        return journalIssues;
    }

    public void setJournalIssues(List<JournalIssue> journalIssues) {
        this.journalIssues = journalIssues;
    }

    public Person getChiefEditor() {
        return chiefEditor;
    }

    public void setChiefEditor(Person chiefEditor) {
        this.chiefEditor = chiefEditor;
    }
}
