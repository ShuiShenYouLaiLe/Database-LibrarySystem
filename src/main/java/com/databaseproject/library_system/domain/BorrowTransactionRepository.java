package com.databaseproject.library_system.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowTransactionRepository extends JpaRepository<BorrowTransaction, Long> {
    List<BorrowTransaction> getBorrowTransactionsByReaderAndReservationStatus(Reader reader, int status);
    BorrowTransaction getBorrowTransactionByCopyAndReservationStatus(Copy copy, int status);
}
