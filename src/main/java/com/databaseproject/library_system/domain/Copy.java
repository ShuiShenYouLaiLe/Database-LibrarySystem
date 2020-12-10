package com.databaseproject.library_system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Copy {
    @Embeddable
    public static class CopyId implements Serializable {
        @Column(name = "doc_id", nullable = false, updatable = false)
        private long doc_id;

        @Column(name = "bid", nullable = false, updatable = false)
        private long bid;

        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(nullable = false, updatable = false)
        private long copy_num;

        public long getBid() {
            return bid;
        }

        public long getDoc_id() {
            return doc_id;
        }

        public long getCopy_num() {
            return copy_num;
        }

        public void setCopy_num(long copy_num) {
            this.copy_num = copy_num;
        }

        public void setDoc_id(long doc_id) {
            this.doc_id = doc_id;
        }

        public void setBid(long bid) {
            this.bid = bid;
        }

        public CopyId() {
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CopyId copyId = (CopyId) o;

            if (doc_id != copyId.doc_id) return false;
            if (doc_id != copyId.doc_id) return false;
            return copy_num == copyId.copy_num;
        }

        @Override
        public int hashCode() {
            int result = (int) (doc_id ^ (doc_id >>> 32));
            result = 31 * result + (int) (bid ^ (bid >>> 32));
            result = 31 * result + (int) (copy_num ^ (copy_num >>> 32));
            return result;
        }
    }


    @EmbeddedId
    private CopyId id;

    private String position;

    private int copyStatus;

    @ManyToOne
    @JoinColumn(name = "doc_id", insertable = false, updatable = false)
    @JsonIgnore
    private Document document;

    @ManyToOne
    @JoinColumn(name = "bid", insertable = false, updatable = false)
    @JsonIgnore
    private Branch branch;

    @OneToMany(mappedBy = "copy", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonIgnore
    Set<BorrowTransaction> borrowTransactions;

    @OneToMany(mappedBy = "copy", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonIgnore
    Set<ReservationTransaction> reservationTransactions;

    public Copy() {
    }

    public CopyId getId() {
        return id;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public Document getDocument() {
        return document;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public int getCopyStatus() {
        return copyStatus;
    }

    public void setCopyStatus(int copyStatus) {
        this.copyStatus = copyStatus;
    }

    public Set<BorrowTransaction> getBorrowTransactions() {
        return borrowTransactions;
    }

    public void setBorrowTransactions(Set<BorrowTransaction> borrowTransactions) {
        this.borrowTransactions = borrowTransactions;
    }

    public Set<ReservationTransaction> getReservationTransactions() {
        return reservationTransactions;
    }

    public void setId(CopyId id) {
        this.id = id;
    }

    public void setReservationTransactions(Set<ReservationTransaction> reservationTransactions) {
        this.reservationTransactions = reservationTransactions;
    }


}
