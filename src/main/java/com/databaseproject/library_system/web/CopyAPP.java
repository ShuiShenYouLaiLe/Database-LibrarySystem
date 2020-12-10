package com.databaseproject.library_system.web;

import com.databaseproject.library_system.domain.Branch;
import com.databaseproject.library_system.domain.Copy;
import com.databaseproject.library_system.domain.Document;
import com.databaseproject.library_system.model.CopyStatus;
import com.databaseproject.library_system.service.BranchService;
import com.databaseproject.library_system.service.CopyService;
import com.databaseproject.library_system.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CopyAPP {
    @Autowired
    CopyController copyController;

    @Autowired
    CopyService copyService;

    @Autowired
    DocumentService documentService;

    @Autowired
    BranchService branchService;

    @GetMapping("/copy")
    public String findCopy(@RequestParam long bid,
                           @RequestParam long copy_num,
                           @RequestParam long doc_id, Model model) {
        Copy copy = new Copy();
        try {
            copy = copyService.findCopyById(bid, copy_num, doc_id);
        } catch (Exception e) {

        }
        if (copy == null) return "failed_page";
        model.addAttribute("copy", copy);
        return "copy";
    }

    @GetMapping("/copies/availability/{doc_id}")
    public String findAvailableCopiesByDocument(@PathVariable long doc_id, Model model) {
        Document document = null;
        try {
            document = documentService.findDocumentById(doc_id);
        } catch(Exception e) {

        }
        if (document == null) return "COPY_NOT_AVAILABLE";
        List<Copy> copies = copyService.findCopiesByDocument(document);
        List<Copy> res = new ArrayList<>();
        for (Copy copy: copies) {
            if (copy.getCopyStatus() == CopyStatus.AVAILABLE.getStatusValue()) {
                res.add(copy);
            }
        }
        if (res == null || res.isEmpty()) return "COPY_NOT_AVAILABLE";
        model.addAttribute("copies", res);
        return "copies";
    }

    @PostMapping("/copy")
    @Transactional
    public String addCopy(@RequestParam long bid,
                        @RequestParam long doc_id,
                        @RequestParam String position) {
        Document document = null;
        Branch branch = null;
        try {
            document = documentService.findDocumentById(doc_id);
            branch = branchService.getBranchById(bid);
        } catch(Exception e) {

        }
        if (document == null || branch == null) return "failed_page";
        List<Copy> copies = copyService.findCopiesByDocument(document);
        long num = 1;
        for (Copy c: copies) {
            if (c.getId().getBid() != bid) continue;
            num = Math.max(c.getId().getCopy_num(), num);
        }
        Copy copy = new Copy();
        Copy.CopyId id = new Copy.CopyId();
        id.setCopy_num(num + 1);
        id.setBid(bid);
        id.setDoc_id(doc_id);
        copy.setId(id);
        copy.setPosition(position);
        copy.setCopyStatus(CopyStatus.AVAILABLE.getStatusValue());
        Copy c = copyService.save(copy);
        if (c == null) return "failed_page";
        return "Successful_Page";
    }

    @PutMapping("/copy/missed")
    public Copy updateCopyMissed(@RequestParam long bid,
                                 @RequestParam long copy_num,
                                 @RequestParam long doc_id) {
        Copy copy = null;
        try {
            copy = copyController.findCopy(bid, copy_num, doc_id);
        } catch(Exception e) {

        }
        if (copy == null) return copy;
        copy.setCopyStatus(CopyStatus.MISSED.getStatusValue());
        return copyService.save(copy);
    }

    @PutMapping("/copy/reserved")
    public Copy updateCopyReserved(@RequestParam long bid,
                                   @RequestParam long copy_num,
                                   @RequestParam long doc_id) {
        Copy copy = null;
        try {
            copy = copyController.findCopy(bid, copy_num, doc_id);
        } catch(Exception e) {

        }
        if (copy == null) return copy;
        copy.setCopyStatus(CopyStatus.RESERVED.getStatusValue());
        return copyService.save(copy);
    }

    @PutMapping("/copy/borrowed")
    public Copy updateCopyBorrowed(@RequestParam long bid,
                                   @RequestParam long copy_num,
                                   @RequestParam long doc_id) {
        Copy copy = null;
        try {
            copy = copyController.findCopy(bid, copy_num, doc_id);
        } catch(Exception e) {

        }
        if (copy == null) return copy;
        copy.setCopyStatus(CopyStatus.BORROWED.getStatusValue());
        return copyService.save(copy);
    }

    @PutMapping("/copy/available")
    public Copy updateCopyAvailable(@RequestParam long bid,
                                    @RequestParam long copy_num,
                                    @RequestParam long doc_id) {
        Copy copy = null;
        try {
            copy = copyController.findCopy(bid, copy_num, doc_id);
        } catch(Exception e) {

        }
        if (copy == null) return copy;
        copy.setCopyStatus(CopyStatus.AVAILABLE.getStatusValue());
        return copyService.save(copy);
    }

    @Transactional
    @GetMapping("/copies/by/document/{id}")
    public List<Copy> findCopiesByDocument(@PathVariable long id) {
        return copyService.findCopiesByDocument(documentService.findDocumentById(id));
    }
}
