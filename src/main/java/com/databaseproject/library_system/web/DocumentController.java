package com.databaseproject.library_system.web;

import com.databaseproject.library_system.domain.*;
import com.databaseproject.library_system.model.*;
import com.databaseproject.library_system.model.Book;
import com.databaseproject.library_system.service.DocumentService;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/document")
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    //Get Document By Id: http://127.0.0.1:8080/api/document/id/{id}
    @GetMapping("/id/{id}")
    public DocumentInterface getDocumentById(@PathVariable long id) throws Exception{
        Document document = new Document();
        try {
            document = documentService.findDocumentById(id);
        } catch(Exception e) {
        }
        return getDetails(document);
    }


    @GetMapping("/title/{title}")
    public List<Document> getDocumentsByTitle(@PathVariable String title) {
        return documentService.findDocumentByTitle(title);
    }

    @GetMapping("/publisherName/{pub_name}")
    public List<Document> getDocumentsByPubName(@PathVariable String pub_name) {
        return documentService.findDocumentByPublisherName(pub_name);
    }

    @GetMapping("/details")
    public DocumentInterface getDetails(Document document) {
        DocumentInterface di = null;
        if (document.getDoc_type() == DocType.Book.getTypeName()) {
            di = new Book();
            returnBook(document, (Book)di);
        } else if (document.getDoc_type() == DocType.JournalVolume.getTypeName()) {
            di = new JournalVolume();
            returnJournal(document, (JournalVolume)di);
        } else {
            di = new Proceedings();
            returnProceedings(document, (Proceedings)di);
        }
        di.setDocId(document.getDoc_id());
        di.setDocType(((int)(long)document.getDoc_type()));
        di.setPDate(document.getP_date());
        di.setPublisher(document.getPublisher());
        di.setTitle(document.getTitle());
        return di;
    }

    private void returnBook(Document doc, Book book) {
        Set<Person> authors = new HashSet<>();
        for (Authors author: doc.getAuthors()) {
            authors.add(author.getAuthor());
        }
        book.setAuthors(authors);
        book.setIsbn(doc.getIsbn());
    }

    private void returnJournal(Document doc, JournalVolume journalVolume) {
        journalVolume.setChiefEditor(doc.getEditor());
        journalVolume.setVolume_num(doc.getVolume_num());
        List<JournalIssue> issues = new ArrayList<>();
        for (JournalIssue issue: doc.getJournalIssues()) issues.add(issue);
        Collections.sort(issues, new Comparator<JournalIssue>() {
            @Override
            public int compare(JournalIssue o1, JournalIssue o2) {
                return o1.getId().getIssue_no() - o2.getId().getIssue_no();
            }
        });
        journalVolume.setJournalIssues(issues);
    }

    private void returnProceedings(Document doc, Proceedings proceedings) {
        proceedings.setcDate(doc.getC_date());
        proceedings.setcLocation(doc.getC_location());
        Set<Person> chairs = new HashSet<>();
        for (Chairs chair: doc.getChairs()) {
            chairs.add(chair.getChairs());
        }
        proceedings.setChairs(chairs);
    }

}
