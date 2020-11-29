package com.databaseproject.library_system.web;

import com.databaseproject.library_system.domain.BorrowTransaction;
import com.databaseproject.library_system.domain.Copy;
import com.databaseproject.library_system.domain.Reader;
import com.databaseproject.library_system.domain.ReservationTransaction;
import com.databaseproject.library_system.model.BorrowStatus;
import com.databaseproject.library_system.model.CopyStatus;
import com.databaseproject.library_system.model.ReservationStatus;
import com.databaseproject.library_system.service.BorrowService;
import com.databaseproject.library_system.service.CopyService;
import com.databaseproject.library_system.service.ReaderService;
import com.databaseproject.library_system.service.ReservationService;
import com.databaseproject.library_system.util.BorrowAndReserveUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController

@RequestMapping("/api")
public class BorrowAndReserveController {
    @Autowired
    BorrowService borrowService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    ReaderService readerService;

    @Autowired
    CopyService copyService;

    @Autowired
    BorrowAndReserveUtil util;

    @Transactional
    @PostMapping("reserve")
    public ReservationTransaction reserveCopy(@RequestParam long reader_id,
                                              @RequestParam long bid,
                                              @RequestParam long doc_id,
                                              @RequestParam long copy_num) {
        Reader reader = readerService.getReaderById(reader_id);
        if (!util.allowedForReserve(reader)) return new ReservationTransaction();
        Copy copy = copyService.findCopyById(bid, copy_num, doc_id);
        copy.setCopyStatus(CopyStatus.RESERVED.getStatusValue());
        ReservationTransaction trans = new ReservationTransaction();
        trans.setCopy(copy);
        trans.setReader(readerService.getReaderById(reader_id));
        trans.setRes_date_time(new Timestamp(new Date().getTime()));
        trans.setReservationStatus(ReservationStatus.RESERVED.getStatusValue());
        return reservationService.create(trans);
    }

    @Transactional
    @PostMapping("/borrow")
    public BorrowTransaction borrowCopy(ReservationTransaction trans, Reader reader) {
        Copy copy = trans.getCopy();
        copy.setCopyStatus(CopyStatus.BORROWED.getStatusValue());
        trans.setReservationStatus(ReservationStatus.FINISHED.getStatusValue());
        copyService.save(copy);
        reservationService.update(trans);
        BorrowTransaction borrowTransaction = new BorrowTransaction();
        borrowTransaction.setBor_date_time(new Timestamp(new Date().getTime()));
        borrowTransaction.setReservationStatus(BorrowStatus.BORROWED.getStatusValue());
        borrowTransaction.setCopy(trans.getCopy());
        borrowTransaction.setReader(reader);
        return borrowService.add(borrowTransaction);
    }

    @Transactional
    @PostMapping("/checkout/{reader_id}")
    public List<BorrowTransaction> checkOut(@PathVariable long reader_id) {
        Reader reader = readerService.getReaderById(reader_id);
        List<ReservationTransaction> reservations = reservationService
                .getDataByReaderAndStatus(reader, ReservationStatus.RESERVED.getStatusValue());
        List<BorrowTransaction> borrows = new ArrayList<>();
        for (ReservationTransaction reservation: reservations) {
            borrows.add(borrowCopy(reservation, reader));
        }
        return borrows;
    }

    @Transactional
    @PostMapping("/return")
    public String returnCopy(@RequestParam long reader_id,
                                        @RequestParam long bid,
                                        @RequestParam long doc_id,
                                        @RequestParam long copy_num) {
        Copy copy = copyService.findCopyById(bid, copy_num, doc_id);
        BorrowTransaction borrowTransaction = null;
        try {
            borrowTransaction = borrowService.getByCopy(copy, CopyStatus.BORROWED.getStatusValue());
        } catch(Exception e) {

        }
        if (borrowTransaction == null ||
                borrowTransaction.getReader().getReader_id() != reader_id) return "No data found";
        copy.setCopyStatus(CopyStatus.AVAILABLE.getStatusValue());
        copyService.save(copy);
        borrowTransaction.setReservationStatus(BorrowStatus.RETURNED.getStatusValue());
        borrowService.update(borrowTransaction);
        return "Your will be fined for " + calcFine(borrowTransaction) + " dollars";
    }

    @Transactional
    @GetMapping("/fine")
    public String getFine(@RequestParam long reader_id,
                             @RequestParam long bid,
                             @RequestParam long doc_id,
                             @RequestParam long copy_num) {
        Copy copy = copyService.findCopyById(bid, copy_num, doc_id);
        BorrowTransaction borrowTransaction = null;
        try {
            borrowTransaction = borrowService.getByCopy(copy, CopyStatus.BORROWED.getStatusValue());
        } catch(Exception e) {

        }
        if (borrowTransaction == null ||
                borrowTransaction.getReader().getReader_id() != reader_id) return "No data found";
        return "Your will be fined for " + calcFine(borrowTransaction) + " dollars";
    }

    @GetMapping("reserved/copies/{id}")
    public List<Copy> getReservedCopies(@PathVariable long id) {
        return copyService.findCopiesReservedByReader(id);
    }

    private double calcFine(BorrowTransaction borrowTransaction) {
        long current = new Date().getTime();
        long prev = borrowTransaction.getBor_date_time().getTime();
        if (current - prev <= 86400 * 20) return 0.0;
        return (((current - prev) + 86400) / 86400) * 0.2;
    }
}
