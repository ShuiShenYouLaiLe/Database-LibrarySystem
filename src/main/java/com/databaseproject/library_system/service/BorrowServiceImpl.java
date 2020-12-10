package com.databaseproject.library_system.service;

import com.databaseproject.library_system.domain.BorrowTransaction;
import com.databaseproject.library_system.domain.BorrowTransactionRepository;
import com.databaseproject.library_system.domain.Copy;
import com.databaseproject.library_system.domain.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowServiceImpl implements BorrowService{

    @Autowired
    BorrowTransactionRepository  repository;

    @Override
    public BorrowTransaction update(BorrowTransaction borrowTransaction) {
        return repository.save(borrowTransaction);
    }

    @Override
    public BorrowTransaction getByCopy(Copy copy, int status) {
        return repository.getBorrowTransactionByCopyAndReservationStatus(copy, status);
    }

    @Override
    public BorrowTransaction add(BorrowTransaction borrowTransaction) {
        return repository.save(borrowTransaction);
    }

    @Override
    public List<BorrowTransaction> getByReaderAndStatus(Reader reader, int status) {
        return repository.getBorrowTransactionsByReaderAndReservationStatus(reader, status);
    }
}
