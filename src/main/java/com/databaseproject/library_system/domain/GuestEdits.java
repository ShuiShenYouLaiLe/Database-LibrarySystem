package com.databaseproject.library_system.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class GuestEdits {
    @Embeddable
    public static class GuestEditsId implements Serializable {
        private JournalIssue.JournalIssueId journalIssueId;
        @Column(name = "pid", nullable = false, updatable = false)
        private long pid;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            GuestEditsId that = (GuestEditsId) o;

            if (pid != that.pid) return false;
            return journalIssueId != null ? journalIssueId.equals(that.journalIssueId) : that.journalIssueId == null;
        }

        @Override
        public int hashCode() {
            int result = journalIssueId != null ? journalIssueId.hashCode() : 0;
            result = 31 * result + (int) (pid ^ (pid >>> 32));
            return result;
        }

        public JournalIssue.JournalIssueId getJournalIssueId() {
            return journalIssueId;
        }

        public long getPid() {
            return pid;
        }
    }

    @EmbeddedId
    private GuestEditsId id;

    public GuestEditsId getId() {
        return id;
    }

    @ManyToOne
    @MapsId("journalIssueId")
    @JoinColumns({
            @JoinColumn(name="doc_id", referencedColumnName = "doc_id"),
            @JoinColumn(name="issue_no", referencedColumnName = "issue_no")
    })
    //@JsonIgnoreProperties({"guest_edits"})
    @JsonIgnore
    private JournalIssue journalIssue;

    @ManyToOne
    @JoinColumn(name = "pid", insertable = false, updatable = false)
    //@JsonIgnoreProperties({"guest_edits"})
    @JsonIgnore
    private Person person;

    public JournalIssue getJournalIssue() {
        return journalIssue;
    }

    public void setJournalIssue(JournalIssue journalIssue) {
        this.journalIssue = journalIssue;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}

