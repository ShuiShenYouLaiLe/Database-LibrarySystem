package com.databaseproject.library_system.web;

import com.databaseproject.library_system.domain.Document;
import com.databaseproject.library_system.service.DocumentService;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    //Get Document By Id: http://127.0.0.1:8080/api/document/id/{id}
    @GetMapping("/id/{id}")
    public String getDocumentById(@PathVariable long id) throws Exception{
        Document document = null;
        try {
            document = documentService.findDocumentById(id);
        } catch(Exception e) {
        }
        ObjectMapper mapper = new ObjectMapper();

        if (document != null) return mapper.writeValueAsString(document);
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\" />\n" +
                "    <title>books</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>Id not exits!</h1>\n" +
                "</body>\n" +
                "</html>";
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
