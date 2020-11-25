package com.databaseproject.library_system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Chairs {
    @Embeddable
    public static class ChairsId implements Serializable {
        @Column(name = "pid", nullable = false, updatable = false)
        private long pid;

        @Column(name = "doc_id", nullable = false, updatable = false)
        private long doc_id;

        public long getPid() {
            return pid;
        }

        public long getDoc_id() {
            return doc_id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ChairsId that = (ChairsId) o;

            if (pid != that.pid) return false;
            return doc_id == that.doc_id;
        }

        @Override
        public int hashCode() {
            int result = (int) (pid ^ (pid >>> 32));
            result = 31 * result + (int) (doc_id ^ (doc_id >>> 32));
            return result;
        }
    }

    @EmbeddedId
    private ChairsId id;

    @ManyToOne
    @JoinColumn(name = "doc_id", insertable = false, updatable = false)
    //@JsonIgnoreProperties({"guest_edits"})
    @JsonIgnore
    private Document conferenceProceedings;

    @ManyToOne
    @JoinColumn(name = "pid", insertable = false, updatable = false)
    //@JsonIgnoreProperties({"guest_edits"})
    @JsonIgnore
    private Person chairs;

    public Document getConferenceProceedings() {
        return conferenceProceedings;
    }

    public void setConferenceProceedings(Document conferenceProceedings) {
        this.conferenceProceedings = conferenceProceedings;
    }

    public Person getChairs() {
        return chairs;
    }

    public void setChairs(Person chairs) {
        this.chairs = chairs;
    }
}
