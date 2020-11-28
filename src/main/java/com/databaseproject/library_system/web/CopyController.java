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

    @GetMapping("/copies/availability")
    public List<Copy> findAvailableCopiesByDocument(Document document) {
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
    public Copy addCopy(Copy copy) {
        return copyService.save(copy);
    }

    @PutMapping("/copy")
    public Copy updateCopy(Copy copy) {
        return copyService.save(copy);
    }

    @Transactional
    @GetMapping("/copies/by/document/{id}")
    public List<Copy> findCopiesByDocument(@PathVariable long id) {
        return copyService.findCopiesByDocument(documentService.findDocumentById(id));
    }
}
