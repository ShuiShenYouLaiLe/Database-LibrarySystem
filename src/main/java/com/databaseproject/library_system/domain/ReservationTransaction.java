package com.databaseproject.library_system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
public class ReservationTransaction {
    @Embeddable
    public static class ReservationTransactionId implements Serializable {
        private Copy.CopyId copyId;
        @Column(nullable = false, updatable = false)
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long res_number;

        public Copy.CopyId getCopyId() {
            return copyId;
        }

        public long getRes_number() {
            return res_number;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ReservationTransactionId that = (ReservationTransactionId) o;

            if (res_number != that.res_number) return false;
            return copyId != null ? copyId.equals(that.copyId) : that.copyId == null;
        }

        @Override
        public int hashCode() {
            int result = copyId != null ? copyId.hashCode() : 0;
            result = 31 * result + (int) (res_number ^ (res_number >>> 32));
            return result;
        }
    }

    @EmbeddedId
    private ReservationTransactionId id;

    private Timestamp res_date_time;


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
