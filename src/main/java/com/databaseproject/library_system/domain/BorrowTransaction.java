package com.databaseproject.library_system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
public class BorrowTransaction {
    @Embeddable
    public static class BorrowTransactionId implements Serializable {
        private Copy.CopyId copyId;
        @Column(nullable = false, updatable = false)
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long bor_number;

        public Copy.CopyId getCopyId() {
            return copyId;
        }

        public long getBor_number() {
            return bor_number;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            BorrowTransactionId that = (BorrowTransactionId) o;

            if (bor_number != that.bor_number) return false;
            return copyId != null ? copyId.equals(that.copyId) : that.copyId == null;
        }

        @Override
        public int hashCode() {
            int result = copyId != null ? copyId.hashCode() : 0;
            result = 31 * result + (int) (bor_number ^ (bor_number >>> 32));
            return result;
        }
    }

    @EmbeddedId
    private BorrowTransactionId id;

    private Timestamp bor_date_time;

    private Timestamp ret_date_time;

    @ManyToOne
    @JoinColumn(name = "reader_id", insertable = false, updatable = false)
    @JsonIgnore
    private Reader reader;

    @MapsId("copyId")
    @JoinColumns({
            @JoinColumn(name = "doc_id", referencedColumnName = "doc_id"),
            @JoinColumn(name = "bid", referencedColumnName = "bid"),
            @JoinColumn(name = "copy_num", referencedColumnName = "copy_num")
    })
    @ManyToOne
    private Copy copy;

}
