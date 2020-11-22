package com.databaseproject.library_system.service;

import com.databaseproject.library_system.domain.Document;
import com.databaseproject.library_system.domain.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService{

    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public Document findDocumentById(long documentId) {
        return documentRepository.findById(documentId).get();
    }

    @Override
    public List<Document> findDocumentByTitle(String title) {
        return documentRepository.findDocumentsByTitle(title);
    }

    @Override
    public List<Document> findDocumentByPublisherName(String pub_name) {
        return documentRepository.findDocumentByPublisherName(pub_name);
    }
}
