package com.databaseproject.library_system.web;

import com.databaseproject.library_system.domain.*;
import com.databaseproject.library_system.model.*;
import com.databaseproject.library_system.service.*;
import com.databaseproject.library_system.util.BorrowAndReserveUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ReaderAPP {
    @Autowired
    private  ReaderService readerService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    BorrowService borrowService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    CopyService copyService;

    @Autowired
    BorrowAndReserveUtil util;

    public static Reader reader;

    @PostMapping("/reader")
    public String post(Reader reader, Model model) {
        Reader r =  readerService.addReader(reader);
        model.addAttribute("reader", r);
        return "reader_detail";
    }

    @GetMapping("/reader/op")
    public String toReaderOpPage(Model model) {
        if (reader == null) return "READER_LOGIN_REQUIRED";
        model.addAttribute("reader", reader);
        return "reader_menu";
    }

    @GetMapping("/reserve/input")
    public String toReserveInputPage(Model model) {
        if (reader == null) return "READER_LOGIN_REQUIRED";
        model.addAttribute("reader", reader);
        model.addAttribute("copy",new Copy.CopyId());
        return "reserve_input";
    }

    @GetMapping("/reader")
    public String findReader(@RequestParam long reader_Id, Model model) {
        Reader reader = null;
        try {
            reader = readerService.getReaderById(reader_Id);
        } catch(Exception e) {

        }
        if (reader == null) return "INVALID_READER_ID";
        this.reader = reader;
        model.addAttribute("reader", reader);
        return "reader_menu";
    }

    @GetMapping("/document/id")
    public String getDocumentById(@RequestParam long doc_id, Model model) throws Exception{
        Document document = null;
        try {
            document = documentService.findDocumentById(doc_id);
        } catch(Exception e) {
        }
        if (document == null) return "DOCUMENT_ID_NOT_EXITS";
        if (reader == null) return "READER_LOGIN_REQUIRED";
        List<Document> documents = new ArrayList<>();
        documents.add(document);
        model.addAttribute("documents", documents);
        model.addAttribute("reader", reader);
        return "documents";
    }

    @GetMapping("/document/title")
    public String getDocumentsByTitle(@RequestParam String title, Model model) {
        List<Document> documents = documentService.findDocumentByTitle(title);
        if (documents == null || documents.isEmpty()) return "DOCUMENT_TITLE_NOT_EXITS";
        if (reader == null) return "READER_LOGIN_REQUIRED";
        model.addAttribute("documents", documents);
        model.addAttribute("reader", reader);
        return "documents";
    }

    @GetMapping("/document/publisher")
    public String goDocumentPublisherPage(@RequestParam String pub_name, Model model) {
        List<Document> documents =  documentService.findDocumentByPublisherName(pub_name);
        if (documents == null || documents.isEmpty()) return "DOCUMENT_NOT_EXITS";
        if (reader == null) return "READER_LOGIN_REQUIRED";
        model.addAttribute("documents", documents);
        model.addAttribute("reader", reader);
        return "documents";
    }

    @Transactional
    @PostMapping("/reserve")
    public String reserveCopy(@RequestParam long bid,
                              @RequestParam long doc_id,
                              @RequestParam long copy_num, Model model) {
        if (reader == null) return "READER_LOGIN_REQUIRED";
        if (!util.allowedForReserve(reader)) return "TO_MANY_RESERVATIONS_OR_BORROWS";
        Copy copy = null;
        try {
            copy = copyService.findCopyById(bid, copy_num, doc_id);
        } catch (Exception e) {

        }
        if (copy == null || copy.getCopyStatus() != CopyStatus.AVAILABLE.getStatusValue()) return "COPY_NOT_AVAILABLE";
        copy.setCopyStatus(CopyStatus.RESERVED.getStatusValue());
        copyService.save(copy);
        ReservationTransaction trans = new ReservationTransaction();
        trans.setCopy(copy);
        trans.setReader(reader);
        trans.setRes_date_time(new Timestamp(new Date().getTime()));
        trans.setReservationStatus(ReservationStatus.RESERVED.getStatusValue());
        ReservationTransaction res =  reservationService.create(trans);
        model.addAttribute("res", res);
        return "reservation_detail";
    }

    @Transactional
    @GetMapping("/checkout")
    public String checkOut(Model model) {
        if (reader == null) return "READER_LOGIN_REQUIRED";
        List<ReservationTransaction> reservations = reservationService
                .getDataByReaderAndStatus(reader, ReservationStatus.RESERVED.getStatusValue());
        if (reservations == null || reservations.isEmpty()) return "NO_RESERVATION_FOUND";
        List<BorrowTransaction> borrows = new ArrayList<>();
        for (ReservationTransaction reservation: reservations) {
            borrows.add(borrowCopy(reservation, reader));
        }
        model.addAttribute("trans", borrows);
        return "borrow_details";
    }

    @Transactional
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
    @GetMapping("/return/all")
    public String returnAll(Model model) {
        if (reader == null) return "READER_LOGIN_REQUIRED";
        List<BorrowTransaction> trans = borrowService.getByReaderAndStatus(reader, BorrowStatus.BORROWED.getStatusValue());
        if (trans == null || trans.size() == 0) return "NO_BORROW_FOUND";
        List<BorrowReturnDetail> res = new ArrayList<>();
        for (BorrowTransaction tran : trans) {
            BorrowReturnDetail detail = new BorrowReturnDetail();
            detail.setBor_date_time(tran.getBor_date_time());
            detail.setBor_number(tran.getBor_number());
            detail.setFine(returnOne(tran));
            res.add(detail);
        }
        model.addAttribute("trans", res);
        return "return_details";
    }

    private double returnOne(BorrowTransaction tran) {
        Copy copy = tran.getCopy();
        copy.setCopyStatus(CopyStatus.AVAILABLE.getStatusValue());
        copyService.save(copy);
        tran.setReservationStatus(BorrowStatus.RETURNED.getStatusValue());
        tran.setRet_date_time(new Timestamp(new Date().getTime()));
        borrowService.update(tran);
        return calcFine(tran);
    }

    @GetMapping("/fine")
    public String toFineInputPage(Model model) {
        model.addAttribute("id", new Copy.CopyId());
        return "fine_input_page";
    }

    @Transactional
    @GetMapping("/fine/result")
    public String getFine(@RequestParam long bid,
                          @RequestParam long doc_id,
                          @RequestParam long copy_num, Model model) {
        Copy copy = null;
        try {
            copy = copyService.findCopyById(bid, copy_num, doc_id);
        } catch (Exception e) {

        }
        if (copy == null) return "COPY_NOT_AVAILABLE";
        BorrowTransaction borrowTransaction = null;
        for (BorrowTransaction t: copy.getBorrowTransactions()) {
            if (t.getReservationStatus() == BorrowStatus.BORROWED.getStatusValue()) {
                borrowTransaction = t;
                break;
            }
        }
        if (borrowTransaction == null ||
                borrowTransaction.getReader().getReader_id() != reader.getReader_id()) return "NO_BORROW_FOUND";
        List<BorrowReturnDetail> res = new ArrayList<>();
        BorrowReturnDetail detail = new BorrowReturnDetail();
        detail.setBor_date_time(borrowTransaction.getBor_date_time());
        detail.setBor_number(borrowTransaction.getBor_number());
        detail.setFine(calcFine(borrowTransaction));
        res.add(detail);
        model.addAttribute("trans", res);
        return "return_details";
    }

    private double calcFine(BorrowTransaction borrowTransaction) {
        long current = new Date().getTime();
        long prev = borrowTransaction.getBor_date_time().getTime();
        if (current - prev <= 86400000l * 20) return 0.0;
        return (((current - prev) + 86400000l) / 86400000l) * 0.2 - 4.0;
    }


    @GetMapping("/reserved")
    public String findReservations(Model model) {
        List<ReservationTransaction> res = reservationService.getDataByReader(reader);
        model.addAttribute("transactions", res);
        return "reservation_details";
    }

    @GetMapping("/reserved/copies")
    public String findReservedCopies(Model model) {
        List<ReservationTransaction> res = reservationService.getDataByReader(reader);
        List<Copy> copies = new ArrayList<>();
        for (ReservationTransaction tran: res) {
            if (tran.getReservationStatus() == ReservationStatus.RESERVED.getStatusValue()) {
                copies.add(tran.getCopy());
            }
        }
        if (copies.size() == 0) return "COPY_NOT_AVAILABLE";
        model.addAttribute("copies", copies);
        return "copies";
    }

}
