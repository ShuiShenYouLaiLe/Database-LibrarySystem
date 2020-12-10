package com.databaseproject.library_system.util;

import com.databaseproject.library_system.domain.*;
import com.databaseproject.library_system.model.BorrowStatus;
import com.databaseproject.library_system.model.CopyStatus;
import com.databaseproject.library_system.model.ReservationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class BorrowAndReserveUtil {

    @Autowired
    ReservationTransactionRepository resRepository;

    @Autowired
    BorrowTransactionRepository borRepository;

    @Autowired
    CopyRepository copyRepository;

    @Transactional
    public boolean allowedForReserve(Reader reader) {
        List<ReservationTransaction> resTrans = resRepository
                .getReservationTransactionsByReaderAndReservationStatus(reader,
                        ReservationStatus.RESERVED.getStatusValue());
        List<ReservationTransaction> valid_trans = new ArrayList<>();
        Date date = new Date();
        for (ReservationTransaction resTran: resTrans) {
            if (date.getTime() - resTran.getRes_date_time().getTime() <= 86400000) {
                valid_trans.add(resTran);
            } else {
                Copy copy = resTran.getCopy();
                copy.setCopyStatus(CopyStatus.AVAILABLE.getStatusValue());
                resTran.setReservationStatus(ReservationStatus.DELETED.getStatusValue());
                copyRepository.save(copy);
                resRepository.save(resTran);
            }
        }
        int resCount = valid_trans.size();
        int borCount = borRepository
                .getBorrowTransactionsByReaderAndReservationStatus(reader,
                        BorrowStatus.BORROWED.getStatusValue()).size();
        return (borCount + resCount) < 10;
    }
}
