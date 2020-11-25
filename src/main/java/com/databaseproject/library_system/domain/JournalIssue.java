package com.databaseproject.library_system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class JournalIssue{
    @Embeddable
    public static class JournalIssueId implements Serializable {
        @Column(name = "doc_id", nullable = false, updatable = false)
        private long doc_id;

        @Column(nullable = false, updatable = false)
        private int issue_no;

        public long getDoc_id() {
            return doc_id;
        }

        public int getIssue_no() {
            return issue_no;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JournalIssueId that = (JournalIssueId) o;

            if (doc_id != that.doc_id) return false;
            return issue_no == that.issue_no;
        }

        @Override
        public int hashCode() {
            int result = (int) (doc_id ^ (doc_id >>> 32));
            result = 31 * result + issue_no;
            return result;
        }
    }
    @EmbeddedId
    private JournalIssueId id;

    private String scope;

    @ManyToOne
    @JoinColumn(name = "doc_id", insertable = false, updatable = false)
    //@JsonIgnoreProperties({"journal_issue"})
    @JsonIgnore
    private Document document;

    public Set<GuestEdits> getGuestEdits() {
        return guestEdits;
    }

    public void setGuestEdits(Set<GuestEdits> guestEdits) {
        this.guestEdits = guestEdits;
    }

    @OneToMany(mappedBy = "journalIssue", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonIgnore
    Set<GuestEdits> guestEdits;

    public JournalIssue() {
    }

    public JournalIssueId getId() {
        return id;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}
