package com.databaseproject.library_system.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CopyRepository extends JpaRepository<Copy, Copy.CopyId> {
    List<Copy> findCopiesByDocument(Document document);
    List<Copy> findCopiesByBranch(Branch branch);

    @Query(value = "SELECT * FROM copy where bid = ?1 AND doc_id = ?2 AND copy_num = ?3", nativeQuery = true)
    Copy findCopyByCopyId(long bid, long doc_id, long copy_num);

    @Query(value = "SELECT * FROM copy WHERE (copy.doc_id, copy.bid, copy.copy_num) IN " +
            "(SELECT doc_id, bid, copy_num FROM reservation_transaction WHERE reader_id = ?1)", nativeQuery = true)
    List<Copy> findCopiesByReader(long reader_id);
}


