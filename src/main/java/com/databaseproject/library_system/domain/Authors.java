package com.databaseproject.library_system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Authors {
    @Embeddable
    public static class AuthorsId implements Serializable {
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

            AuthorsId that = (AuthorsId) o;

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
    private AuthorsId id;

    @ManyToOne
    @JoinColumn(name = "doc_id", insertable = false, updatable = false)
    //@JsonIgnoreProperties({"guest_edits"})
    @JsonIgnore
    private Document book;

    @ManyToOne
    @JoinColumn(name = "pid", insertable = false, updatable = false)
    //@JsonIgnoreProperties({"guest_edits"})
    @JsonIgnore
    private Person author;

    public Document getBook() {
        return book;
    }

    public void setBook(Document book) {
        this.book = book;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    public AuthorsId getId() {
        return id;
    }

    public Authors() {
    }
}

