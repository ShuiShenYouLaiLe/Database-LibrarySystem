package com.databaseproject.library_system.service;


import com.databaseproject.library_system.domain.Branch;
import com.databaseproject.library_system.domain.Copy;
import com.databaseproject.library_system.domain.Document;

import java.util.List;

public interface CopyService {
    List<Copy> findCopiesByDocument(Document document);
    Copy save(Copy copy);
    void remove(Copy copy);
    List<Copy> findCopiesByBranch(Branch branch);
    Copy findCopyById(long bid, long copy_num, long doc_id);
    List<Copy> findCopiesReservedByReader(long reader_Id);
}
