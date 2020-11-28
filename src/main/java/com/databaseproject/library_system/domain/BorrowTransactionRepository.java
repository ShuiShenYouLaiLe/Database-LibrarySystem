package com.databaseproject.library_system.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowTransactionRepository extends JpaRepository<BorrowTransaction, Long> {

}
