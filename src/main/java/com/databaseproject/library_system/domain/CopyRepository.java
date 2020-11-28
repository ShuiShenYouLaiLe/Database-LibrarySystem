package com.databaseproject.library_system.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CopyRepository extends JpaRepository<Copy, Copy.CopyId> {
    List<Copy> findCopiesByDocument(Document document);
    List<Copy> findCopiesByBranch(Branch branch);
}
