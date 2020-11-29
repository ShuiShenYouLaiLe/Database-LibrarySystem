package com.databaseproject.library_system.service;

import com.databaseproject.library_system.domain.Reader;
import com.databaseproject.library_system.domain.ReservationTransaction;
import com.databaseproject.library_system.domain.ReservationTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService{
    @Autowired
    ReservationTransactionRepository repository;

    @Override
    public ReservationTransaction update(ReservationTransaction reservation) {
        return repository.save(reservation);
    }

    @Override
    public ReservationTransaction create(ReservationTransaction reservation) {
        return repository.save(reservation);
    }

    @Override
    public List<ReservationTransaction> getDataByReader(Reader reader) {
        return repository.getReservationTransactionsByReader(reader);
    }

    @Override
    public List<ReservationTransaction> getDataByReaderAndStatus(Reader reader, int status) {
        return repository.getReservationTransactionsByReaderAndReservationStatus(reader, status);
    }
}
