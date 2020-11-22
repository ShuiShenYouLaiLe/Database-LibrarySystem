package com.databaseproject.library_system.web;

import com.databaseproject.library_system.domain.Document;
import com.databaseproject.library_system.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/document")
public class DocumentController {
    @Autowired
    private DocumentService documentService;


    @GetMapping("/id/{id}")
    public Document getDocumentById(@PathVariable long id){
        return documentService.findDocumentById(id);
    }

    @GetMapping("/title/{title}")
    public List<Document> getDocumentsByTitle(@PathVariable String title) {
        return documentService.findDocumentByTitle(title);
    }

    @GetMapping("/publisherName/{pub_name}")
    public List<Document> getDocumentsByPubName(@PathVariable String pub_name) {
        return documentService.findDocumentByPublisherName(pub_name);
    }
}
