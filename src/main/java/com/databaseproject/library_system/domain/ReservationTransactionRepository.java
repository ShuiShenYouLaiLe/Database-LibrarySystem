package com.databaseproject.library_system.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationTransactionRepository extends JpaRepository<ReservationTransaction, Long> {
    List<ReservationTransaction> getReservationTransactionsByReaderAndReservationStatus(Reader reader, int status);
    List<ReservationTransaction> getReservationTransactionsByReader(Reader reader);
}
