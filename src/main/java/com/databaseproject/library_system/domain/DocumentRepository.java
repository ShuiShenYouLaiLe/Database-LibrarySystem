package com.databaseproject.library_system.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findDocumentsByTitle(String title);
    @Query(value = "SELECT * FROM document where document.publisher_id in " +
            "(select publisher_id from publisher where pub_name = ?1)", nativeQuery = true)
    List<Document> findDocumentByPublisherName(String pub_name);
}

