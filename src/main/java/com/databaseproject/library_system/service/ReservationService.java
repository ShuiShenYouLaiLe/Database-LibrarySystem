package com.databaseproject.library_system.service;

import com.databaseproject.library_system.domain.Reader;
import com.databaseproject.library_system.domain.ReservationTransaction;

import java.util.List;

public interface ReservationService {
    ReservationTransaction update(ReservationTransaction reservationTransaction);
    ReservationTransaction create(ReservationTransaction reservationTransaction);
    List<ReservationTransaction> getDataByReader(Reader reader);
    List<ReservationTransaction> getDataByReaderAndStatus(Reader reader, int status);
}
