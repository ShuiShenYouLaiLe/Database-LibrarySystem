package com.databaseproject.library_system.service;

import com.databaseproject.library_system.domain.Document;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DocumentService {
    Document findDocumentById(long documentId);
    List<Document> findDocumentByTitle(String title);
    List<Document> findDocumentByPublisherName(String pub_name);
}
