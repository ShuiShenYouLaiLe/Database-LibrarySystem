package com.databaseproject.library_system.web;

import com.databaseproject.library_system.domain.*;
import com.databaseproject.library_system.model.*;
import com.databaseproject.library_system.model.Book;
import com.databaseproject.library_system.service.DocumentService;
import com.databaseproject.library_system.service.ReaderService;
import com.databaseproject.library_system.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class DocumentAPP {
    @Autowired
    private DocumentService documentService;

    @Autowired
    ReaderService readerService;

    @Autowired
    ReaderRepository readerRepository;

    private Reader reader = ReaderAPP.reader;

    @GetMapping("/reader/document/id")
    public String goDocumentIdPage(Model model) {
        model.addAttribute("document", new Document());
        return "document_id_input";
    }

    @GetMapping("/reader/document/title")
    public String goDocumentTitlePage(Model model) {
        model.addAttribute("document", new Document());
        return "document_title_input";
    }

    @GetMapping("/reader/document/publisher")
    public String goDocumentPublisherPage(Model model) {
        model.addAttribute("publisher", new Publisher());
        return "document_publisher_input";
    }

    @GetMapping("/document/publisher/2")
    public String documentPublisherPage(Model model) {
        model.addAttribute("publisher", new Publisher());
        return "document_publisher_input2";
    }

    @GetMapping("/document/publisher/details")
    public String goDocumentPublisherPage(@RequestParam String pub_name, Model model) {
        List<Document> documents =  documentService.findDocumentByPublisherName(pub_name);
        if (documents == null || documents.isEmpty()) return "DOCUMENT_NOT_EXITS";
        model.addAttribute("publisher", pub_name);
        model.addAttribute("documents", documents);
        return "documents_with_id";
    }

    @GetMapping("/document/details/{doc_id}")
    public String getDetails(@PathVariable long doc_id, Model model) {
        Document document = documentService.findDocumentById(doc_id);
        DocumentInterface di = null;
        String return_template = "";
        if (document.getDoc_type() == DocType.Book.getTypeName()) {
            di = new Book();
            returnBook(document, (Book)di);
            di.setDocId(document.getDoc_id());
            di.setDocType(((int)(long)document.getDoc_type()));
            di.setPDate(document.getP_date());
            di.setPublisher(document.getPublisher());
            di.setTitle(document.getTitle());
            model.addAttribute("book", (Book) di);
            return_template = "book_details";
        } else if (document.getDoc_type() == DocType.JournalVolume.getTypeName()) {
            di = new JournalVolume();
            returnJournal(document, (JournalVolume)di);
            di.setDocId(document.getDoc_id());
            di.setDocType(((int)(long)document.getDoc_type()));
            di.setPDate(document.getP_date());
            di.setPublisher(document.getPublisher());
            di.setTitle(document.getTitle());
            model.addAttribute("journal", (JournalVolume) di);
            return_template = "journal_details";
        } else {
            di = new Proceedings();
            returnProceedings(document, (Proceedings)di);
            di.setDocId(document.getDoc_id());
            di.setDocType(((int)(long)document.getDoc_type()));
            di.setPDate(document.getP_date());
            di.setPublisher(document.getPublisher());
            di.setTitle(document.getTitle());
            model.addAttribute("proceedings", (Proceedings) di);
            return_template = "proceedings_details";
        }

        return return_template;
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
