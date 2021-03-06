package com.databaseproject.library_system.web;

import com.databaseproject.library_system.domain.Copy;
import com.databaseproject.library_system.domain.Document;
import com.databaseproject.library_system.model.CopyStatus;
import com.databaseproject.library_system.service.CopyService;
import com.databaseproject.library_system.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CopyController {
    @Autowired
    CopyService copyService;

    @Autowired
    DocumentService documentService;

    @GetMapping("/copy")
    public Copy findCopy(@RequestParam long bid,
                         @RequestParam long copy_num,
                         @RequestParam long doc_id) {
        Copy copy = new Copy();
        try {
            copy = copyService.findCopyById(bid, copy_num, doc_id);
        } catch (Exception e) {

        }
        return copy;
    }

    @GetMapping("/copies/availability/{id}")
    public List<Copy> findAvailableCopiesByDocument(@PathVariable long id) {
        Document document = null;
        try {
            document = documentService.findDocumentById(id);
        } catch(Exception e) {

        }
        if (document == null) return new ArrayList<>();
        List<Copy> copies = copyService.findCopiesByDocument(document);
        List<Copy> res = new ArrayList<>();
        for (Copy copy: copies) {
            if (copy.getCopyStatus() == CopyStatus.AVAILABLE.getStatusValue()) {
                res.add(copy);
            }
        }
        return res;
    }

    @PostMapping("/copy")
    @Transactional
    public Copy addCopy(@RequestParam long bid,
                        @RequestParam long doc_id,
                        @RequestParam String position) {
        Document document = null;
        try {
            document = documentService.findDocumentById(doc_id);
        } catch(Exception e) {

        }
        if (document == null) return new Copy();
        List<Copy> copies = copyService.findCopiesByDocument(document);
        long num = 1;
        for (Copy c: copies) {
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
        return copyService.save(copy);
    }

    @PutMapping("/copy/missed")
    public Copy updateCopyMissed(@RequestParam long bid,
                           @RequestParam long copy_num,
                           @RequestParam long doc_id) {
        Copy copy = null;
        try {
            copy = findCopy(bid, copy_num, doc_id);
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
            copy = findCopy(bid, copy_num, doc_id);
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
            copy = findCopy(bid, copy_num, doc_id);
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
            copy = findCopy(bid, copy_num, doc_id);
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
